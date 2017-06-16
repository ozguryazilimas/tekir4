/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.telve.auth.Identity;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

/**
 * 
 * @author Hakan Uygun
 */
@Feeder
public class PersonFeeder extends AbstractFeeder<Person>{

    @Inject
    private Identity identity;
    
    
    public void feed(@Observes(during = TransactionPhase.AFTER_COMPLETION) Person entity) {
       /*  FIXME: Burayı iyi bir düşünelim.
        FeaturePointer contactPointer = new FeaturePointer();
        contactPointer.setBusinessKey(entity.getName());
        contactPointer.setPrimaryKey(entity.getId());
        contactPointer.setFeature("Person");
        
        sendFeed("FEED", getClass().getSimpleName(), identity.getLoginName(), "Person hede hede", "Uuuuu <a href='#'>Başka bir yer</a> linki verelim bakalım. burası daha da uzun", contactPointer, null );
        */
    }
    
    
}
