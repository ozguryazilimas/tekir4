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
