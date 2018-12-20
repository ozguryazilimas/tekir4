package com.ozguryazilim.tekir.feed.housekeep;

import com.ozguryazilim.telve.messagebus.command.AbstractStorableCommand;

/**
 * 
 * Belirlenen tarihten eski feedleri temizlemek için komut.
 * 
 * Gün sonlarında çalıştırılarak eski feedleri temizler.
 * 
 * 
 * @author Hakan Uygun
 */
public class FeedClearCommand extends AbstractStorableCommand{
    
    /**
     * Ne kadar önceki değere bakacak?
     */
    private String interval;

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
    
    
}
