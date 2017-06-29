/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.purchase.reminder;

import com.ozguryazilim.tekir.order.reminder.OrderReminderCommandProperty;
import com.ozguryazilim.telve.messagebus.command.AbstractStorableCommand;

/**
 * Satın Alım Siparişi Hatırlatma komutu.
 * 
 * Zamanlanmış görev olarak çalışacak. 
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-28
 * @see AbstractStorableCommand
 * @see OrderReminderCommandProperty
 */
public class PurchaseOrderReminderCommand extends AbstractStorableCommand{
    
    /**
     * Satın alım siparişi açıldıktan sonra kapanmadıysa hatırlatma için geçecek süre
     */
    private String interval;
    
    /**
     * Satın Alım siparişinin yaklaşan veya geçen siparişler için özelliği
     */
    private OrderReminderCommandProperty property;
    
    /**
     * Satın Alım Siparişi özelliğinin alınması
     * @return {@link SalesOrderReminderCommand#property}
     */
    public OrderReminderCommandProperty getProperty() {
        return property;
    }
    
    /**
     * Satın Alım Siparişi özelliğinin ayarlanması
     * @param interval {@link SalesOrderReminderCommand#interval}
     */
    public void setProperty(OrderReminderCommandProperty property) {
        this.property = property;
    }
    
    /**
     * Geçecek sürenin alınması
     * @return {@link PurchaseOrderReminderCommand#interval}
     */
    public String getInterval() {
        return interval;
    }

    /**
     * Geçecek sürenin ayarlanması
     * @param interval {@link PurchaseOrderReminderCommand#interval}
     */
    public void setInterval(String interval) {
        this.interval = interval;
    }
}
