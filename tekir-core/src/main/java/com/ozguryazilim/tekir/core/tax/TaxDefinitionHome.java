package com.ozguryazilim.tekir.core.tax;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.tekir.entities.TaxDefinition;
import javax.inject.Inject;

/**
 * Home Control Class
 *
 * @author
 */
@ParamEdit
@AutoCode(caption = "module.caption.TaxDefinition", size = 3)
public class TaxDefinitionHome extends ParamBase<TaxDefinition, Long> {

    @Inject
    private TaxDefinitionRepository repository;
    
    @Inject
    private AutoCodeService codeService;

    @Override
    protected TaxDefinition getNewEntity() {
        
        TaxDefinition result = new TaxDefinition();
        result.setCode(codeService.getNewSerialNumber(TaxDefinitionHome.class.getSimpleName()));
        
        return result;
    }

    

    @Override
    public TaxDefinitionRepository getRepository() {
        return this.repository;
    }

}
