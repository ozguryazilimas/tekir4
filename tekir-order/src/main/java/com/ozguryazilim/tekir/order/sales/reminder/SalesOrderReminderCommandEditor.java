/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.sales.reminder;

import com.ozguryazilim.tekir.order.config.OrderPages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

/**
 * Satış Siparişi Hatırlatması için komut editörü.
 *
 * Satış Siparişi komutunun oluşturulduğu ve düzenlendiği sınıf.
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-28
 * @see CommandEditor
 * @see CommandEditorBase
 * @see SalesOrderReminderCommand
 * @see OpportunityPages
 */
@CommandEditor(command = SalesOrderReminderCommand.class, page = OrderPages.Sales.SalesOrderReminderCommand.class)
public class SalesOrderReminderCommandEditor extends CommandEditorBase<SalesOrderReminderCommand>{

    /**
     * Satış Siparişi komutunu oluşturur.
     * 
     * {@inheritDoc}
     * 
     * Geçecek varsayılan süre 3d olarak {@link SalesOrderReminderCommand#setInterval(java.lang.String)}
     * metodu ile ayarlanmıştır.
     * 
     * @return Oluşturulan satış siparişi komutu
     * @see SalesOrderReminderCommand
     */
    @Override
    public SalesOrderReminderCommand createNewCommand() {
        SalesOrderReminderCommand result = new SalesOrderReminderCommand();
        
        result.setInterval("3d");
        
        return result;
    }
    
}
