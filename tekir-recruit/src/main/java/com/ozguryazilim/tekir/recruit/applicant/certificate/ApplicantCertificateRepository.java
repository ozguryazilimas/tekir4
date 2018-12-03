package com.ozguryazilim.tekir.recruit.applicant.certificate;

import com.ozguryazilim.tekir.entities.ApplicantCertificate;
import com.ozguryazilim.tekir.recruit.applicant.information.ApplicantInformationRepositoryBase;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Erdem Uslu
 */
@Dependent
@Repository
public abstract class ApplicantCertificateRepository 
        extends ApplicantInformationRepositoryBase<ApplicantCertificate, ApplicantCertificate> {
    
}
