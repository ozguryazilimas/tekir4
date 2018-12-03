package com.ozguryazilim.tekir.recruit.applicant.workhistory;

import com.ozguryazilim.tekir.entities.ApplicantWorkHistory;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationRepositoryBase;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Erdem Uslu
 */
@Dependent
@Repository
public abstract class ApplicantWorkHistoryRepository 
        extends ApplicantInformationRepositoryBase<ApplicantWorkHistory, ApplicantWorkHistory> {
    
}
