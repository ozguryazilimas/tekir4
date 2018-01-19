/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.sales.reminder;

import com.ozguryazilim.tekir.order.reminder.OrderReminderCommandType;
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
 * @see OrderReminderCommandType
 */
public class SalesOrderReminderCommand extends AbstractStorableCommand{
    
    /**
     * Satış siparişi açıldıktan sonra kapanmadıysa hatırlatma için geçecek süre
     */
    private String interval;
    
    /**
     * Satış siparişinin yaklaşan veya geçen siparişler durumu için tip
     */
    private OrderReminderCommandType type;
    
    /**
     * Satış Siparişi tipinin alınması
     * @return {@link SalesOrderReminderCommand#type}
     */
    public OrderReminderCommandType getType() {
        return type;
    }
    
    /**
     * Satış Siparişi tipinin ayarlanması
     * @param interval {@link SalesOrderReminderCommand#interval}
     */
    public void setType(OrderReminderCommandType type) {
        this.type = type;
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
