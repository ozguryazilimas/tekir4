package com.ozguryazilim.tekir.recruit.applicant.education;

import com.ozguryazilim.tekir.entities.ApplicantEducation;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationRepositoryBase;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Erdem Uslu
 */
@Dependent
@Repository
public abstract class ApplicantEducationRepository 
        extends ApplicantInformationRepositoryBase<ApplicantEducation, ApplicantEducation> {
    
}
