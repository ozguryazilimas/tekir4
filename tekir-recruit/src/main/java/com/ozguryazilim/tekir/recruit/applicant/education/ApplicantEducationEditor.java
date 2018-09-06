package com.ozguryazilim.tekir.recruit.applicant.education;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.ApplicantEducation;
import com.ozguryazilim.tekir.recruit.applicant.information.AbstractApplicantInformationEditor;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationEditor;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationRepositoryBase;
import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import javax.inject.Inject;

/**
 *
 * @author Erdem Uslu
 */
@ApplicantInformationEditor(page = RecruitPages.ApplicantPages.ApplicantEducationEditor.class)
public class ApplicantEducationEditor extends AbstractApplicantInformationEditor<ApplicantEducation> {

    @Inject
    private ApplicantEducationRepository applicantEducationRepository;
    
    @Override
    protected ApplicantInformationRepositoryBase<ApplicantEducation, ?> getRepository() {
        return applicantEducationRepository;
    }

    @Override
    public String getIcon(ApplicantEducation entity) {
        return "fa fa-graduation-cap";
    }

    @Override
    public void setApplicantOfEntity(Applicant applicant) {
        getEntity().setApplicant(applicant);
    }
    
}