package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.contact.information.ContactInformationRepository;
import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.ContactInformation;
import com.ozguryazilim.tekir.entities.JobApplication;

import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;

import java.util.List;
import javax.inject.Inject;

/**
 * Repository sınıfları ile ön taraf arasında köprü görevi yapıyor.
 * 
 * @author serdar
 */
@FormEdit(feature = ApplicantFeature.class)
public class ApplicantHome extends FormBase<Applicant, Long> {

    @Inject
    private ApplicantRepository repository;

    @Inject
    private JobApplicantRepository jobApplicantRepository;
    
    @Inject
    private ContactInformationRepository informationRepository;
        
    @Override
    protected RepositoryBase<Applicant, ApplicantViewModel> getRepository() {
        return repository;
    }

    
    /**
     * Adayın iletişim bilgilerini ilgili Repository sınıfından aldık.
     *
     * @return
     */
    public List<ContactInformation> getContactInformations() {
        return informationRepository.findByContact(getEntity());
    }
    
    /**
     * Adayın başvurduğu tüm ilanları aldık.
     *
     * @return
     */
    public List<JobApplication> getJobApplication() {
        return jobApplicantRepository.findByApplicant(getEntity());
    }

    /**
     * NoteWidget için gerekli.
     *
     * @return
     */
    public FeaturePointer getFeaturePointer() {
        FeaturePointer result = new FeaturePointer();
        result.setBusinessKey(getEntity().getName());
        result.setFeature(getFeatureClass().getSimpleName());
        result.setPrimaryKey(getEntity().getId());
        return result;
    }
    
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase entityBase) {
        return FeatureUtils.getFeaturePointer(entityBase);
    }

}
