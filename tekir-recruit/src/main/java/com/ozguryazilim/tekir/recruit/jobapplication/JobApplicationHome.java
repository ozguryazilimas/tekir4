package com.ozguryazilim.tekir.recruit.jobapplication;

import com.ozguryazilim.tekir.contact.information.ContactInformationRepository;
import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactPhone;
import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.tekir.recruit.applicant.ApplicantHome;
import com.ozguryazilim.tekir.recruit.jobapplication.evaluationnotes.EvaluationNoteRepository;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.quick.QuickRecordController;
import java.util.Collections;
import javax.inject.Inject;
import org.omnifaces.cdi.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erdem Uslu
 */
@FormEdit(feature = JobApplicationFeature.class)
@AutoCode(cosumer = "JobApplication", caption = "module.caption.JobApplicaiton", serial = "JAP")
public class JobApplicationHome extends FormBase<JobApplication, Long> {

    private static Logger LOG = LoggerFactory.getLogger(JobApplicationHome.class);

    @Inject
    private JobApplicationRepository repository;

    @Inject
    private ContactInformationRepository contactInformationRepository;

    @Inject
    private ApplicantHome applicantHome;

    @Inject
    @Param
    private Boolean isQuick;

    @Inject
    private QuickRecordController quickRecordController;

    @Inject
    private Identity identity;

    @Inject
    private AutoCodeService codeService;

    @Inject
    private EvaluationNoteRepository evaluationNoteRepository;

    private String quickEmail;
    private String quickPhone;

    public String getQuickEmail() {
        return quickEmail;
    }

    public void setQuickEmail(String quickEmail) {
        this.quickEmail = quickEmail;
    }

    public String getQuickPhone() {
        return quickPhone;
    }

    public void setQuickPhone(String quickPhone) {
        this.quickPhone = quickPhone;
    }

    @Override
    protected RepositoryBase<JobApplication, ?> getRepository() {
        return repository;
    }

    @Override
    public void createNew() {
        super.createNew();
        getEntity().setOwner(identity.getLoginName());
        getEntity().setCode(codeService.getNewSerialNumber(JobApplication.class.getSimpleName()));
        if (isQuick != null && isQuick) {
            quickPhone = null;
            quickEmail = null;
            applicantHome.createNew();
            getEntity().setApplicant(applicantHome.getEntity());
        }
    }

    /**
     * Yeni Başvuru Adayı için quickPanelContect'i başvuru aday formuna ayarlar.
     */
    public void createApplicantQuickPanel() {
        quickRecordController.setName("applicantQuickRecord");
    }

    /**
     * NoteWidget için gerekli.
     *
     * @return
     */
    public FeaturePointer getFeaturePointer() {
        FeaturePointer result = new FeaturePointer();
        result.setBusinessKey(getEntity().getCode());
        result.setFeature(getFeatureClass().getSimpleName());
        result.setPrimaryKey(getEntity().getId());
        return result;
    }

    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase entityBase) {
        return FeatureUtils.getFeaturePointer(entityBase);
    }

    public boolean isCanEvaluate() {
        return evaluationNoteRepository.findByApplicationAndOwner(getEntity(), identity.getLoginName());
    }

    @Override
    public boolean onBeforeSave() {
        if (isQuick != null && isQuick) {
            Applicant applicant = getEntity().getApplicant();

            ContactPhone contactPhone = new ContactPhone();
            contactPhone.setContact(applicant);
            contactPhone.setRoles(Collections.singletonList("PRIMARY"));
            contactPhone.setAddress(quickPhone);
            contactInformationRepository.save(contactPhone);
            applicant.setPrimaryMobile(contactPhone);

            ContactEMail contactEMail = new ContactEMail();
            contactEMail.setEmailAddress(quickEmail);
            contactEMail.setContact(applicant);
            contactEMail.setRoles(Collections.singletonList("PRIMARY"));
            contactInformationRepository.save(contactEMail);
            applicant.setPrimaryEmail(contactEMail);

            applicantHome.save();
        }
        return super.onBeforeSave();
    }
}
