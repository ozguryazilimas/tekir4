/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.reminder;

import com.ozguryazilim.telve.messagebus.command.AbstractStorableCommand;

/**
 * Aktivite Hatırlatma komutu.
 * 
 * Zamanlanmış görev olarak çalışacak. 
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-21
 * @see AbstractStorableCommand
 */
public class ActivityReminderCommand extends AbstractStorableCommand{
    
    /**
     * Aktivite açıldıktan sonra kapanmadıysa hatırlatma için geçecek süre
     */
    private String interval;
    
    /**
     * Geçecek sürenin alınması
     * @return {@link ActivityReminderCommand#interval}
     */
    public String getInterval() {
        return interval;
    }

    /**
     * Geçecek sürenin ayarlanması
     * @param interval {@link ActivityReminderCommand#interval}
     */
    public void setInterval(String interval) {
        this.interval = interval;
    }
}
