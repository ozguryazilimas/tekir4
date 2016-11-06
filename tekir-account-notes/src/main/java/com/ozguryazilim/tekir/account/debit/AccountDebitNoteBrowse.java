/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.debit;

import com.ozguryazilim.tekir.account.config.AccountNotePages;
import com.ozguryazilim.tekir.entities.AccountDebitNote;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse(browsePage = AccountNotePages.AccountDebitNoteBrowse.class, editPage = AccountNotePages.AccountDebitNote.class, viewContainerPage = AccountNotePages.AccountDebitNoteView.class)
public class AccountDebitNoteBrowse extends VoucherBrowseBase<AccountDebitNote, AccountDebitNoteViewModel>{

    @Inject
    private AccountDebitNoteRepository repository;
    
    @Override
    protected void buildQueryDefinition(QueryDefinition<AccountDebitNote, AccountDebitNoteViewModel> queryDefinition) {
        queryDefinition
                .addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true)
                .addColumn(new TextColumn<>(VoucherBase_.info, "opportunity.label.Topic"), true);
                
    }

    @Override
    protected RepositoryBase<AccountDebitNote, AccountDebitNoteViewModel> getRepository() {
        return repository;
    }
    
}
