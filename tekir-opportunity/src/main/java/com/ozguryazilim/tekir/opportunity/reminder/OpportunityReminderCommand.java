package com.ozguryazilim.tekir.opportunity.reminder;

import com.ozguryazilim.telve.messagebus.command.AbstractStorableCommand;

/**
 * Fırsat Hatırlatma komutu.
 * 
 * Zamanlanmış görev olarak çalışacak. 
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-20
 * @see AbstractStorableCommand
 */
public class OpportunityReminderCommand extends AbstractStorableCommand{
    
    /**
     * Fırsat açıldıktan sonra kapanmadıysa hatırlatma için geçecek süre
     */
    private String interval;
    
    /**
     * Geçecek sürenin alınması
     * @return {@link OpportunityReminderCommand#interval}
     */
    public String getInterval() {
        return interval;
    }

    /**
     * Geçecek sürenin ayarlanması
     * @param interval {@link OpportunityReminderCommand#interval}
     */
    public void setInterval(String interval) {
        this.interval = interval;
    }
}
