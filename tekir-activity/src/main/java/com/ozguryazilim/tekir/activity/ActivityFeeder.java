/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.telve.entities.FeaturePointer;

/**
 *
 * @author Hakan Uygun
 */
@Feeder(icon = "fa fa-act")
public class ActivityFeeder extends AbstractFeeder<Activity>{

    @Override
    public void feed(Activity entity) {
        
        FeaturePointer contactPointer = new FeaturePointer();
        contactPointer.setBusinessKey(entity.getSubject());
        contactPointer.setPrimaryKey(entity.getId());
        contactPointer.setFeature("Activity");
        
        sendFeed("ACTIVITY", "ActivityFeeder", entity.getAssignee(), entity.getSubject(), entity.getBody(), contactPointer, null );
    }
    
}
