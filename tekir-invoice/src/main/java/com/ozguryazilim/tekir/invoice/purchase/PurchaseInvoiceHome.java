/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice.purchase;

import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.PurchaseInvoice;
import com.ozguryazilim.tekir.invoice.InvoiceHomeBase;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemEditor;
import com.ozguryazilim.tekir.voucher.matcher.MatcherStateChange;
import com.ozguryazilim.tekir.voucher.matcher.VoucherMatcherService;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@FormEdit(feature = PurchaseInvoiceFeature.class)
public class PurchaseInvoiceHome extends InvoiceHomeBase<PurchaseInvoice> {

    @Inject
    private PurchaseInvoiceRepository repository;

    @Inject
    private VoucherCommodityItemEditor commodityItemEditor;

    @Inject
    private ProcessService processService;
    
    @Inject
    private VoucherMatcherService matcherService;
    
    
    @Override
    protected RepositoryBase<PurchaseInvoice, ?> getRepository() {
        return repository;
    }

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.PURCHASE;
    }

    public void listenMatcherState(@Observes @FeatureQualifier(feauture = PurchaseInvoiceFeature.class) MatcherStateChange event) {
        feedMatcherState(event);
    }

}
