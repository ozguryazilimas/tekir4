/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.activity.ActivityFeature;
import com.ozguryazilim.tekir.activity.ActivityHome;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.quote.QuoteHome;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.telve.attachment.AttachmentContext;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.reports.JasperReportHandler;

import javax.inject.Inject;
import org.apache.deltaspike.core.api.provider.BeanProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Opportunity View Conttroller
 * @author Hakan Uygun
 */
@FormEdit(feature=OpportunityFeature.class)
public class OpportunityHome extends VoucherFormBase<Opportunity>{
	
    private static Logger LOG = LoggerFactory.getLogger(OpportunityHome.class);
    
    @Inject
    private OpportunityRepository repository;

    @Inject
    private QuoteHome quoteHome;

    @Inject
    private CurrencyService currencyService;
    
    @Inject
    private AccountTxnService accountTxnService;
    
    @Inject
    private ProcessService processService;
    
    @Inject
    private JasperReportHandler reportHandler;
    
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
        
        if( getEntity().getState().equals(VoucherState.DRAFT) && !getEntity().isPersisted()){
            getEntity().setState(VoucherState.OPEN);
        }
        
        //Opportunity'de bir process id'si doğal olarak olmayacak. Satış süreci ilk kez buradan başlar :)
        //Eğer daha önce bir process alınmamış ise yeni bir tane oluştur.
        if( getEntity().getProcess() == null ) {
            getEntity().setProcess(processService.createProcess(getEntity().getAccount(), getEntity().getTopic(), ProcessType.SALES));
        }
        
        getEntity().setLocalBudget(currencyService.convert(getEntity().getCurrency(), getEntity().getBudget(), getEntity().getDate()));
        
        return super.onBeforeSave(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onAfterCreate() {
        //Eğer Source bir activity ise geri mention bağlayalım.
        if( getEntity().getStarter() != null && getEntity().getStarter().getFeature().equals(ActivityFeature.class.getSimpleName())){
            //FIXME: Bir servis haline getirmek lazım activityHome ile yaptığımız şeyi
            //Bizim FeaturePointer'ımızıbir alalım
            FeaturePointer fp = getFeaturePointer();
            
            ActivityHome activityHome =  BeanProvider.getContextualReference(ActivityHome.class, true);
            activityHome.setId(getEntity().getStarter().getPrimaryKey());
            activityHome.getEntity().setRegarding(fp);
            
            activityHome.save();
            
        }
        return super.onAfterCreate(); 
    }

    

    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();        
        
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check" ), VoucherState.OPEN);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("won", "fa fa-check" ), VoucherState.WON);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("loss", "fa fa-close", true ), VoucherState.LOSS);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("cancel", "fa fa-ban", true ), VoucherState.CLOSE);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("revise", "fa fa-unlock", true ), VoucherState.DRAFT);
        
        config.addStateTypeAction(VoucherStateType.OPEN, new VoucherPrintOutAction(this));
        config.addStateTypeAction(VoucherStateType.CLOSE, new VoucherPrintOutAction(this));
        
        return config;
    }
    
    public Person getPerson() {
        if (getEntity().getAccount() instanceof Person) {
            return (Person) getEntity().getAccount();
        } else {
            return ((Corporation) getEntity().getAccount()).getPrimaryContact();
        }
    }

    public Corporation getCorporation() {
        if (getEntity().getAccount() instanceof Corporation) {
            return (Corporation) getEntity().getAccount();
        } else {
            return ((Person) getEntity().getAccount()).getCorporation();
        }
    }
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase contact){
    		return FeatureUtils.getFeaturePointer(contact);
    }

    public AttachmentContext getContext() {
        AttachmentContext result = new AttachmentContext();
        
        String accountRoot = getEntity().getAccount().getName() + "[" + getEntity().getAccount().getCode() + "]";
        FeaturePointer fp = getFeaturePointer();
        result.setRoot( accountRoot + "/" + fp.getFeature() + "/" + fp.getBusinessKey());
        result.setUsername("Bişi");
        result.setFeaturePointer(getFeaturePointer());
        
        return result;
    }
}
