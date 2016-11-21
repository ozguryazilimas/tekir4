/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.bank;

import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.tekir.entities.BankCashAccount;
import com.ozguryazilim.tekir.entities.BankCashAccount_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Lookup(dialogPage = CorePages.BankCashAccountLookup.class)
public class BankCashAccountLookup extends
        LookupTableControllerBase<BankCashAccount, BankCashAccountViewModel> {

    @Inject
    private BankCashAccountRepository repository;

    @Override
    public void buildModel(LookupTableModel<BankCashAccountViewModel> model) {
        model.addColumn("code", "general.label.Code");
        model.addColumn("name", "general.label.Name");
    }

    @Override
    protected RepositoryBase<BankCashAccount, BankCashAccountViewModel> getRepository() {
        return repository;
    }

    @Override
    public String getCaptionFieldName() {
        return BankCashAccount_.name.getName();
    }
}
