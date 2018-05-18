/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.core.query.columns.TagColumn;
import com.ozguryazilim.tekir.core.query.filter.TagFilter;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.entities.Opportunity_;
import com.ozguryazilim.tekir.entities.Process_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.VoucherProcessBase_;
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
@Browse(feature=OpportunityFeature.class )
public class OpportunityBrowse extends VoucherBrowseBase<Opportunity, OpportunityViewModel>{

    @Inject
    private OpportunityRepository repository;
    
    @Inject 
    private OpportunityHome home;
    
    @Override
    protected void buildQueryDefinition(QueryDefinition<Opportunity, OpportunityViewModel> queryDefinition) {
        queryDefinition
                .addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true)
                .addColumn(new SubTextColumn<>(Opportunity_.account, Contact_.name, "general.label.Account"), true)
                .addColumn(new TextColumn<>(Opportunity_.topic, "voucher.label.Topic"), true)
                .addColumn(new DateColumn<>(Opportunity_.date, "voucher.label.Date"), true)
                .addColumn(new MoneyColumn<>(Opportunity_.budget, Opportunity_.currency, "opportunity.label.Budget"), true)
                .addColumn(new UserColumn<>(Opportunity_.owner, "voucher.label.Owner"), true)
                .addColumn(new TextColumn<>(Opportunity_.referenceNo, "voucher.label.ReferenceNo"), false)
                .addColumn(new TagColumn<>("tags", "general.label.Tag"), false)
                .addColumn(new TextColumn<>(Opportunity_.info, "voucher.label.Info"), false)
                .addColumn(new TextColumn<>(VoucherBase_.stateReason, "voucher.label.StateReason"), false)
                .addColumn(new TextColumn<>(VoucherBase_.stateInfo, "voucher.label.StateInfo"), false)
                .addColumn(new VoucherStateColumn<>( VoucherBase_.state, "general.label.State"), false)
                .addColumn(new SubTextColumn<>(Opportunity_.process, Process_.processNo, "voucher.label.Process"), false);

        
        queryDefinition
                .addFilter(new StringFilter<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"))
                .addFilter(new TagFilter<>("tags", "general.label.Tag","Opportunity"))
                .addFilter(new StringFilter<>(VoucherBase_.info, "voucher.label.Info"))
                .addFilter(new StringFilter<>(VoucherBase_.topic, "voucher.label.Topic"))
                .addFilter(new VoucherStateFilter<>(VoucherBase_.state, getHome().getStateConfig().getStates(), "general.label.State"))
                .addFilter(new VoucherStateTypeFilter<>(VoucherBase_.state, "voucher.label.StateType"))
                .addFilter(new StringFilter<>(VoucherBase_.stateReason, "voucher.label.StateReason"))
                .addFilter(new UserFilter<>(VoucherBase_.owner, "voucher.label.Owner"))
                .addFilter(new BigDecimalFilter<>(Opportunity_.budget, "opportunity.label.Budget"))
                .addFilter(new SubStringFilter<>(Opportunity_.account, Contact_.name, "general.label.Account"))
                .addFilter(new SubStringFilter<>(VoucherProcessBase_.process, Process_.processNo, "voucher.label.Process"))
                .addFilter(new DateFilter<>(VoucherBase_.date, "voucher.label.Date", FilterOperand.In, DateValueType.LastTenDays));
                
    }

    @Override
    public VoucherRepositoryBase<Opportunity, OpportunityViewModel> getVoucherRepository() {
        return repository;
    }
    
    @Override
    public VoucherFormBase<Opportunity> getHome() {
    	// TODO Auto-generated method stub
    	return home;
    }
    
}
