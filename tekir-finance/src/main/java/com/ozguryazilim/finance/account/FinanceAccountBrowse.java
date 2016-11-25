/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account;

import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature = FinanceAccountFeature.class)
public class FinanceAccountBrowse extends BrowseBase<FinanceAccount, FinanceAccount> {

    @Inject
    private FinanceAccountRepository repository;
    
    @Override
    protected void buildQueryDefinition(QueryDefinition<FinanceAccount, FinanceAccount> queryDefinition) {
        queryDefinition
                    .addColumn(new LinkColumn<>(FinanceAccount_.code, "general.label.Code"), true)
                    .addColumn(new LinkColumn<>(FinanceAccount_.name, "general.label.Name"), true);
                    //.addColumn(new TextColumn<>(FinanceAccount_.info, "general.label.Info"), true);
    }

    @Override
    protected RepositoryBase<FinanceAccount, FinanceAccount> getRepository() {
        return repository;
    }
    
}
