/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.feed.housekeep;

import com.ozguryazilim.tekir.feed.FeedRepository;
import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;
import com.ozguryazilim.telve.utils.DateUtils;
import java.util.Date;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oyas
 */
@CommandExecutor(command = FeedClearCommand.class)
public class FeedClearCommandExecutor extends AbstractCommandExecuter<FeedClearCommand>{

    private static final Logger LOG = LoggerFactory.getLogger(FeedClearCommandExecutor.class);
    
    @Inject
    private FeedRepository feedRepository;
    
    @Override
    public void execute(FeedClearCommand command) {
        
        Date dt = DateUtils.getDateBeforePeriod(command.getInterval(), new Date());
        
        LOG.info("Feeds older than {} will be deleted.", dt );
        
        feedRepository.deleteBeforeDate(dt);
        
    }
    
}
