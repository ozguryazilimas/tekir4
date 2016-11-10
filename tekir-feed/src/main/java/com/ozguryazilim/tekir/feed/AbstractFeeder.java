/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.feed;

import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messagebus.command.CommandSender;
import javax.inject.Inject;

/**
 * Feeder'lar için alt yapı
 * 
 * @author Hakan Uygun
 */
public abstract class AbstractFeeder<E> {
   
    
    @Inject
    private CommandSender commandSender;
    
    protected void sendFeed(String type, String feeder, String user, String subject, String body, FeaturePointer basePointer, FeaturePointer relatedPointer){
        FeedCommand command = new FeedCommand(type, feeder, user, subject, body, basePointer, relatedPointer);
        commandSender.sendCommand(command);
    }
    
}
