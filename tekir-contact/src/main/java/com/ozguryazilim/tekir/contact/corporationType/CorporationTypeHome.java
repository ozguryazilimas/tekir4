package com.ozguryazilim.tekir.contact.corporationType;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.tekir.entities.CorporationType;
import javax.inject.Inject;

/**
 * Home Control Class
 *
 * @author
 */
@ParamEdit
@AutoCode(caption = "module.caption.CorporationType", serial = "CORPT", size=2 )
public class CorporationTypeHome extends ParamBase<CorporationType, Long> {

    @Inject
    private CorporationTypeRepository repository;

    @Inject
    private AutoCodeService codeService;

    @Override
    protected CorporationType getNewEntity() {

        CorporationType result = new CorporationType();
        result.setCode(codeService.getNewSerialNumber(CorporationTypeHome.class.getSimpleName()));

        return result;
    }

    @Override
    public CorporationTypeRepository getRepository() {
        return this.repository;
    }

}
