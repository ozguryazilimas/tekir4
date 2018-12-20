package com.ozguryazilim.tekir.feed;

import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messagebus.command.CommandSender;
import java.util.List;
import javax.inject.Inject;

/**
 * Feeder'lar için alt yapı
 * 
 * @author Hakan Uygun
 */
public abstract class AbstractFeeder<E> {
   
    
    @Inject
    private CommandSender commandSender;
    
    protected void sendFeed(String type, String feeder, String user, String subject, String body, List<FeaturePointer> mentions){
        FeedCommand command = new FeedCommand(type, feeder, user, subject, body, mentions);
        commandSender.sendCommand(command);
    }
    
}
