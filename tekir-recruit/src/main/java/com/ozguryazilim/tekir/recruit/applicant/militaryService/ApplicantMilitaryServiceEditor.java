package com.ozguryazilim.tekir.recruit.applicant.militaryService;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.ApplicantMilitaryService;
import com.ozguryazilim.tekir.recruit.applicant.information.AbstractApplicantInformationEditor;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationEditor;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationRepositoryBase;
import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import org.joda.time.LocalDate;

import javax.inject.Inject;

@ApplicantInformationEditor(page = RecruitPages.ApplicantPages.ApplicantMilitaryServiceEditor.class)
public class ApplicantMilitaryServiceEditor extends AbstractApplicantInformationEditor<ApplicantMilitaryService> {

    @Inject
    private ApplicantMilitaryServiceRepository applicantMilitaryServiceRepository;

    @Override
    protected ApplicantInformationRepositoryBase<ApplicantMilitaryService, ?> getRepository() {
        return applicantMilitaryServiceRepository;
    }

    @Override
    public String getIcon(ApplicantMilitaryService entity) {
        if (entity.getPostponedTo() == null) {
            return "fa-calendar";
        }

        return entity.getPostponedTo().before(LocalDate.now().toDate()) ? "fa-calendar-check-o text-green" : "fa-calendar";
    }

    @Override
    public void setApplicantOfEntity(Applicant applicant) {
        getEntity().setApplicant(applicant);
    }

}