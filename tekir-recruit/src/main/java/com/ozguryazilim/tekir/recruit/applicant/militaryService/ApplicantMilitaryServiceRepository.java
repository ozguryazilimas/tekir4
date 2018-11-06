package com.ozguryazilim.tekir.recruit.applicant.militaryService;

import com.ozguryazilim.tekir.entities.ApplicantMilitaryService;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationRepositoryBase;
import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;

@Dependent
@Repository
public abstract class ApplicantMilitaryServiceRepository
        extends ApplicantInformationRepositoryBase<ApplicantMilitaryService, ApplicantMilitaryService> {

}
