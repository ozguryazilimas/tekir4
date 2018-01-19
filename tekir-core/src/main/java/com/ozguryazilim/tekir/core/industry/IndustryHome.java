package com.ozguryazilim.tekir.core.industry;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;
import com.ozguryazilim.tekir.entities.Industry;
import javax.inject.Inject;

/**
 * Home Control Class
 *
 * @author
 */
@ParamEdit
@AutoCode(caption = "module.caption.Industry", size = 3)
public class IndustryHome extends TreeBase<Industry> {

    @Inject
    private IndustryRepository repository;

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

        getEntity().setCode(codeService.getNewSerialNumber(IndustryHome.class.getSimpleName(), parentCode));
    }

    @Override
    public void createNewSibling() {
        //Önce gereken kurallar ile sınıf oluşsun
        super.createNewSibling();
        String parentCode = null;

        if (getEntity().getParent() != null) {
            parentCode = getEntity().getParent().getCode();
        }

        getEntity().setCode(codeService.getNewSerialNumber(IndustryHome.class.getSimpleName(), parentCode));
    }

    @Override
    public IndustryRepository getRepository() {
        return this.repository;
    }

}
