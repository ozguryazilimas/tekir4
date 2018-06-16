/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.virement;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.core.dialogs.ExchangeRateNotFoundDialog;
import com.ozguryazilim.tekir.entities.AccountVirement;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.reports.JasperReportHandler;
import com.ozguryazilim.telve.sequence.SequenceManager;
import javax.inject.Inject;

import javax.money.convert.CurrencyConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oyas
 */
@FormEdit( feature = AccountVirementFeature.class)
public class AccountVirementHome extends VoucherFormBase<AccountVirement>{
	
	private static Logger LOG = LoggerFactory.getLogger(AccountVirementHome.class);

    @Inject
    private AccountVirementRepository repository;
    
    @Inject
    private CurrencyService currencyService;

    @Inject
    private SequenceManager sequenceManager;
    
    @Inject
    private ProcessService processService;    
    
    @Inject
    private JasperReportHandler reportHandler;

    @Inject
    private ExchangeRateNotFoundDialog exchangeRateNotFoundDialog;
    
    @Override
    public void createNew() {
        super.createNew(); 
        getEntity().setCurrency(currencyService.getDefaultCurrency());
    }

    @Override
    public boolean onBeforeSave() {
        try {
            getEntity().setLocalAmount(currencyService.convert(
                getEntity().getCurrency(), getEntity().getAmount(), getEntity().getDate()));
        } catch (CurrencyConversionException CCEx) {
            exchangeRateNotFoundDialog.openDialog();
            return false;
        }
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
    protected RepositoryBase<AccountVirement, ?> getRepository() {
        return repository;
    }
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase contact){
    		return FeatureUtils.getFeaturePointer(contact);
    }
}
