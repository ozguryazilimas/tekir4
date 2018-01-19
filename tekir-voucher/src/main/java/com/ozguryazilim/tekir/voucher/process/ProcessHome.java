/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.process;

import com.google.common.base.Splitter;
import java.util.List;
import javax.inject.Inject;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureLinkController;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import java.util.HashMap;
import java.util.Map;

/**
 * Process View Conttroller
 *
 * @author oyas
 */
@FormEdit(feature = ProcessFeature.class)
public class ProcessHome extends FormBase<Process, Long> {

    @Inject
    private AccountTxnRepository accountTxnRepository;

    @Inject
    private ProcessRepository repository;

    @Inject
    private ProcessService processService;

    @Inject
    private FeatureLinkController featureController;

    private List<AccountTxn> txnList;

    private static final String SALES_PROCESS_STEPS = "OpportunityFeature,QuoteFeature,SalesOrderFeature,SalesInvoiceFeature,PaymentReceivedFeature";
    private static final String PURCHAES_PROCESS_STEPS = "PurchaseOrderFeature,PurchaseInvoiceFeature,PaymentFeature";

    private Map<String, String> steps = new HashMap<>();

    @Override
    protected RepositoryBase<Process, ?> getRepository() {
        return repository;
    }

    public boolean onAfterLoad() {
        setTxnList(null);
        refreshTxn();
        return super.onAfterLoad();
    }

    protected void refreshTxn() {
        txnList = accountTxnRepository.findByProcessId(getEntity().getProcessNo());
        txnList.sort((AccountTxn t, AccountTxn t1) -> {
            return t.getDate().compareTo(t1.getDate());
        });

        steps.clear();
        getProcessSteps().forEach((s) -> steps.put(s, "NAN"));
        txnList.forEach((t) -> {
            VoucherState vs = VoucherState.valueOf(t.getStatus());
            if( vs.getType().equals(VoucherStateType.CLOSE) ){
                steps.put(t.getFeature().getFeature(), vs.getEffect().toString());
            } else {
                steps.put(t.getFeature().getFeature(), vs.getType().toString());
            }
        });
    }

    public boolean showDelete() {
        return txnList.isEmpty();
    }

    public FeaturePointer getFeaturePointer() {
        FeaturePointer result = new FeaturePointer();
        result.setBusinessKey(getEntity().getProcessNo());
        result.setFeature(getFeatureClass().getSimpleName());
        result.setPrimaryKey(getEntity().getId());
        return result;
    }
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase contact){
    		return FeatureUtils.getFeaturePointer(contact);
    }
    
    public List<AccountTxn> getTxnList() {
        if (txnList == null) {
            refreshTxn();
        }
        return txnList;
    }

    public void setTxnList(List<AccountTxn> txnList) {
        this.txnList = txnList;
    }

    public List<String> getProcessSteps() {
        if (ProcessType.SALES.equals(getEntity().getType())) {
            return Splitter.on(',').omitEmptyStrings().trimResults().splitToList(SALES_PROCESS_STEPS);
        } else {
            return Splitter.on(',').omitEmptyStrings().trimResults().splitToList(PURCHAES_PROCESS_STEPS);
        }
    }

    public Map<String, String> getSteps() {
        return steps;
    }

    public void setSteps(Map<String, String> steps) {
        this.steps = steps;
    }

}
