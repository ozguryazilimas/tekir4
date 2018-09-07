package com.ozguryazilim.tekir.recruit.applicant.workhistory;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.ApplicantWorkHistory;
import com.ozguryazilim.tekir.recruit.applicant.information.AbstractApplicantInformationEditor;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationEditor;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationRepositoryBase;
import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import javax.inject.Inject;

/**
 *
 * @author Erdem Uslu
 */
@ApplicantInformationEditor(page = RecruitPages.ApplicantPages.ApplicantWorkHistoryEditor.class)
public class ApplicantWorkHistoryEditor extends AbstractApplicantInformationEditor<ApplicantWorkHistory> {

    @Inject
    private ApplicantWorkHistoryRepository applicantWorkHistoryRepository;
    
    @Override
    protected ApplicantInformationRepositoryBase<ApplicantWorkHistory, ?> getRepository() {
        return applicantWorkHistoryRepository;
    }

    @Override
    public String getIcon(ApplicantWorkHistory entity) {
        return "fa-certificate";
    }

    @Override
    public void setApplicantOfEntity(Applicant applicant) {
        getEntity().setApplicant(applicant);
    }
    
}