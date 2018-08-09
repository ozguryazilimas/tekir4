package com.ozguryazilim.tekir.recruit.jobadvert;

import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;

/**
 *
 * @author deniz
 */
@FormEdit( feature = JobAdvertFeature.class )
public class JobAdvertHome extends FormBase<JobAdvert, Long> {
    
    @Inject
    private JobAdvertRepository repository;

    @Override
    protected RepositoryBase<JobAdvert, JobAdvertViewModel> getRepository() {
        return repository;
    }
      
}
