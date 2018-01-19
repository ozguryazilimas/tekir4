package com.ozguryazilim.tekir.core.territory;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.tekir.entities.Territory;
import javax.inject.Inject;

/**
 * Home Control Class
 *
 * @author
 */
@ParamEdit
@AutoCode(caption = "module.caption.Territory", size = 3)
public class TerritoryHome extends ParamBase<Territory, Long> {

    @Inject
    private TerritoryRepository repository;

    @Inject
    private AutoCodeService codeService;

    @Override
    protected Territory getNewEntity() {

        Territory result = new Territory();
        result.setCode(codeService.getNewSerialNumber(TerritoryHome.class.getSimpleName()));

        return result;
    }

    @Override
    public TerritoryRepository getRepository() {
        return this.repository;
    }

}
