/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.virement;

import com.ozguryazilim.tekir.core.query.columns.TagColumn;
import com.ozguryazilim.tekir.core.query.filter.TagFilter;
import com.ozguryazilim.tekir.entities.AccountVirement;
import com.ozguryazilim.tekir.entities.AccountVirement_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.tekir.voucher.columns.VoucherStateColumn;
import com.ozguryazilim.tekir.voucher.filter.VoucherStateFilter;
import com.ozguryazilim.tekir.voucher.filter.VoucherStateTypeFilter;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.DateColumn;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.MoneyColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.columns.UserColumn;
import com.ozguryazilim.telve.query.filters.BigDecimalFilter;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringFilter;
import com.ozguryazilim.telve.query.filters.SubStringFilter;
import com.ozguryazilim.telve.query.filters.UserFilter;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature=AccountVirementFeature.class )
public class AccountVirementBrowse extends VoucherBrowseBase<AccountVirement, AccountVirementViewModel>{

    @Inject
    private AccountVirementRepository repository;
    
    @Inject
    private AccountVirementHome home;
    
    @Override
    protected void buildQueryDefinition(QueryDefinition<AccountVirement, AccountVirementViewModel> queryDefinition) {
        queryDefinition
                .addColumn(new DateColumn<>(VoucherBase_.date, "general.label.Date"), true)
                .addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true)
                .addColumn(new TextColumn<>(VoucherBase_.topic, "voucher.label.Topic"), true)
                .addColumn(new SubTextColumn<>(AccountVirement_.fromAccount, Contact_.name, "general.label.FromAccount"), true)
                .addColumn(new SubTextColumn<>(AccountVirement_.toAccount, Contact_.name, "general.label.ToAccount"), true)
                .addColumn(new TextColumn<>(VoucherBase_.info, "general.label.Info"), true)
                .addColumn(new TagColumn<>("tags", "general.label.Tag"), false)
                .addColumn(new VoucherStateColumn<>( VoucherBase_.state, "general.label.State"), false)
                .addColumn(new TextColumn<>(VoucherBase_.referenceNo, "voucher.label.ReferenceNo"), false)
                .addColumn(new TextColumn<>(VoucherBase_.stateReason, "voucher.label.StateReason"), false)
                .addColumn(new TextColumn<>(VoucherBase_.stateInfo, "voucher.label.StateInfo"), false)
                .addColumn(new UserColumn<>(VoucherBase_.owner, "voucher.label.Owner"), true)
                .addColumn(new MoneyColumn<>(AccountVirement_.amount, AccountVirement_.currency, "general.label.Amount"), true);
                
        queryDefinition
                .addFilter(new StringFilter<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"))
                .addFilter(new TagFilter<>("tags", "general.label.Tag","AccountVirement"))
                .addFilter(new StringFilter<>(VoucherBase_.info, "voucher.label.Info"))
                .addFilter(new StringFilter<>(VoucherBase_.topic, "voucher.label.Topic"))
                .addFilter(new VoucherStateFilter<>(VoucherBase_.state, getHome().getStateConfig().getStates(), "general.label.State"))
                .addFilter(new VoucherStateTypeFilter<>(VoucherBase_.state, "voucher.label.StateType"))
                .addFilter(new StringFilter<>(VoucherBase_.stateReason, "voucher.label.StateReason"))
                .addFilter(new UserFilter<>(VoucherBase_.owner, "voucher.label.Owner"))
                .addFilter(new BigDecimalFilter<>(AccountVirement_.amount, "general.label.Amount"))
                .addFilter(new SubStringFilter<>(AccountVirement_.fromAccount, Contact_.name, "general.label.FromAccount"))
                .addFilter(new SubStringFilter<>(AccountVirement_.toAccount, Contact_.name, "general.label.ToAccount"))
                .addFilter(new DateFilter<>(VoucherBase_.date, "voucher.label.Date", FilterOperand.In, DateValueType.LastTenDays));
                
    }

    @Override
    public VoucherRepositoryBase<AccountVirement, AccountVirementViewModel> getVoucherRepository() {
        return repository;
    }
    
    @Override
    public VoucherFormBase<AccountVirement> getHome() {
    	// TODO Auto-generated method stub
    	return home;
    }
    
}
