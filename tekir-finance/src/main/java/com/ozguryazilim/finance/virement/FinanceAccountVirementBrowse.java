/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.virement;

import com.ozguryazilim.tekir.core.query.columns.TagColumn;
import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.tekir.entities.VirementType;
import com.ozguryazilim.tekir.entities.FinanceAccountVirement;
import com.ozguryazilim.tekir.entities.FinanceAccountVirement_;
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
import com.ozguryazilim.telve.query.columns.EnumColumn;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.MoneyColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import com.ozguryazilim.telve.query.columns.TextColumn;
import com.ozguryazilim.telve.query.columns.UserColumn;
import com.ozguryazilim.telve.query.filters.BigDecimalFilter;
import com.ozguryazilim.telve.query.filters.DateFilter;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.query.filters.EnumFilter;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringFilter;
import com.ozguryazilim.telve.query.filters.SubStringFilter;
import com.ozguryazilim.telve.query.filters.UserFilter;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature=FinanceAccountVirementFeature.class )
public class FinanceAccountVirementBrowse extends VoucherBrowseBase<FinanceAccountVirement, FinanceAccountVirementViewModel>{

    @Inject
    private FinanceAccountVirementRepository repository;
    
    @Inject
    private FinanceAccountVirementHome home;
    
    @Override
    protected void buildQueryDefinition(QueryDefinition<FinanceAccountVirement, FinanceAccountVirementViewModel> queryDefinition) {
        queryDefinition
                .addColumn(new DateColumn<>(VoucherBase_.date, "general.label.Date"), true)
                .addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true)
                .addColumn(new SubTextColumn<>(FinanceAccountVirement_.fromAccount, FinanceAccount_.name, "general.label.FromAccount"), true)
                .addColumn(new SubTextColumn<>(FinanceAccountVirement_.toAccount, FinanceAccount_.name, "general.label.ToAccount"), true)
                .addColumn(new TextColumn<>(VoucherBase_.info, "general.label.Info"), true)
                .addColumn(new TagColumn<>("tags", "general.label.Tag"), false)
                .addColumn(new TextColumn<>(VoucherBase_.referenceNo, "voucher.label.ReferenceNo"), false)
                .addColumn(new VoucherStateColumn<>( VoucherBase_.state, "general.label.State"), false)
                .addColumn(new TextColumn<>(VoucherBase_.stateReason, "voucher.label.StateReason"), false)
                .addColumn(new TextColumn<>(VoucherBase_.stateInfo, "voucher.label.StateInfo"), false)
                .addColumn(new UserColumn<>(VoucherBase_.owner, "voucher.label.Owner"), true)
                .addColumn(new MoneyColumn<>(FinanceAccountVirement_.fromAmount, FinanceAccountVirement_.fromCurrency, "general.label.FromAmount"), true)
                .addColumn(new MoneyColumn<>(FinanceAccountVirement_.toAmount, FinanceAccountVirement_.toCurrency, "general.label.ToAmount"), true)
                .addColumn(new EnumColumn<>(FinanceAccountVirement_.virementType, "finance.label.VirementType","virementtype."), true);

        queryDefinition
                .addFilter(new StringFilter<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"))
                .addFilter(new StringFilter<>(VoucherBase_.info, "voucher.label.Info"))
                .addFilter(new StringFilter<>(VoucherBase_.topic, "voucher.label.Topic"))
                .addFilter(new VoucherStateFilter<>(VoucherBase_.state, getHome().getStateConfig().getStates(), "general.label.State"))
                .addFilter(new VoucherStateTypeFilter<>(VoucherBase_.state, "voucher.label.StateType"))
                .addFilter(new StringFilter<>(VoucherBase_.stateReason, "voucher.label.StateReason"))
                .addFilter(new UserFilter<>(VoucherBase_.owner, "voucher.label.Owner"))
                .addFilter(new BigDecimalFilter<>(FinanceAccountVirement_.fromAmount, "general.label.FromAmount"))
                .addFilter(new BigDecimalFilter<>(FinanceAccountVirement_.toAmount, "general.label.ToAmount"))
                .addFilter(new SubStringFilter<>(FinanceAccountVirement_.fromAccount, FinanceAccount_.name, "general.label.FromAccount"))
                .addFilter(new SubStringFilter<>(FinanceAccountVirement_.toAccount, FinanceAccount_.name, "general.label.ToAccount"))
                .addFilter(new DateFilter<>(VoucherBase_.date, "voucher.label.Date", FilterOperand.In, DateValueType.LastTenDays))
                .addFilter(new EnumFilter<>(FinanceAccountVirement_.virementType, VirementType.VIREMENT, "finance.label.VirementType", "virementtype."))
;

    }

    @Override
    public VoucherRepositoryBase<FinanceAccountVirement, FinanceAccountVirementViewModel> getVoucherRepository() {
        return repository;
    }
    
    @Override
    public VoucherFormBase<FinanceAccountVirement> getHome() {
    	// TODO Auto-generated method stub
    	return home;
    }
    
}
