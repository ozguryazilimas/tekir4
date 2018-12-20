package com.ozguryazilim.tekir.payment.purchase;

import com.ozguryazilim.tekir.entities.Payment;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.payment.PaymentHomeBase;
import com.ozguryazilim.tekir.payment.PaymentMatcherViewModel;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormEdit;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
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
public class PaymentHome extends PaymentHomeBase<Payment>{
    
    private static final Logger LOG = LoggerFactory.getLogger(PaymentHome.class);
    
    @Inject
    private PaymentRepository repository;
    
    private List<PaymentMatcherViewModel> matcherViewModels;
    
    private BigDecimal remainder = BigDecimal.ZERO;
    
    

    @Override
    protected RepositoryBase<Payment, ?> getRepository() {
        return repository;
    }
    
    /* Toplu Matchable ile işlem yapmak için deneme
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
    */

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.PURCHASE;
    } 
}
