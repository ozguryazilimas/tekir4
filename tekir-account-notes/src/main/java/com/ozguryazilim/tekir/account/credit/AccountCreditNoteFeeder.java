/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.credit;


import com.ozguryazilim.tekir.entities.AccountCreditNote;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.qualifiers.After;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Feeder
public class AccountCreditNoteFeeder extends AbstractFeeder<AccountCreditNote>{

    @Inject
    private Identity identity;
    
    public void feed(@Observes @FeatureQualifier(feauture = AccountCreditNoteFeature.class) @After VoucherStateChange event) {

        //FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
        if (event.getPayload() instanceof AccountCreditNote) {

            AccountCreditNote entity = (AccountCreditNote) event.getPayload();

            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
            FeaturePointer contactPointer = FeatureUtils.getAccountFeaturePointer(entity.getAccount());

            
            sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getInfo(), getMessage(event), voucherPointer, contactPointer);
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
        switch( event.getAction().getName()){
            case "CREATE" :
                return "Credit Note created";
            case "Publish" :
                return "Credit Note published";
            case "Won" :
                return "Credit Note";
            case "Loss" :
                return "Opportunity Lost" + event.getPayload().getStateReason();
            case "Cancel":
                return "Opportunity canceled. " + event.getPayload().getStateReason();
                
        }
        
        switch (event.getTo().getName()) {
                case "OPEN":
                    return "Opportunity created";
                case "CLOSE":
                    return "Opportunity Won. Congrats!";
                case "LOST":
                    return "Opportunity lost! " + event.getPayload().getStateReason();
                case "CANCELED":
                    return "Opportunity canceled. " + event.getPayload().getStateReason();
                default:
                    return "Opportunity created";
            }
    }
    
}
