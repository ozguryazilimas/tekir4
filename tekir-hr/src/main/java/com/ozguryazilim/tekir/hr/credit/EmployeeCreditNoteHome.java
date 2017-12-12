/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.credit;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.EmployeeCreditNote;
import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateEffect;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.telve.config.LocaleSelector;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.messages.TelveResourceBundle;
import com.ozguryazilim.telve.reports.JasperReportHandler;
import com.ozguryazilim.telve.sequence.SequenceManager;
import java.util.Map;
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Erdem Uslu
 */
@FormEdit( feature = EmployeeCreditNoteFeature.class)
public class EmployeeCreditNoteHome extends VoucherFormBase<EmployeeCreditNote>{

    private static Logger LOG = LoggerFactory.getLogger(EmployeeCreditNoteHome.class);
    
    @Inject
    private EmployeeCreditNoteRepository repository;

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
        
        getEntity().setLocalAmount(currencyService.convert(getEntity().getCurrency(), getEntity().getAmount(), getEntity().getDate()));
        
        return super.onBeforeSave();
    }
  
    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();
        VoucherState complete = new VoucherState("COMPLETE", VoucherStateType.CLOSE, VoucherStateEffect.POSITIVE);
        VoucherState cancel = new VoucherState("CANCEL", VoucherStateType.CLOSE, VoucherStateEffect.NEGATIVE);
        
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check" ), VoucherState.OPEN);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("complete", "fa fa-check" , false), complete);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("cancel", "fa fa-ban", true), cancel);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("revise", "fa fa-unlock", true), VoucherState.REVISE);
        config.addTranstion(VoucherState.REVISE, new VoucherStateAction("publish", "fa fa-check"), VoucherState.OPEN);
        
        config.addStateAction(VoucherState.REVISE, new VoucherPrintOutAction(this));
        config.addStateTypeAction(VoucherStateType.OPEN, new VoucherPrintOutAction(this));
        config.addStateTypeAction(VoucherStateType.CLOSE, new VoucherPrintOutAction(this));
        
        return config;
    }
    
    @Override
    protected void decoratePrintOutParams(Map<String, Object> params) {
        params.put(JRParameter.REPORT_LOCALE, LocaleSelector.instance().getLocale());
        params.put(JRParameter.REPORT_RESOURCE_BUNDLE, TelveResourceBundle.getBundle());
    }

    @Override
    protected RepositoryBase<EmployeeCreditNote, ?> getRepository() {
        return repository;
    }
    
    public Employee getEmployee() {
        return getEntity().getEmployee();
    }
    
    public FinanceAccount getFinanceAccount() {
        return getEntity().getFinanceAccount();
    }

    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase entityBase){
    	return FeatureUtils.getFeaturePointer(entityBase);
    }
    
}
