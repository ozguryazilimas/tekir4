/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice.purchase;

import com.ozguryazilim.tekir.entities.PurchaseInvoice;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.qualifiers.After;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Feeder
public class PurchaseInvoiceFeeder extends AbstractFeeder<PurchaseInvoice>{
    
    @Inject
    private Identity identity;
    
    public void feed(@Observes @FeatureQualifier(feauture = PurchaseInvoiceFeature.class) @After VoucherStateChange event) {

        //FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
        if (event.getPayload() instanceof PurchaseInvoice) {

            List<FeaturePointer> mentions = new ArrayList<>();
            PurchaseInvoice entity = (PurchaseInvoice) event.getPayload();

            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
            FeaturePointer contactPointer = FeatureUtils.getAccountFeaturePointer(entity);
            mentions.add(contactPointer);
            mentions.add(voucherPointer);
            
            sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getVoucherNo(), getMessage(event), mentions);
        }
    }

    /**
     * Geriye event bilgilerine bakarak feed body mesajını hazırlayıp döndürür.
     * 
     * TODO: i18n problemi ve action / state karşılaştırması + const kullanımına ihtiyaç var.
     * @param event
     * @return 
     */
    protected String getMessage( VoucherStateChange event ){
        switch (event.getTo().getName()) {
                case "OPEN":
                    return "Sales order created";
                case "CLOSE":
                    return "Sales order Won. Congrats!";
                case "LOST":
                    return "Sales Invoice lost! " + event.getPayload().getStateReason();
                case "CANCELED":
                    return "Sales Invoice canceled. " + event.getPayload().getStateReason();
                default:
                    return "Sales Invoice created";
            }
    }
    
}
