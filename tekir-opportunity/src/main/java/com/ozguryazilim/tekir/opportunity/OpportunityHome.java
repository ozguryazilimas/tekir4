/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.quote.QuoteHome;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureHandler;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.sequence.SequenceManager;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 * Opportunity View Conttroller
 * @author Hakan Uygun
 */
@FormEdit(feature=OpportunityFeature.class)
public class OpportunityHome extends VoucherFormBase<Opportunity>{

    @Inject
    private OpportunityRepository repository;

    @Inject
    private QuoteHome quoteHome;

    @Inject
    private CurrencyService currencyService;
    
    @Inject
    private SequenceManager sequenceManager;
    
    @Inject
    private AccountTxnService accountTxnService;
    
    @Inject
    private ProcessService processService;
    
    @Override
    public void createNew() {
        super.createNew(); 
        
        getEntity().setCurrency(currencyService.getDefaultCurrency());
        getEntity().setProbability(50);
        
    }
    
    
    
    @Override
    protected RepositoryBase<Opportunity, ?> getRepository() {
        return repository;
    }

    @Override
    public boolean onBeforeSave() {
        
        if( getEntity().getState().equals(VoucherState.DRAFT) ){
            getEntity().setState(VoucherState.OPEN);
        }
        
        //Opportunity'de bir process id'si doğal olarak olmayacak. Satış süreci ilk kez buradan başlar :)
        //Eğer daha önce bir process alınmamış ise yeni bir tane oluştur.
        if( getEntity().getProcess() == null ) {
            getEntity().setProcess(processService.createProcess(getEntity().getAccount(), getEntity().getTopic(), ProcessType.SALES));
        }
        
        return super.onBeforeSave(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onAfterSave() {
        accountTxnService.saveFeature(getFeaturePointer(), getEntity().getAccount(), getEntity().getCode(), getEntity().getTopic(), Boolean.FALSE, Boolean.TRUE, getEntity().getCurrency(), getEntity().getBudget(), getEntity().getDate(), getEntity().getOwner(), getEntity().getProcess().getProcessNo(), getEntity().getState().toString(), getEntity().getStateReason());
        
        return super.onAfterSave(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    public Class<? extends ViewConfig> closeWin(){
        /*
        getEntity().setStatus(VocuherStatus.WON);
        save();
        */
        //FIXME: FP üretmek için utility lazım. Ama daha önemlisi Voucher'lar otomatik üretebilmeli.
        FeaturePointer fp = new FeaturePointer();
        
        fp.setBusinessKey(getEntity().getVoucherNo());
        fp.setPrimaryKey(getEntity().getId());
        fp.setFeature(getEntity().getClass().getSimpleName());
        
        //FIXME: Buralar düzeltilecek
        return null; //quoteHome.createFromFeature(  fp, getEntity().getAccount(), getEntity().getProcessId());
        
    }
    
    public Class<? extends ViewConfig> closeLoss(){
        //getEntity().setStatus(VocuherStatus.LOST);
        return save();
    }
    
    public Class<? extends ViewConfig> closeCancel(){
        //getEntity().setStatus(VocuherStatus.CANCELED);
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

    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("Publish", "fa fa-check", false, ""), VoucherState.OPEN);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("Won", "fa fa-check", false, ""), VoucherState.CLOSE);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("Loss", "fa fa-close", true, ""), VoucherState.CLOSE);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("Cancel", "fa fa-ban", true, ""), VoucherState.CLOSE);
        return config;
    }

    @Override
    public Class<? extends FeatureHandler> getFeatureClass() {
        return OpportunityFeature.class;
    }
}
