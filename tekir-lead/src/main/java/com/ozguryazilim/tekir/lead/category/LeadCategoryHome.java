package com.ozguryazilim.tekir.lead.category;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.LeadCategory;
import com.ozguryazilim.telve.data.TreeRepositoryBase;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;

@ParamEdit
@AutoCode(caption = "module.caption.LeadCategory", serial = "LEC", size = 2)
public class LeadCategoryHome extends TreeBase<LeadCategory> {

    @Inject
    private LeadCategoryRepository repository;

    @Inject
    private AutoCodeService codeService;

    @Override
    public void createNewChild() {
        //Önce gereken kurallar ile sınıf oluşsun.
        super.createNewChild();

        String parentCode = null;

        if (getEntity().getParent() != null) {
            parentCode = getEntity().getParent().getCode();
        }

        getEntity().setCode(codeService.getNewSerialNumber(LeadCategoryHome.class.getSimpleName(), parentCode));
    }

    @Override
    public void createNewSibling() {
        //Önce gereken kurallar ile sınıf oluşsun
        super.createNewSibling();
        String parentCode = null;

        if (getEntity().getParent() != null) {
            parentCode = getEntity().getParent().getCode();
        }

        getEntity().setCode(codeService.getNewSerialNumber(LeadCategoryHome.class.getSimpleName(), parentCode));
    }

    @Override
    protected TreeRepositoryBase<LeadCategory> getRepository() {
        return repository;
    }

}
