package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.recruit.config.RecruitPages;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.NavigationParameterContext;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;

/**
 *
 * @author deniz
 */
@FormEdit(feature = ApplicantFeature.class)
public class ApplicantHome extends FormBase<Applicant, Long> {

    @Inject
    private ApplicantRepository repository;

    @Inject
    private Identity identity;

    @Inject
    private NavigationParameterContext navigationParameterContext;

    @Inject
    private ViewNavigationHandler viewNavigationHandler;

    @Override
    protected RepositoryBase<Applicant, ApplicantViewModel> getRepository() {
        return repository;
    }

    public Class<? extends ViewConfig> newApplicant() {
        Applicant applicant = new Applicant();

        applicant.setOwner(identity.getLoginName());
        setEntity(applicant);

        navigationParameterContext.addPageParameter("eid", 0);

        return RecruitPages.applicant.Applicant.class;
    }

    @Override
    public boolean onBeforeSave() {
        //Eğer person ise name alanını düzeltmek lazım
        getEntity().setName(getEntity().getFirstName() + " " + getEntity().getLastName());

        return super.onBeforeSave();
    }

    @Override
    public boolean onAfterLoad() {

        //Burası yetki eklenince gerçekleştirilmeli
        /*
        if (!identity.isPermitted("applicant:select:" + getEntity().getOwner())) {
            FacesMessages.error("facesMessages.error.NoPermission");
            createNew();
            viewNavigationHandler.navigateTo(RecruitPages.applicant.ApplicantBrowse.class);
            return false;
        }
         */
        return super.onAfterLoad();
    }

}
