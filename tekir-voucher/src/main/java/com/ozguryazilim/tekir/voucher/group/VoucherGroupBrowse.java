package com.ozguryazilim.tekir.voucher.group;

import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.tekir.entities.VoucherGroup_;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroupStatus;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.tekir.voucher.columns.VoucherStateColumn;
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
 * Brwose Control Class
 *
 * @author
 */
@Browse( feature=VoucherGroupFeature.class )
public class VoucherGroupBrowse extends BrowseBase<VoucherGroup, VoucherGroup> {

    @Inject
    private VoucherGroupRepository repository;

    @Override
    protected void buildQueryDefinition(QueryDefinition<VoucherGroup, VoucherGroup> queryDefinition) {
        queryDefinition
                .addColumn(new TextColumn<>(VoucherGroup_.topic, "voucher.label.Topic"), true)
                .addColumn(new LinkColumn<>(VoucherGroup_.groupNo, "voucher.label.GroupNo"), true)
                .addColumn(new EnumColumn<>(VoucherGroup_.status, "general.label.Status", "voucher.groupStatus."), true);
                
        
        queryDefinition
		        .addFilter(new StringFilter<>(VoucherGroup_.topic, "voucher.label.Topic"))
                        .addFilter(new StringFilter<>(VoucherGroup_.groupNo, "voucher.label.GroupNo"))
		        .addFilter(new EnumFilter<>(VoucherGroup_.status, VoucherGroupStatus.ACTIVE,"general.label.Status", "voucher.groupStatus."));
    }

    @Override
    public RepositoryBase<VoucherGroup, VoucherGroup> getRepository() {
        return repository;
    }	
}
