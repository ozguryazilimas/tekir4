/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment.purchase;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Payment;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.VoucherMatchable;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.payment.PaymentMatcherViewModel;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.tekir.voucher.matcher.VoucherMatcherService;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.messages.FacesMessages;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.event.CellEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Payment Controller Class.
 * 
 * Stateler : 
 * DRAFT -> CLOSE
 * REVISE -> CLOSE
 * CLOSE -> REVISE
 * 
 * Account ve Regarding sadece ve sadecedraft statüsünde iken yapılabilir.
 * 
 * @author Hakan Uygun
 */
@FormEdit(feature = PaymentFeature.class)
public class PaymentHome extends VoucherFormBase<Payment>{
    
    private static final Logger LOG = LoggerFactory.getLogger(PaymentHome.class);
    
    @Inject
    private PaymentRepository repository;
    
    @Inject
    private VoucherMatcherService matcherService;
    
    @Inject
    private ProcessService processService;
    
    @Inject
    private CurrencyService currencyService;
    
    private List<PaymentMatcherViewModel> matcherViewModels;
    
    private BigDecimal remainder = BigDecimal.ZERO;
    
    private VoucherMatchable matchable;
    
    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check"), VoucherState.CLOSE);
        config.addTranstion(VoucherState.REVISE, new VoucherStateAction("publish", "fa fa-check"), VoucherState.CLOSE);
        config.addTranstion(VoucherState.CLOSE, new VoucherStateAction("revise", "fa fa-unlock", true), VoucherState.REVISE);
        return config;
    }

    @Override
    protected RepositoryBase<Payment, ?> getRepository() {
        return repository;
    }
    
    @Override
    public void createNew() {
        super.createNew(); 
        getEntity().setCurrency(currencyService.getDefaultCurrency());
        matchable = null;
    }
    
    @Override
    public boolean onBeforeSave() {
        if (getEntity().getProcess() == null) {
            getEntity().setProcess(processService.createProcess(getEntity().getAccount(), getEntity().getTopic(), ProcessType.PURCHASE));
        }

        return super.onBeforeSave();
    }

    @Override
    public boolean onAfterSave() {
        if( getEntity().getState().equals(VoucherState.CLOSE)){
            if( getEntity().getStarter() != null ){
                matcherService.updateMachters(getEntity().getStarter(), getFeaturePointer(), getEntity().getCurrency(), getEntity().getAmount(), getEntity().getProcess().getProcessNo());
            }
        }
        return super.onAfterSave(); //To change body of generated methods, choose Tools | Templates.
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
        
        buildMatcherViewModels();
        if (!account.getContactRoles().contains("ACCOUNT")) {
            FacesMessages.error("Seçtiğiniz bağlantı bir Cari değil!", "Bağlantıyı cariye dönüştürmelisiniz?");
        }
        if (!getEntity().getAccount().getContactRoles().contains("CUSTOMER")) {
            FacesMessages.warn("Seçtiğiniz bağlantı bir Müşteri değil.", "Bağlantıyı müşteri olarak işaretlemek ister misiniz?");
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

    @Override
    public boolean onAfterLoad() {
        matchable = matcherService.findMatchableByFeature(getEntity().getStarter());
        buildMatcherViewModels();
        return super.onAfterLoad(); 
    }
    
    
    
    public List<VoucherMatchable> getMacMatchables(){
        return matcherService.findMatchableVouchers(getEntity().getAccount(), "PurchaseInvoiceFeature", "");
    }

    public List<PaymentMatcherViewModel> getMatcherViewModels() {
        if( matcherViewModels == null ){
            buildMatcherViewModels();
        }
        return matcherViewModels;
    }

    private void buildMatcherViewModels() {
        //TODO: remainder felan da düzenlenmeli.
        matcherViewModels = new ArrayList<>();
        
        List<VoucherMatchable> ls = matcherService.findMatchableVouchers(getEntity().getAccount(), "PurchaseInvoiceFeature", "");
        for( VoucherMatchable m : ls ){
            PaymentMatcherViewModel pm = new PaymentMatcherViewModel(m, BigDecimal.ZERO);
            matcherViewModels.add(pm);
        }
    }

    public BigDecimal getRemainder() {
        return remainder;
    }

    public void setRemainder(BigDecimal remainder) {
        this.remainder = remainder;
    }
    
    public void onCellEdit(CellEditEvent event) {
        LOG.debug("CellEditEvent");
        
        BigDecimal oldValue = (BigDecimal) event.getOldValue();
        BigDecimal newValue = (BigDecimal) event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            //FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            //FacesContext.getCurrentInstance().addMessage(null, msg);
            setRemainder(getRemainder().add(oldValue).subtract(newValue));
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

    
}
