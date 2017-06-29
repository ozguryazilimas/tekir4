/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.sales.reminder;

import com.ozguryazilim.tekir.order.reminder.OrderReminderCommandProperty;
import com.ozguryazilim.telve.messagebus.command.AbstractStorableCommand;

/**
 * Satış Siparişi Hatırlatma komutu.
 * 
 * Zamanlanmış görev olarak çalışacak. 
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-28
 * @see AbstractStorableCommand
 * @see OrderReminderCommandProperty
 */
public class SalesOrderReminderCommand extends AbstractStorableCommand{
    
    /**
     * Satış siparişi açıldıktan sonra kapanmadıysa hatırlatma için geçecek süre
     */
    private String interval;
    
    /**
     * Satış siparişinin yaklaşan veya geçen siparişler için özelliği
     */
    private OrderReminderCommandProperty property;
    
    /**
     * Satış Siparişi özelliğinin alınması
     * @return {@link SalesOrderReminderCommand#property}
     */
    public OrderReminderCommandProperty getProperty() {
        return property;
    }
    
    /**
     * Satış Siparişi özelliğinin ayarlanması
     * @param interval {@link SalesOrderReminderCommand#interval}
     */
    public void setProperty(OrderReminderCommandProperty property) {
        this.property = property;
    }
    
    /**
     * Geçecek sürenin alınması
     * @return {@link SalesOrderReminderCommand#interval}
     */
    public String getInterval() {
        return interval;
    }

    /**
     * Geçecek sürenin ayarlanması
     * @param interval {@link SalesOrderReminderCommand#interval}
     */
    public void setInterval(String interval) {
        this.interval = interval;
    }
}
