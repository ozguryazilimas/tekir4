package com.ozguryazilim.tekir.recruit.jobadvert;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
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

}
