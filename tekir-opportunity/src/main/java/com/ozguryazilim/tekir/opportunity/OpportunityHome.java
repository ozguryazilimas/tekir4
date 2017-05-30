/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import javax.inject.Inject;
import javax.money.convert.CurrencyConversionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.core.dialogs.ExchangeRateFetchDialog;
import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.quote.QuoteHome;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.messages.FormatedMessage;
import com.ozguryazilim.telve.messages.MessagesUtils;
import com.ozguryazilim.telve.reports.JasperReportHandler;

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
    
    @Inject
    private FormatedMessage formatedMessage;
    
    @Inject
    private ExchangeRateFetchDialog exchangeRateFetchDialog;
    
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
    	
    	try {
			getEntity().setLocalBudget(
					currencyService.convert(getEntity().getCurrency(), getEntity().getBudget(), getEntity().getDate()));
		} catch (CurrencyConversionException e) {
			exchangeRateFetchDialog.openDialog(this);
			return false;
		}
        
        if( getEntity().getState().equals(VoucherState.DRAFT) && !getEntity().isPersisted()){
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
    
    public void iDontWanna(){
		FacesMessages.info(
				formatedMessage.getMessage(
						MessagesUtils.getMessage("opportunity.info.ExchangeRate"),
						new Object[]{MessagesUtils.getMessage("module.caption.ExchangeRate")}
						)
				);
    }

}
