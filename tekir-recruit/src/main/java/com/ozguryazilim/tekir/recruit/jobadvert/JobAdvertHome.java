package com.ozguryazilim.tekir.recruit.jobadvert;

import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.NavigationParameterContext;

/**
 *
 * @author deniz
 */
@FormEdit( feature = JobAdvertFeature.class )
public class JobAdvertHome extends FormBase<JobAdvert, Long> {
    
    @Inject
    private NavigationParameterContext navigationParameterContext;

    @Inject
    private Identity identity;

    @Inject
    private JobAdvertRepository repository;

    @Override
    protected RepositoryBase<JobAdvert, JobAdvertViewModel> getRepository() {
        return repository;
    }

    public Class<? extends ViewConfig> newAdvert() {
        JobAdvert j = new JobAdvert();
        j.setOwner(identity.getLoginName());
        setEntity(j);
        navigationParameterContext.addPageParameter("eid", 0);
        return RecruitPages.JobAdvert.class;
    }

}
