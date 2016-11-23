/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.entities.PaymentBase;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.matcher.VoucherMatcherService;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
public abstract class PaymentFeederBase<E extends PaymentBase> extends AbstractFeeder<E> {
    
    @Inject
    private Identity identity;
    
    @Inject
    private VoucherMatcherService matcherService;
    
    @Inject
    private ProcessService processService;
    
    @Inject
    private AccountTxnService accountTxnService;
    
    public void feedFeeder(VoucherStateChange event) {
        if (event.getPayload() instanceof PaymentBase) {

            List<FeaturePointer> mentions = new ArrayList<>();
            E entity = (E) event.getPayload();

            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
            FeaturePointer contactPointer = FeatureUtils.getAccountFeaturePointer(entity);
            mentions.add(contactPointer);
            mentions.add(voucherPointer);
            
            sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getVoucherNo(), getMessage(event), mentions);
        }
    }
    
    public void feedMatcherService(VoucherStateChange event) {
        //TODO: Burada sadece publish olduğunda matcherservise gitmeli.
        //TODO: Eğer üzerinde bir matcher varsa edite izin verilmemeli.
        if( "OPEN".equals(event.getTo().getName())){
            if (event.getPayload() instanceof PaymentBase) {
                E entity = (E) event.getPayload();
                matcherService.register(entity, entity.getCurrency(), entity.getAmount());
            }
        }
        
        
        
        if( "OPEN".equals(event.getTo().getName()) && "DRAFT".equals(event.getFrom().getName()) ){
            if (event.getPayload() instanceof PaymentBase) {
                E entity = (E) event.getPayload();
                processService.incProcessUsage(entity.getProcess());
            }
        }
        
        if( event.getTo().getType().equals(VoucherStateType.CLOSE) ){
            if (event.getPayload() instanceof PaymentBase) {
                E entity = (E) event.getPayload();
                processService.decProcessUsage(entity.getProcess());
            }
        }
        
    }
    
    
    public void feedAccountTxn(EntityChangeEvent event) {
        
        if( event.getAction() != EntityChangeAction.DELETE   ) {
            E entity = (E) event.getEntity();
            
            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
            
            accountTxnService.saveFeature(voucherPointer, entity.getAccount(), entity.getCode(), entity.getInfo(), Boolean.FALSE, getProcessType() == ProcessType.PURCHASE, entity.getCurrency(), entity.getAmount(), entity.getDate(), entity.getOwner(), entity.getProcess().getProcessNo(), entity.getState().toString(), entity.getStateReason());
        }
        
        //TODO: Delete edildiğinde de gidip txn'den silme yapılmalı.
            
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
                    return getProcessType() + " payment created";
                case "CLOSE":
                    return getProcessType() + " payment Won. Congrats!";
                case "LOST":
                    return getProcessType() + " payment lost! " + event.getPayload().getStateReason();
                case "CANCELED":
                    return getProcessType() + " payment canceled. " + event.getPayload().getStateReason();
                default:
                    return getProcessType() + " payment created";
            }
    }
    
    protected abstract ProcessType getProcessType();
}
