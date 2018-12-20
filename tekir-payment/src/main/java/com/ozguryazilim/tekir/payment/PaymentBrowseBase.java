package com.ozguryazilim.tekir.payment;

import com.ozguryazilim.tekir.core.query.columns.TagColumn;
import com.ozguryazilim.tekir.core.query.filter.TagFilter;
import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.PaymentBase;
import com.ozguryazilim.tekir.entities.PaymentBase_;
import com.ozguryazilim.tekir.entities.Process_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.VoucherProcessBase_;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.tekir.voucher.columns.VoucherStateColumn;
import com.ozguryazilim.tekir.voucher.filter.VoucherStateFilter;
import com.ozguryazilim.tekir.voucher.filter.VoucherStateTypeFilter;
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

/**
 *
 * @author oyas
 */
public abstract class PaymentBrowseBase<E extends PaymentBase, V extends PaymentViewModel> extends VoucherBrowseBase<E, V>{
    
    @Override
    protected void buildQueryDefinition(QueryDefinition<E, V> queryDefinition) {
        queryDefinition
                .addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true)
                .addColumn(new SubTextColumn<>(VoucherProcessBase_.account, Contact_.name, "general.label.Account"), true)
                .addColumn(new SubTextColumn<>(PaymentBase_.financeAccount, FinanceAccount_.name, "general.label.FinanceAccount"), true)
                .addColumn(new TextColumn<>(VoucherProcessBase_.topic, "voucher.label.Topic"), true)
                .addColumn(new DateColumn<>(VoucherBase_.date, "voucher.label.Date"), true)
                .addColumn(new MoneyColumn<>(PaymentBase_.amount, PaymentBase_.currency, "general.label.Total"), true)
                .addColumn(new UserColumn<>(VoucherBase_.owner, "voucher.label.Owner"), true)
                .addColumn(new TextColumn<>(VoucherBase_.referenceNo, "voucher.label.ReferenceNo"), false)
                .addColumn(new TagColumn<>("tags", "general.label.Tag"), false)
                .addColumn(new TextColumn<>(VoucherBase_.info, "voucher.label.Info"), false)
                .addColumn(new TextColumn<>(VoucherBase_.stateReason, "voucher.label.StateReason"), false)
                .addColumn(new TextColumn<>(VoucherBase_.stateInfo, "voucher.label.StateInfo"), false)
                .addColumn(new VoucherStateColumn<>( VoucherBase_.state, "general.label.State"), false)
                .addColumn(new SubTextColumn<>(VoucherProcessBase_.process, Process_.processNo, "voucher.label.Process"), false);
        
        queryDefinition
                .addFilter(new StringFilter<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"))
                .addFilter(new TagFilter<>("tags", "general.label.Tag","Payment"))
                .addFilter(new StringFilter<>(VoucherBase_.info, "voucher.label.Info"))
                .addFilter(new StringFilter<>(VoucherBase_.topic, "voucher.label.Topic"))
                .addFilter(new VoucherStateFilter<>(VoucherBase_.state, getHome().getStateConfig().getStates(), "general.label.State"))
                .addFilter(new VoucherStateTypeFilter<>(VoucherBase_.state, "voucher.label.StateType"))
                .addFilter(new StringFilter<>(VoucherBase_.stateReason, "voucher.label.StateReason"))
                .addFilter(new UserFilter<>(VoucherBase_.owner, "voucher.label.Owner"))
                .addFilter(new BigDecimalFilter<>(PaymentBase_.amount, "general.label.Total"))
                .addFilter(new SubStringFilter<>(VoucherProcessBase_.account, Contact_.name, "general.label.Account"))
                .addFilter(new SubStringFilter<>(PaymentBase_.financeAccount, FinanceAccount_.name, "general.label.FinanceAccount"))
                .addFilter(new SubStringFilter<>(VoucherProcessBase_.process, Process_.processNo, "voucher.label.Process"))
                .addFilter(new DateFilter<>(VoucherBase_.date, "voucher.label.Date", FilterOperand.In, DateValueType.LastTenDays));
    }
}
