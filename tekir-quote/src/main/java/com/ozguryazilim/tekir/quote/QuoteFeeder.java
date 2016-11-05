/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Feeder(icon = "fa fa-book")
public class QuoteFeeder extends AbstractFeeder<Quote>{

    @Inject
    private Identity identity;
    
    @Override
    public void feed(Quote entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void feed(@Observes @FeatureQualifier(feauture = QuoteFeature.class) @After VoucherStateChange event) {

        //FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
        if (event.getPayload() instanceof Quote) {

            Quote entity = (Quote) event.getPayload();

            //FIXME: Bu pointer işi için Util'e gitmek lazım ( FeautureUtils )
            FeaturePointer voucherPointer = new FeaturePointer();
            voucherPointer.setBusinessKey(entity.getVoucherNo());
            voucherPointer.setPrimaryKey(entity.getId());
            voucherPointer.setFeature(entity.getClass().getSimpleName());

            FeaturePointer contactPointer = new FeaturePointer();
            contactPointer.setBusinessKey(entity.getAccount().getName());
            contactPointer.setPrimaryKey(entity.getAccount().getId());
            contactPointer.setFeature(entity.getAccount().getClass().getSimpleName());

            
            sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getVoucherNo(), getMessage(event), voucherPointer, contactPointer);
        }
    }

    
    public void feed(@Observes @EntityQualifier(entity = Quote.class) @After EntityChangeEvent event) {
        
        //Update işlemleri için feed etmek aslında StateChange için sorun yaratacak :(
        if( event.getAction() == EntityChangeAction.UPDATE ) return;
        
        //FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
        if (event.getEntity() instanceof Quote) {

            Quote entity = (Quote) event.getEntity();

            //FIXME: Bu pointer işi için Util'e gitmek lazım ( FeautureUtils )
            FeaturePointer voucherPointer = new FeaturePointer();
            voucherPointer.setBusinessKey(entity.getVoucherNo());
            voucherPointer.setPrimaryKey(entity.getId());
            voucherPointer.setFeature(entity.getClass().getSimpleName());

            FeaturePointer contactPointer = new FeaturePointer();
            contactPointer.setBusinessKey(entity.getAccount().getName());
            contactPointer.setPrimaryKey(entity.getAccount().getId());
            contactPointer.setFeature(entity.getAccount().getClass().getSimpleName());

            
            sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getVoucherNo(), getMessage(event), voucherPointer, contactPointer);
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
                    return "Quote created";
                case "CLOSE":
                    return "Quote Won. Congrats!";
                case "LOST":
                    return "Quote lost! " + event.getPayload().getStateReason();
                case "CANCELED":
                    return "Quote canceled. " + event.getPayload().getStateReason();
                default:
                    return "Quote created";
            }
    }
    
    
    protected String getMessage( EntityChangeEvent event ){
        switch (event.getAction()) {
                case INSERT:
                    return "Quote created";
                case UPDATE:
                    return "Quote updated";
                case DELETE:
                    return "Quote deleted";
                default: return "";
            }
    }
}
