/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account;

import com.ozguryazilim.finance.config.FinancePages;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Lookup(dialogPage = FinancePages.FinanceAccountLookup.class)
public class FinanceAccountLookup extends
        LookupTableControllerBase<FinanceAccount, FinanceAccount> {

    @Inject
    private FinanceAccountRepository repository;

    @Override
    public void buildModel(LookupTableModel<FinanceAccount> model) {
        model.addColumn("code", "general.label.Code");
        model.addColumn("name", "general.label.Name");
    }

    @Override
    protected RepositoryBase<FinanceAccount, FinanceAccount> getRepository() {
        return repository;
    }

    @Override
    public String getCaptionFieldName() {
        return FinanceAccount_.name.getName();
    }
}
