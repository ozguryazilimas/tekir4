/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.debit;

import com.ozguryazilim.tekir.account.config.AccountNotePages;
import com.ozguryazilim.tekir.entities.AccountDebitNote;
import com.ozguryazilim.tekir.entities.AccountDebitNote_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.tekir.voucher.columns.VoucherStateColumn;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.MoneyColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.StringFilter;
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
                .addColumn(new VoucherStateColumn<>( VoucherBase_.state, "general.label.State"), true)
                .addColumn(new DateColumn<>(VoucherBase_.date, "general.label.Date"), true)
                .addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true)
                .addColumn(new SubTextColumn<>(AccountDebitNote_.account, Contact_.name, "general.label.Account"), true)
                .addColumn(new TextColumn<>(VoucherBase_.info, "general.label.Info"), true)
                .addColumn(new TextColumn<>(VoucherBase_.code, "general.label.Code"), false)
                .addColumn(new TextColumn<>(VoucherBase_.referenceNo, "general.label.ReferanceNo"), false)
                //.addColumn(new TextColumn<>(VoucherBase_.stateReason, "general.label.StateReason"), true)
                //.addColumn(new TextColumn<>(VoucherBase_.stateInfo, "general.label.StateInfo"), false)
                .addColumn(new MoneyColumn<>(AccountDebitNote_.amount, AccountDebitNote_.currency, "general.label.Amount"), true);
                
        queryDefinition
                .addFilter(new StringFilter<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"))
                .addFilter(new StringFilter<>(VoucherBase_.code, "voucher.label.Code"))
                .addFilter(new StringFilter<>(VoucherBase_.info, "voucher.label.Info"))
                .addFilter(new DateFilter<>(VoucherBase_.date, "voucher.label.Date"));
                
    }

    @Override
    protected RepositoryBase<AccountDebitNote, AccountDebitNoteViewModel> getRepository() {
        return repository;
    }
    
}
