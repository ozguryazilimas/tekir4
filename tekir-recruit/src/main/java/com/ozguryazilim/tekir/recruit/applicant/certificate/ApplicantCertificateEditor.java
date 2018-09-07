package com.ozguryazilim.tekir.recruit.applicant.certificate;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.ApplicantCertificate;
import com.ozguryazilim.tekir.recruit.applicant.information.AbstractApplicantInformationEditor;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationEditor;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationRepositoryBase;
import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import javax.inject.Inject;

/**
 *
 * @author Erdem Uslu
 */
@ApplicantInformationEditor(page = RecruitPages.ApplicantPages.ApplicantCertificateEditor.class)
public class ApplicantCertificateEditor extends AbstractApplicantInformationEditor<ApplicantCertificate> {

    @Inject
    private ApplicantCertificateRepository applicantCertificateRepository;
    
    @Override
    protected ApplicantInformationRepositoryBase<ApplicantCertificate, ?> getRepository() {
        return applicantCertificateRepository;
    }

    @Override
    public String getIcon(ApplicantCertificate entity) {
        return "";
    }

    @Override
    public void setApplicantOfEntity(Applicant applicant) {
        getEntity().setApplicant(applicant);
    }
    
}