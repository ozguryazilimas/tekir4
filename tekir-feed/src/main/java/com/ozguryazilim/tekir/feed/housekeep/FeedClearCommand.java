/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
