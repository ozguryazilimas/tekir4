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
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Feeder(icon = "fa fa-book")
public class OpportunityFeeder extends AbstractFeeder<Opportunity> {

    @Inject
    private Identity identity;

    @Override
    public void feed(Opportunity entity) {

        FeaturePointer voucherPointer = new FeaturePointer();
        voucherPointer.setBusinessKey(entity.getVoucherNo());
        voucherPointer.setPrimaryKey(entity.getId());
        voucherPointer.setFeature(entity.getClass().getSimpleName());

        FeaturePointer contactPointer = new FeaturePointer();
        contactPointer.setBusinessKey(entity.getAccount().getName());
        contactPointer.setPrimaryKey(entity.getAccount().getId());
        contactPointer.setFeature(entity.getAccount().getClass().getSimpleName());

        //TODO: Özellikle lost durumu için competitor varsa yazmak ve hatta linklemek lazım.
        /*
        String subject;
        switch ( entity.getStatus() ){
            case OPEN : subject = "Opportunity created"; break;
            case WON : subject = "Opportunity Won. Congrats!"; break;
            case LOST : subject = "Opportunity lost! " + entity.getStatusReason(); break;
            case CANCELED : subject = "Opportunity canceled. " + entity.getStatusReason(); break;
            default:
                subject = "Opportunity created"; break;
        }
         */
        sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getTopic(), "", voucherPointer, contactPointer);
    }

    public void feed(@Observes @FeatureQualifier(feauture = OpportunityFeature.class) @After VoucherStateChange event) {

        //FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
        if (event.getPayload() instanceof Opportunity) {

            Opportunity entity = (Opportunity) event.getPayload();

            //FIXME: Bu pointer işi için Util'e gitmek lazım ( FeautureUtils )
            FeaturePointer voucherPointer = new FeaturePointer();
            voucherPointer.setBusinessKey(entity.getVoucherNo());
            voucherPointer.setPrimaryKey(entity.getId());
            voucherPointer.setFeature(entity.getClass().getSimpleName());

            FeaturePointer contactPointer = new FeaturePointer();
            contactPointer.setBusinessKey(entity.getAccount().getName());
            contactPointer.setPrimaryKey(entity.getAccount().getId());
            contactPointer.setFeature(entity.getAccount().getClass().getSimpleName());

            
            sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getTopic(), getMessage(event), voucherPointer, contactPointer);
        }
    }

    /*
    public void feed(@Observes @EntityQualifier(entity = Opportunity.class) @After EntityChangeEvent event) {
        
        //Update işlemleri için feed etmek aslında StateChange için sorun yaratacak :(
        if( event.getAction() == EntityChangeAction.UPDATE ) return;
        
        //FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
        if (event.getEntity() instanceof Opportunity) {

            Opportunity entity = (Opportunity) event.getEntity();

            //FIXME: Bu pointer işi için Util'e gitmek lazım ( FeautureUtils )
            FeaturePointer voucherPointer = new FeaturePointer();
            voucherPointer.setBusinessKey(entity.getVoucherNo());
            voucherPointer.setPrimaryKey(entity.getId());
            voucherPointer.setFeature(entity.getClass().getSimpleName());

            FeaturePointer contactPointer = new FeaturePointer();
            contactPointer.setBusinessKey(entity.getAccount().getName());
            contactPointer.setPrimaryKey(entity.getAccount().getId());
            contactPointer.setFeature(entity.getAccount().getClass().getSimpleName());

            
            sendFeed(entity.getState().getName(), "OpportunityFeeder", identity.getLoginName(), entity.getTopic(), getMessage(event), voucherPointer, contactPointer);
        }
    }*/
    
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
    
    
    protected String getMessage( EntityChangeEvent event ){
        switch (event.getAction()) {
                case INSERT:
                    return "Opportunity created";
                case UPDATE:
                    return "Opportunity updated";
                case DELETE:
                    return "Opportunity deleted";
                default: return "";
            }
    }
}
