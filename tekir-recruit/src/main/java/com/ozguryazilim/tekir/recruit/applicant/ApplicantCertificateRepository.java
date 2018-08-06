package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.ApplicantCertificate;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.util.List;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 * ApplicantCertificate için Repository Sınıfı.
 *
 * @author serdar
 */
@Repository
@Dependent
public abstract class ApplicantCertificateRepository extends
        RepositoryBase<ApplicantCertificate, ApplicantCertificate>
        implements
        CriteriaSupport<ApplicantCertificate> {

    /**
     * Applicant Id'sine göre adayın sertifika bilgilerini liste halinde getirir
     * @param applicant
     * @return 
     */
    public abstract List<ApplicantCertificate> findByApplicant(Applicant applicant);

}
