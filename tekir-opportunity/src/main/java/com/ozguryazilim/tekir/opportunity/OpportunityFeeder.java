/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.entities.Opportunity;
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
public class OpportunityFeeder extends AbstractFeeder<Opportunity> {

    @Inject
    private Identity identity;

    public void feed(@Observes @FeatureQualifier(feauture = OpportunityFeature.class) @After VoucherStateChange event) {

        //FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
        if (event.getPayload() instanceof Opportunity) {

            Opportunity entity = (Opportunity) event.getPayload();

            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
            FeaturePointer contactPointer = FeatureUtils.getAccountFeaturePointer(entity);
            
            sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getTopic(), getMessage(event), voucherPointer, contactPointer);
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
                return "Opportunity created";
            case "Publish" :
                return "Opportunity published";
            case "Won" :
                return "Opportunity won! Congrats!";
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
