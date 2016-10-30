/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.entities.VocuherStatus;
import com.ozguryazilim.tekir.opportunity.config.OpportunityPages;
import com.ozguryazilim.tekir.quote.QuoteHome;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 * Opportunity View Conttroller
 * @author Hakan Uygun
 */
@FormEdit(browsePage = OpportunityPages.OpportunityBrowse.class, editPage = OpportunityPages.Opportunity.class, viewContainerPage = OpportunityPages.OpportunityView.class, masterViewPage = OpportunityPages.OpportunityMasterView.class)
public class OpportunityHome extends VoucherFormBase<Opportunity>{

    @Inject
    private OpportunityRepository repository;

    @Inject
    private QuoteHome quoteHome;

    @Inject
    private CurrencyService currencyService;
    
    @Override
    public void createNew() {
        super.createNew(); //To change body of generated methods, choose Tools | Templates.
        
        getEntity().setCurrency(currencyService.getDefaultCurrency());
        
    }
    
    
    
    @Override
    protected RepositoryBase<Opportunity, ?> getRepository() {
        return repository;
    }

    @Override
    public boolean onBeforeSave() {
        
        if( getEntity().getStatus() == VocuherStatus.DRAFT ){
            getEntity().setStatus(VocuherStatus.OPEN);
        }
        
        return super.onBeforeSave(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    public Class<? extends ViewConfig> closeWin(){
        
        getEntity().setStatus(VocuherStatus.WON);
        save();
        
        //FIXME: FP üretmek için utility lazım. Ama daha önemlisi Voucher'lar otomatik üretebilmeli.
        FeaturePointer fp = new FeaturePointer();
        
        fp.setBusinessKey(getEntity().getVoucherNo());
        fp.setPrimaryKey(getEntity().getId());
        fp.setFeature(getEntity().getClass().getSimpleName());
        
        return quoteHome.createFromFeature(  fp, getEntity().getAccount(), getEntity().getProcessId());
        
    }
}
