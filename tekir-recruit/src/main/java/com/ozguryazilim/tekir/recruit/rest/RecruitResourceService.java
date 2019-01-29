package com.ozguryazilim.tekir.recruit.rest;


import com.google.common.base.Strings;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.tekir.recruit.applicant.ApplicantRepository;
import com.ozguryazilim.tekir.recruit.jobadvert.JobAdvertRepository;
import com.ozguryazilim.tekir.recruit.jobadvert.JobAdvertViewModel;
import com.ozguryazilim.tekir.recruit.jobapplication.JobApplicationRepository;
import com.ozguryazilim.tekir.recruit.rest.dto.JobApplicationRequest;
import com.ozguryazilim.tekir.recruit.rest.dto.JobApplicationResponse;
import com.ozguryazilim.telve.attachment.AttachmentContext;
import com.ozguryazilim.telve.attachment.AttachmentContextProvider;
import com.ozguryazilim.telve.attachment.AttachmentDocument;
import com.ozguryazilim.telve.attachment.AttachmentException;
import com.ozguryazilim.telve.attachment.AttachmentFolder;
import com.ozguryazilim.telve.attachment.AttachmentNotFoundException;
import com.ozguryazilim.telve.attachment.AttachmentStore;
import com.ozguryazilim.telve.attachment.AttacmentContextProviderSelector;
import com.ozguryazilim.telve.attachment.qualifiers.FileStore;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Path("/recruit")
@RequestScoped
public class RecruitResourceService{

    public static final int MAX_SIZE = 10240;

    @Inject
    private JobAdvertRepository jobAdvertRepository;

    @Inject
    private JobApplicationRepository jobApplicationRepository;

    @Inject
    private ApplicantRepository applicantRepository;

    @Inject
    private EntityManager entityManager;

    @Inject
    private Identity identity;

    @Inject
    private AutoCodeService codeService;

    @Inject
    @FileStore
    private AttachmentStore store;

    @Inject
    private AttacmentContextProviderSelector providerSelector;


    @GET
    @Path("/jobadvert")
    @Produces(MediaType.APPLICATION_JSON)
    public List<JobAdvertViewModel> getActiveJobAdverts() {
        return jobAdvertRepository.findActives();
    }

    @POST
    @Path("/apply")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public JobApplicationResponse applyToJob(JobApplicationRequest request) throws Exception {
        JobAdvert advert = jobAdvertRepository.findBy(request.getAdvertId());
        if (advert == null) {
            throw new Exception("JobAdvert not forund with id [" + request.getAdvertId() + "}");
        }


        Applicant applicant = applicantRepository.findAnyBySsn(request.getApplicant().getSsn());
        if (applicant == null) {
            saveApplicant(request);
            applicant = request.getApplicant();
        }

        if (!Strings.isNullOrEmpty(applicant.getJobTitle())) {
            applicant.setJobTitle("-");
        }

        JobApplication application = new JobApplication();
        application.setAdvert(advert);
        application.setApplicant(applicant);
        application.setOwner(identity.getLoginName());
        application.setCode(codeService.getNewSerialNumber(JobApplication.class.getSimpleName()));
        application.setDate(new Date());

        entityManager.persist(application);
        return new JobApplicationResponse(application.getCode());
    }

    private void saveApplicant(JobApplicationRequest request) {
        Applicant applicant = request.getApplicant();
        applicant.setCode(codeService.getNewSerialNumber(Applicant.class.getSimpleName()));
        if (applicant.getName() == null) {
            applicant.setName(applicant.getFirstName() + " " + applicant.getLastName());
        }
        entityManager.persist(applicant);

        if (request.getCertificates() != null) {
            request.getCertificates().forEach(c -> {
                c.setApplicant(applicant);
                entityManager.persist(c);
            });
        }

        if (request.getEducation() != null) {
            request.getEducation().forEach(e -> {
                e.setApplicant(applicant);
                entityManager.persist(e);
            });
        }

        if (request.getMilitaryObligation() != null) {
            request.getMilitaryObligation().setApplicant(applicant);
            entityManager.persist(request.getMilitaryObligation());
        }

        if (request.getWorkHistory() != null) {
            request.getWorkHistory().forEach(w -> {
                w.setApplicant(applicant);
                entityManager.persist(w);
            });
        }
    }

    @POST
    @Path("/attachment/{token}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void addAttachment(@PathParam("token") String token,
                              @Context HttpServletRequest request) throws Exception {

        JobApplication application = jobApplicationRepository.findByCode(token);
        if (application == null) {
            throw new Exception("Job application not found with token [" + token + "]");
        }

        Applicant applicant = application.getApplicant();

        DiskFileItemFactory factory = new DiskFileItemFactory(MAX_SIZE, getRepository());
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        List<FileItem> files = fileUpload.parseRequest(request);

        for (FileItem item : files) {
            if ("file".equals(item.getFieldName())) {
                attachFile(applicant, item);
                break;
            }
        }
    }

    private void attachFile(Applicant applicant, FileItem item) throws AttachmentException, AttachmentNotFoundException, IOException {
        FeaturePointer fp = FeatureUtils.getFeaturePointer(applicant, applicant.getCode(), applicant.getId());
        AttachmentContextProvider provider = providerSelector.getAttachmentContextProvider(fp, applicant);
        AttachmentContext context = provider.getContext(fp, applicant);
        AttachmentFolder folder = store.getFolder(context, "");
        store.addDocument(context, folder, new AttachmentDocument(item.getName()), item.getInputStream());
        item.delete();
    }

    private File getRepository() {
        String storagePath = FileUtils.getTempDirectoryPath() + File.separator + "recruit/";
        File repository = new File(storagePath);
        if (!repository.exists()) {
            repository.mkdir();
        }
        return repository;
    }
}
