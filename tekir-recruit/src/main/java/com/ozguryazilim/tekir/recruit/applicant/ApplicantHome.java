package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.contact.information.ContactInformationRepository;
import com.ozguryazilim.tekir.entities.ContactInformation;
import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.Gender;
import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.tekir.recruit.jobapplication.JobApplicationRepository;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import java.util.List;
import javax.inject.Inject;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 *
 * @author deniz
 */
@FormEdit(feature = ApplicantFeature.class)
@AutoCode(cosumer = "Applicant", caption = "module.caption.Applicant", serial = "APP")
public class ApplicantHome extends FormBase<Applicant, Long> {

    @Inject
    private ApplicantRepository repository;
    
    @Inject
    private JobApplicationRepository jobApplicationRepository;
    
    @Inject
    private ContactInformationRepository informationRepository;

    @Inject
    private AutoCodeService codeService;
    
    @Inject
    private Identity identity;
    
    private Integer age;

    @Override
    protected RepositoryBase<Applicant, ApplicantViewModel> getRepository() {
        return repository;
    }

    @Override
    public void createNew() {
        super.createNew(); 
        getEntity().setOwner(identity.getLoginName());
        getEntity().setCode(codeService.getNewSerialNumber(Applicant.class.getSimpleName()));
    }

    @Override
    public boolean onBeforeSave() {
        getEntity().setName(getEntity().getFirstName() + " " + getEntity().getLastName());

        return super.onBeforeSave();
    }
        
    /**
     * Yüklendikten sonra age değişkenini boşaltıyoruz
     *
     *
     * @return
     */
    @Override
    public boolean onAfterLoad() {
        if (age != null) {
            age = null;
        }
        return true;
    }

    /**
     * Değişiklik yapıldıktan sonra age değişkenini boşaltıyoruz.
     *
     * @return
     */
    @Override
    public boolean onAfterSave() {
        if (age != null) {
            age = null;
        }
        return true;
    }
    
    /**
     * Yaş bilgisini tekrar edilmemesi için global bir değişkende saklayarak
     * dönüyoruz.
     *
     * @return
     */
    public Integer getAge() {
        if (age == null) {
            if (getEntity().getBirthDate() != null) {
                LocalDate birthdate = new LocalDate(getEntity().getBirthDate());
                LocalDate now = new LocalDate();
                Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
                age = period.getYears();
            }
        }
        return age;
    }

    /**
     * Geriye ilgili Başvuran Adayına ait iletişim bilgilerini döndürür.
     *
     * @return
     */
    public List<ContactInformation> getContactInformations() {
        return informationRepository.findByContact(getEntity());
    }
    
    /**
     * Geriye ilgili Başvuran Adayına ait iş başvurularını döndürür.
     *
     * @return
     */
    public List<JobApplication> getJobApplications() {
        return jobApplicationRepository.findByApplicant(getEntity());
    }
    
    /**
     * NoteWidget için gerekli.
     *
     * @return
     */
    public FeaturePointer getFeaturePointer() {
        FeaturePointer result = new FeaturePointer();
        result.setBusinessKey(getEntity().getCode());
        result.setFeature(getFeatureClass().getSimpleName());
        result.setPrimaryKey(getEntity().getId());
        return result;
    }
    
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase entityBase) {
        return FeatureUtils.getFeaturePointer(entityBase);
    }

    public Boolean isMale(Applicant applicant) {
        return applicant.getGender().equals(Gender.MALE);
    }
  
}
