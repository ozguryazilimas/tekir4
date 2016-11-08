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
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.feature.FeatureHandler;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.sequence.SequenceManager;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@FormEdit( feature = AccountCreditNoteFeature.class)
public class AccountCreditNoteHome extends VoucherFormBase<AccountCreditNote>{

    @Inject
    private AccountCreditNoteRepository repository;

    @Inject
    private CurrencyService currencyService;

    @Inject
    private SequenceManager sequenceManager;
    
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
        return super.onBeforeSave();
    }
    
    
    
    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("Publish", "fa fa-check", false, ""), VoucherState.CLOSE);
        config.addTranstion(VoucherState.CLOSE, new VoucherStateAction("Revise", "fa fa-unlock", false, ""), VoucherState.OPEN);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("Publish", "fa fa-check", true, ""), VoucherState.CLOSE);
        return config;
    }

    @Override
    public Class<? extends FeatureHandler> getFeatureClass() {
        return AccountCreditNoteFeature.class;
    }

    @Override
    protected RepositoryBase<AccountCreditNote, ?> getRepository() {
        return repository;
    }
    
}
