package com.ozguryazilim.tekir.voucher.process;

import com.ozguryazilim.tekir.entities.Process_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.forms.BrowseBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.ProcessStatus;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.Process_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
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
@Browse( feature=ProcessFeature.class )
public class ProcessBrowse extends BrowseBase<Process, Process> {

    @Inject
    private ProcessRepository repository;

    @Override
    protected void buildQueryDefinition(QueryDefinition<Process, Process> queryDefinition) {
        queryDefinition
                .addColumn(new TextColumn<>(Process_.topic, "voucher.label.Topic"), true)
                .addColumn(new TextColumn<>(Process_.processNo, "voucher.label.Date"), true)
                .addColumn(new EnumColumn<>(Process_.type, "general.label.Type", "processType."), true)
                .addColumn(new EnumColumn<>(Process_.status, "general.label.Status", "processStatus."), true)
                .addColumn(new SubTextColumn<>(Process_.account,Contact_.name, "voucher.label.Account"), true);
                
        
        queryDefinition
		        .addFilter(new StringFilter<>(Process_.topic, "voucher.label.Topic"))
		        .addFilter(new StringFilter<>(Process_.processNo, "voucher.label.Date"))
		        .addFilter(new EnumFilter<>(Process_.type, ProcessType.SALES,"general.label.Type", "processType."))
		        .addFilter(new EnumFilter<>(Process_.status, ProcessStatus.OPEN,"general.label.Status", "processStatus."))
		        .addFilter(new SubStringFilter<>(Process_.account,Contact_.name, "voucher.label.Account"));
    }

    @Override
    public RepositoryBase<Process, Process> getRepository() {
        return repository;
    }	
}
