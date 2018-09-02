package com.ozguryazilim.tekir.recruit.jobadvert;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.tekir.entities.JobAdvert;
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

/**
 *
 * @author deniz
 */
@FormEdit( feature = JobAdvertFeature.class )
@AutoCode(cosumer = "JobAdvert", caption = "module.caption.JobAdvert", serial = "JA", size = 4)
public class JobAdvertHome extends FormBase<JobAdvert, Long> {
    
    @Inject
    private Identity identity;

    @Inject
    private JobAdvertRepository repository;
    
    @Inject
    private JobApplicationRepository jobApplicationRepository;
    
    @Inject
    private AutoCodeService codeService;

    @Override
    protected RepositoryBase<JobAdvert, JobAdvertViewModel> getRepository() {
        return repository;
    }
    
    @Override
    public void createNew() {
        super.createNew(); 
        getEntity().setOwner(identity.getLoginName());
        getEntity().setSerial(codeService.getNewSerialNumber(JobAdvert.class.getSimpleName()));
    }

    /**
     * Geriye ilgili İş İlanına'a ait iş başvurularını döndürür.
     *
     * @return
     */
    public List<JobApplication> getJobApplications() {
        return jobApplicationRepository.findByJobAdvert(getEntity());
    }
    
    /**
     * NoteWidget için gerekli.
     *
     * @return
     */
    public FeaturePointer getFeaturePointer() {
        FeaturePointer result = new FeaturePointer();
        result.setBusinessKey(getEntity().getSerial());
        result.setFeature(getFeatureClass().getSimpleName());
        result.setPrimaryKey(getEntity().getId());
        return result;
    }
    
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase entityBase) {
        return FeatureUtils.getFeaturePointer(entityBase);
    }
    
}
