/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Feeder(icon = "fa fa-book")
public class OpportunityFeeder extends AbstractFeeder<Opportunity>{

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
        
        
        String subject;
        switch ( entity.getStatus() ){
            case OPEN : subject = "Opportunity created"; break;
            case WON : subject = "Opportunity Won. Congrats!"; break;
            case LOST : subject = "Opportunity lost!"; break;
            case CANCELED : subject = "Opportunity canceled"; break;
            default:
                subject = "Opportunity created"; break;
        }
        
        sendFeed(entity.getStatus().name(), "OpportunityFeeder", identity.getLoginName(), entity.getTopic(), subject, voucherPointer, contactPointer );
    }
    
}
