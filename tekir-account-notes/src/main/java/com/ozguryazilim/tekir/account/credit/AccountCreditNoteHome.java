package com.ozguryazilim.tekir.account.credit;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.AccountCreditNote;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.AbstractPerson;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.reports.JasperReportHandler;
import com.ozguryazilim.telve.sequence.SequenceManager;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oyas
 */
@FormEdit( feature = AccountCreditNoteFeature.class)
public class AccountCreditNoteHome extends VoucherFormBase<AccountCreditNote>{

    private static Logger LOG = LoggerFactory.getLogger(AccountCreditNoteHome.class);
    
    @Inject
    private AccountCreditNoteRepository repository;

    @Inject
    private CurrencyService currencyService;

    @Inject
    private SequenceManager sequenceManager;
    
    @Inject
    private JasperReportHandler reportHandler;
    
    @Override
    public void createNew() {
        super.createNew(); 
        getEntity().setCurrency(currencyService.getDefaultCurrency());
    }

    @Override
    public boolean onBeforeSave() {
        /* ProcessService'den alınacak
        if( Strings.isNullOrEmpty( getEntity().getProcessId() )){
            getEntity().setProcessId(sequenceManager.getNewSerialNumber("PS", 6));
        }*/
        
        getEntity().setLocalAmount(currencyService.convert(getEntity().getCurrency(), getEntity().getAmount(), getEntity().getDate()));
        
        return super.onBeforeSave();
    }
    
    
    
    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check" ), VoucherState.CLOSE);
        config.addTranstion(VoucherState.CLOSE, new VoucherStateAction("reopen", "fa fa-unlock", true ), VoucherState.DRAFT);
        config.addStateTypeAction(VoucherStateType.CLOSE, new VoucherPrintOutAction(this));
        return config;
    }

    @Override
    protected RepositoryBase<AccountCreditNote, ?> getRepository() {
        return repository;
    }
    
    public Contact getAccount() {
        return getEntity().getAccount();
    }
    
    public AbstractPerson getPerson() {
        if (getAccount() instanceof AbstractPerson) {
            return (AbstractPerson) getAccount();
        } else {
            return ((Corporation) getAccount()).getPrimaryContact();
        }
    }

    public Corporation getCorporation() {
        if (getAccount() instanceof Corporation) {
            return (Corporation) getAccount();
        } else {
            return ((AbstractPerson) getAccount()).getCorporation();
        }
    }
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase contact){
    		return FeatureUtils.getFeaturePointer(contact);
    }
}
