/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.quote.reminder;

import com.ozguryazilim.telve.messagebus.command.AbstractStorableCommand;

/**
 * Teklif Hatırlatma komutu.
 * 
 * Zamanlanmış görev olarak çalışacak. 
 * 
 * @author Hakan Uygun
 */
public class QuoteReminderCommand extends AbstractStorableCommand{
    /**
     * Teklif geçerlilik süresinden ne kadar önce hatırlatma gönderecek?
     */
    private String interval;

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
