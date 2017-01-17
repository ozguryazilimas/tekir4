/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.credit;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.AccountCreditNote;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.telve.data.RepositoryBase;
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
        config.addStateAction(VoucherState.CLOSE, new VoucherPrintOutAction(this));
        return config;
    }

    @Override
    protected RepositoryBase<AccountCreditNote, ?> getRepository() {
        return repository;
    }
    
}
