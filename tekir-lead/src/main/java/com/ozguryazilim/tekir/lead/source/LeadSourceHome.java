package com.ozguryazilim.tekir.lead.source;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.LeadSource;
import com.ozguryazilim.telve.data.TreeRepositoryBase;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.TreeBase;

@ParamEdit
@AutoCode(caption = "module.caption.LeadSource", serial = "LES")
public class LeadSourceHome extends TreeBase<LeadSource> {

    @Inject
    private LeadSourceRepository repository;

    @Inject
    private AutoCodeService codeService;

    @Override
    protected LeadSource getNewEntity() {

        LeadSource result = new LeadSource();
        result.setCode(codeService.getNewSerialNumber(LeadSourceHome.class.getSimpleName()));

        return result;
    }

    @Override
    protected TreeRepositoryBase<LeadSource> getRepository() {
        return repository;
    }

}
