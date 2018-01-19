package com.ozguryazilim.tekir.commodity.category;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;
import com.ozguryazilim.tekir.entities.CommodityCategory;
import javax.inject.Inject;

/**
 * Home Control Class
 *
 * @author
 */
@ParamEdit
@AutoCode(caption = "module.caption.CommodityCategory", serial = "COMC")
public class CommodityCategoryHome extends TreeBase<CommodityCategory> {

    @Inject
    private CommodityCategoryRepository repository;

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

        getEntity().setCode(codeService.getNewSerialNumber(CommodityCategoryHome.class.getSimpleName(), parentCode));
    }

    @Override
    public void createNewSibling() {
        //Önce gereken kurallar ile sınıf oluşsun
        super.createNewSibling();
        String parentCode = null;

        if (getEntity().getParent() != null) {
            parentCode = getEntity().getParent().getCode();
        }

        getEntity().setCode(codeService.getNewSerialNumber(CommodityCategoryHome.class.getSimpleName(), parentCode));
    }

    @Override
    public CommodityCategoryRepository getRepository() {
        return this.repository;
    }

}
