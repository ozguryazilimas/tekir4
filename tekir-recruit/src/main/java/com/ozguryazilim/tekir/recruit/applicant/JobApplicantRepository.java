package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.util.List;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 * Adayın başvurduğu tüm ilanları alabilmek için gerekli Repository sınıfı
 *
 * @author serdar
 */
@Repository
@Dependent
public abstract class JobApplicantRepository extends
        RepositoryBase<JobApplication, JobApplication>
        implements
        CriteriaSupport<JobApplication> {

    /**
     * Başvurulan ilanları liste halinde getirir.
     *
     * @param applicant
     * @return
     */
    @Query("select c from JobApplication c where applicant = ?1")
    public abstract List<JobApplication> findByApplicant(Applicant applicant);

}
