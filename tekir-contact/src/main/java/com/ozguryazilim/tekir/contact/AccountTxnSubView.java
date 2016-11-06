/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.AccountTxn_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.SubView;
import com.ozguryazilim.telve.forms.SubViewQueryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.TextColumn;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@SubView(containerPage = ContactPages.ContactView.class, viewPage = ContactPages.AccountTxnSubView.class, permission = "accountTxn", order = 42)
public class AccountTxnSubView extends SubViewQueryBase<AccountTxn, AccountTxn>{

    @Inject
    private ContactHome contactHome;
    
    @Inject
    private AccountTxnRepository repository;
    
    @Override
    protected void buildQueryDefinition(QueryDefinition<AccountTxn, AccountTxn> queryDefinition) {
        queryDefinition
                //.addColumn(new SubTextColumn<>(RelatedContact_.sourceContact, Contact_.name, "contact.label.Source"), true)
                .addColumn(new TextColumn<>(AccountTxn_.info, "general.label.Info"), true)
                .addColumn(new TextColumn<>(AccountTxn_.owner, "general.label.Info"), true)
                .addColumn(new TextColumn<>(AccountTxn_.status, "general.label.Info"), true);
    }

    @Override
    protected RepositoryBase<AccountTxn, AccountTxn> getRepository() {
        repository.setAccount(contactHome.getEntity());
        return repository;
    }
    
}
