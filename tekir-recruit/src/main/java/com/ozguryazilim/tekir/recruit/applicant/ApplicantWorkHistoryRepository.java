package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.ApplicantWorkHistory;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.util.List;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 * ApplicantWorkHistory için Repository sınıfı.
 *
 * @author serdar
 */
@Repository
@Dependent
public abstract class ApplicantWorkHistoryRepository extends
        RepositoryBase<ApplicantWorkHistory, ApplicantWorkHistory>
        implements
        CriteriaSupport<ApplicantWorkHistory> {

    /**
     * Adayın iş geçmişi bilgilerini Liste halinde getirir.
     * @param applicant
     * @return 
     */
    public abstract List<ApplicantWorkHistory> findByApplicant(Applicant applicant);

}
