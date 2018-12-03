package com.ozguryazilim.tekir.recruit.applicant.information;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.ViewModel;
import java.util.List;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 * ApplicantInformationEditor modelleri için repository tanımları.
 * 
 * Editör modellerinin repository'leri bu base'i miras almalı.
 * 
 * @author Erdem Uslu
 */
public abstract class ApplicantInformationRepositoryBase<E extends EntityBase, R extends ViewModel>
        extends RepositoryBase<E, R>
        implements CriteriaSupport<E> {

    /**
     * İlgili applicant'a ait tanımlanan information modelin entitylerini döndürür.
     * 
     * @param applicant
     * @return 
     */
    public abstract List<E> findByApplicant(Applicant applicant);

    /**
     * İlgili applicant'a ait tanımlanan information modelin entitylerini siler.
     * 
     * @param applicant 
     */
    public abstract void deleteByApplicant(Applicant applicant);
    
}
