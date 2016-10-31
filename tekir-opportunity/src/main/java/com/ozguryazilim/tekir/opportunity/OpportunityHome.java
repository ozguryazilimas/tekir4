/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.entities.VocuherStatus;
import com.ozguryazilim.tekir.opportunity.config.OpportunityPages;
import com.ozguryazilim.tekir.quote.QuoteHome;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.sequence.SequenceManager;
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
    
    @Inject
    private OpportunityFeeder feeder;
    
    @Inject
    private SequenceManager sequenceManager;
    
    @Inject
    private AccountTxnService accountTxnService;
    
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
        
        if( Strings.isNullOrEmpty( getEntity().getProcessId() )){
            getEntity().setProcessId(sequenceManager.getNewSerialNumber("PS", 6));
        }
        
        return super.onBeforeSave(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onAfterSave() {
        feeder.feed(getEntity());
        
        accountTxnService.saveFeature(getFeaturePointer(), getEntity().getAccount(), getEntity().getCode(), getEntity().getTopic(), Boolean.FALSE, Boolean.TRUE, getEntity().getCurrency(), getEntity().getBudget(), getEntity().getDate(), getEntity().getOwner(), getEntity().getProcessId(), getEntity().getStatus().name(), getEntity().getStatusReason());
        
        return super.onAfterSave(); //To change body of generated methods, choose Tools | Templates.
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
    
    public Class<? extends ViewConfig> closeLoss(){
        getEntity().setStatus(VocuherStatus.LOST);
        return save();
    }
    
    public Class<? extends ViewConfig> closeCancel(){
        getEntity().setStatus(VocuherStatus.CANCELED);
        return save();
    }
    
    /**
     * FIXME: Bu method üst sınıflara taşınmalı. Voucher ve hatta Form seviyelerine.
     * @return 
     */
    public FeaturePointer getFeaturePointer(){
        FeaturePointer fp = new FeaturePointer();
        fp.setBusinessKey(getEntity().getVoucherNo());
        fp.setPrimaryKey(getEntity().getId());
        fp.setFeature(getEntity().getClass().getSimpleName());
        return fp;
    }
}
