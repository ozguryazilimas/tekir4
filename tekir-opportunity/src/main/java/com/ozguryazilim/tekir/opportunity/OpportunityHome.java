/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.opportunity.config.OpportunityPages;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormEdit;
import java.util.Currency;
import javax.inject.Inject;

/**
 * Opportunity View Conttroller
 * @author Hakan Uygun
 */
@FormEdit(browsePage = OpportunityPages.OpportunityBrowse.class, editPage = OpportunityPages.Opportunity.class, viewContainerPage = OpportunityPages.OpportunityView.class, masterViewPage = OpportunityPages.OpportunityMasterView.class)
public class OpportunityHome extends VoucherFormBase<Opportunity>{

    @Inject
    private OpportunityRepository repository;

    @Override
    public void createNew() {
        super.createNew(); //To change body of generated methods, choose Tools | Templates.
        
        //TODO: Bu değeri configden default currency olarak almak lazım. ( Hatta değer belki custemer, user default'u bile olabilir )
        //Customer > User > System şeklinde bir öncelik sıralaması nasıl olur?
        getEntity().setCurrency(Currency.getInstance("TRY"));
        
    }
    
    
    
    @Override
    protected RepositoryBase<Opportunity, ?> getRepository() {
        return repository;
    }
    
}
