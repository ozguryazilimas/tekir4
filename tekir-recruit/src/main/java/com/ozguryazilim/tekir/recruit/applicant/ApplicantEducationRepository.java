package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.ApplicantEducation;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.util.List;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 * ApplicantEducation için Repository Sınıfı
 * 
 * @author serdar
 */

@Repository
@Dependent
public abstract class ApplicantEducationRepository extends
        RepositoryBase<ApplicantEducation, ApplicantEducation>
        implements
        CriteriaSupport<ApplicantEducation>{
    
    /**
     * Applicant id'sine göre eğitim bilgilerini liste halinde getirir.
     * @param applicant
     * @return 
     */
    public abstract List<ApplicantEducation> findByApplicant( Applicant applicant );
    
}

