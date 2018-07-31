package com.ozguryazilim.tekir.recruit;

import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;

/**
 *
 * @author deniz
 */
@FormEdit( feature = RecruitFeature.class )
public class RecruitHome extends FormBase<JobAdvert, Long> {
    
    @Inject
    private RecruitRepository repository;

    @Override
    protected RepositoryBase<JobAdvert, RecruitViewModel> getRepository() {
        return repository;
    }
      
}
