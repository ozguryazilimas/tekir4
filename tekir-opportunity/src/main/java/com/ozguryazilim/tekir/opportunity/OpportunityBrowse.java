/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.entities.Opportunity_;
import com.ozguryazilim.tekir.entities.Process_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import com.ozguryazilim.telve.query.columns.SubTextColumn;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse(feature=OpportunityFeature.class )
public class OpportunityBrowse extends VoucherBrowseBase<Opportunity, OpportunityViewModel>{

    @Inject
    private OpportunityRepository repository;
    
    @Override
    protected void buildQueryDefinition(QueryDefinition<Opportunity, OpportunityViewModel> queryDefinition) {
        queryDefinition
                .addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true)
                .addColumn(new SubTextColumn<>(Opportunity_.process, Process_.topic, "voucher.label.Topic"), true);
                
    }

    @Override
    public VoucherRepositoryBase<Opportunity, OpportunityViewModel> getVoucherRepository() {
        return repository;
    }
    
}
