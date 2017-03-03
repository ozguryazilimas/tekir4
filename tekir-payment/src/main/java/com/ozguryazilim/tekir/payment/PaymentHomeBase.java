/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.PaymentBase;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.VoucherMatchable;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.tekir.voucher.matcher.VoucherMatcherService;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.reports.JasperReportHandler;

import javax.inject.Inject;

/**
 *
 * @author oyas
 */
public abstract class PaymentHomeBase<E extends PaymentBase> extends VoucherFormBase<E>{
    
    @Inject
    private VoucherMatcherService matcherService;
    
    @Inject
    private ProcessService processService;
    
    @Inject
    private CurrencyService currencyService;
    
    @Inject
    private JasperReportHandler reportHandler;
   
    
    private VoucherMatchable matchable;
    
    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check"), VoucherState.CLOSE);
        config.addTranstion(VoucherState.REVISE, new VoucherStateAction("publish", "fa fa-check"), VoucherState.CLOSE);
        config.addTranstion(VoucherState.CLOSE, new VoucherStateAction("revise", "fa fa-unlock", true), VoucherState.REVISE);
        config.addStateAction(VoucherState.CLOSE, new VoucherPrintOutAction(this));
        return config;
    }

    
    @Override
    public void createNew() {
        super.createNew(); 
        getEntity().setCurrency(currencyService.getDefaultCurrency());
        matchable = null;
    }

    @Override
    public boolean onAfterLoad() {
        matchable = matcherService.findMatchableByFeature(getEntity().getStarter());
        return super.onAfterLoad(); 
    }
    
    @Override
    public boolean onBeforeSave() {
        if (getEntity().getProcess() == null) {
            getEntity().setProcess(processService.createProcess(getEntity().getAccount(), getEntity().getTopic(), getProcessType()));
        }

        getEntity().setLocalAmount(currencyService.convert(getEntity().getCurrency(), getEntity().getAmount(), getEntity().getDate()));
        
        return super.onBeforeSave();
    }
    
    @Override
    public boolean onAfterSave() {
        if( getEntity().getState().equals(VoucherState.CLOSE)){
            if( getEntity().getStarter() != null ){
                matcherService.updateMachters(getEntity().getStarter(), getFeaturePointer(), getEntity().getCurrency(), getEntity().getAmount(), getEntity().getLocalAmount(), getEntity().getProcess().getProcessNo());
            }
        }
        return super.onAfterSave();
    }
    
    
    public com.ozguryazilim.tekir.entities.Process getProcess() {
        return getEntity().getProcess();
    }

    public void setProcess(com.ozguryazilim.tekir.entities.Process process) {
        getEntity().setProcess(process);
        if (process != null) {
            getEntity().setAccount(process.getAccount());
            getEntity().setTopic(process.getTopic());
        }
    }

    public Contact getAccount() {
        return getEntity().getAccount();
    }

    public void setAccount(Contact account) {
        getEntity().setAccount(account);
        getEntity().setProcess(null);
        
        if( matchable != null && !matchable.getAccount().equals(account)){
            matchable = null;
        }
        
        if (!account.getContactRoles().contains("ACCOUNT")) {
            FacesMessages.error("Seçtiğiniz bağlantı bir Cari değil!", "Bağlantıyı cariye dönüştürmelisiniz?");
        }
        
    }
    
    public Person getPerson() {
        if (getAccount() instanceof Person) {
            return (Person) getAccount();
        } else {
            return ((Corporation) getAccount()).getPrimaryContact();
        }
    }

    public Corporation getCorporation() {
        if (getAccount() instanceof Corporation) {
            return (Corporation) getAccount();
        } else {
            return ((Person) getAccount()).getCorporation();
        }
    }


    public VoucherMatchable getMatchable() {
        return matchable;
    }

    public void setMatchable(VoucherMatchable matchable) {
        this.matchable = matchable;
        
        if( matchable != null ){
            
            if( getEntity().getAccount() == null ){
                getEntity().setAccount(matchable.getAccount());
            }
            
            com.ozguryazilim.tekir.entities.Process p = processService.findByProcessNo(matchable.getProcessNo());
            if( p != null && Strings.isNullOrEmpty(getEntity().getTopic())){
                getEntity().setTopic(p.getTopic());
            }
            getEntity().setProcess(p);
            
            getEntity().setStarter(matchable.getFeature());
            
            getEntity().setCurrency(matchable.getCurrency());
            getEntity().setAmount(matchable.getRemainder());
        }
    }

    
    protected abstract ProcessType getProcessType();
    
}
