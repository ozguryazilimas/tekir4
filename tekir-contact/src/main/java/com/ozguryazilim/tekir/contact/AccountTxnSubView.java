/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.core.query.columns.TagColumn;
import com.ozguryazilim.tekir.core.query.filter.TagFilter;
import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.AccountTxn_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.SubView;
import com.ozguryazilim.telve.forms.SubViewQueryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.BooleanColumn;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.FeatureColumn;
import com.ozguryazilim.telve.query.columns.MessageColumn;
import com.ozguryazilim.telve.query.columns.MoneyColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.columns.UserColumn;
import com.ozguryazilim.telve.query.filters.BigDecimalFilter;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringFilter;
import com.ozguryazilim.telve.query.filters.UserFilter;
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
                .addColumn(new FeatureColumn<>(AccountTxn_.feature, "general.label.Feature"), true)
                .addColumn(new DateColumn<>(AccountTxn_.date, "general.label.Date"), true)
                .addColumn(new TextColumn<>(AccountTxn_.info, "general.label.Info"), true)
                .addColumn(new TextColumn<>(AccountTxn_.topic, "voucher.label.Topic"), true)
                .addColumn(new BooleanColumn<>(AccountTxn_.debit, "general.label.DebitCredit", "booleanValue.DebitCredit."), true)
                .addColumn(new MoneyColumn<>(AccountTxn_.amount, AccountTxn_.currency, "general.label.Money"), true)
                .addColumn(new TagColumn<>("tags", "general.label.Tag"), false)
                .addColumn(new TextColumn<>(AccountTxn_.processId, "general.label.Process"), false)
                .addColumn(new TextColumn<>(AccountTxn_.referenceNo, "general.label.ReferenceNo"), false)
                .addColumn(new MessageColumn<>(AccountTxn_.status, "general.label.Status", "voucherState.name."), true)
                .addColumn(new TextColumn<>(AccountTxn_.statusReason, "general.label.StatusReason"), false)
                .addColumn(new UserColumn<>(AccountTxn_.owner, "general.label.Owner"), false);
        
        queryDefinition
                .addFilter(new UserFilter<>(AccountTxn_.owner, "general.label.Owner"))
                .addFilter(new TagFilter<>("tags", "general.label.Tag","*"))
                .addFilter(new StringFilter<>(AccountTxn_.info, "voucher.label.Info"))
                .addFilter(new StringFilter<>(AccountTxn_.topic, "voucher.label.Topic"))
                //.addFilter(new StringFilter<>(AccountTxn_.stateReason, "voucher.label.StateReason"))
                
                .addFilter(new BigDecimalFilter<>(AccountTxn_.amount, "general.label.Total"))
                //.addFilter(new SubStringFilter<>(VoucherProcessBase_.account, Contact_.name, "general.label.Account"))
                //.addFilter(new SubStringFilter<>(VoucherProcessBase_.process, Process_.processNo, "voucher.label.Process"))
                .addFilter(new DateFilter<>(AccountTxn_.date, "voucher.label.Date", FilterOperand.In, DateValueType.LastTenDays));
        
    }

    @Override
    protected RepositoryBase<AccountTxn, AccountTxn> getRepository() {
        repository.setAccount(contactHome.getEntity());
        return repository;
    }
    
}
