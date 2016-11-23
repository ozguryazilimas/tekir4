/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.purchase;

import com.ozguryazilim.tekir.entities.PurchaseOrder;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.matcher.VoucherMatcherService;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
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
public class PurchaseOrderFeeder extends AbstractFeeder<PurchaseOrder>{
    
    @Inject
    private Identity identity;
    
    @Inject
    private VoucherMatcherService matcherService;
    
    @Inject
    private ProcessService processService;
    
    public void feed(@Observes @FeatureQualifier(feauture = PurchaseOrderFeature.class) @After VoucherStateChange event) {

        //FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
        if (event.getPayload() instanceof PurchaseOrder) {

            List<FeaturePointer> mentions = new ArrayList<>();
            PurchaseOrder entity = (PurchaseOrder) event.getPayload();

            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
            FeaturePointer contactPointer = FeatureUtils.getAccountFeaturePointer(entity);
            mentions.add(contactPointer);
            mentions.add(voucherPointer);
            
            sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getVoucherNo(), getMessage(event), mentions);
        }
    }

     public void feedMatcherService(@Observes @FeatureQualifier(feauture = PurchaseOrderFeature.class) @After VoucherStateChange event) {
        //TODO: Burada sadece publish olduğunda matcherservise gitmeli.
        //TODO: Eğer üzerinde bir matcher varsa edite izin verilmemeli.
        if( "OPEN".equals(event.getTo().getName())){
            if (event.getPayload() instanceof PurchaseOrder) {
                PurchaseOrder entity = (PurchaseOrder) event.getPayload();
                matcherService.register(entity, entity.getCurrency(), entity.getTotal());
            }
        }
        
        
        if( "OPEN".equals(event.getTo().getName()) && "DRAFT".equals(event.getFrom().getName()) ){
            if (event.getPayload() instanceof PurchaseOrder) {
                PurchaseOrder entity = (PurchaseOrder) event.getPayload();
                processService.incProcessUsage(entity.getProcess());
            }
        }
        
        if( event.getTo().getType().equals(VoucherStateType.CLOSE) ){
            if (event.getPayload() instanceof PurchaseOrder) {
                PurchaseOrder entity = (PurchaseOrder) event.getPayload();
                processService.decProcessUsage(entity.getProcess());
            }
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
                    return "Sales Order lost! " + event.getPayload().getStateReason();
                case "CANCELED":
                    return "Sales Order canceled. " + event.getPayload().getStateReason();
                default:
                    return "Sales Order created";
            }
    }
    
}
