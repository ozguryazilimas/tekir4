package com.ozguryazilim.tekir.recruit.applicant;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;

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
    private AutoCodeService codeService;
    
    @Inject
    private Identity identity;

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
  
}
