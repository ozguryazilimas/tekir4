/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.purchase.reminder;

import com.ozguryazilim.tekir.order.config.OrderPages;
import com.ozguryazilim.tekir.order.reminder.OrderReminderCommandProperty;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

/**
 * Satın Alım Siparişi Hatırlatması için komut editörü.
 *
 * Satın Alım Siparişi komutunun oluşturulduğu ve düzenlendiği sınıf.
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-28
 * @see CommandEditor
 * @see CommandEditorBase
 * @see PurchaseOrderReminderCommand
 * @see OpportunityPages
 */
@CommandEditor(command = PurchaseOrderReminderCommand.class, page = OrderPages.Purchase.PurchaseOrderReminderCommand.class)
public class PurchaseOrderReminderCommandEditor extends CommandEditorBase<PurchaseOrderReminderCommand>{

    /**
     * Satın Alım Siparişi komutunu oluşturur.
     * 
     * {@inheritDoc}
     * 
     * Geçecek varsayılan süre 3d olarak {@link PurchaseOrderReminderCommand#setInterval(java.lang.String)}
     * Özellik varsayılanda UPCOMING olarak {@link PurchaseOrderReminderCommand#setProperty(com.ozguryazilim.tekir.order.sales.reminder.OrderReminderCommandProperty) }
     * metodları ile ayarlanmıştır.
     * 
     * @return Oluşturulan satın alım siparişi komutu
     * @see PurchaseOrderReminderCommand
     */
    @Override
    public PurchaseOrderReminderCommand createNewCommand() {
        PurchaseOrderReminderCommand result = new PurchaseOrderReminderCommand();
        
        result.setInterval("3d");
        result.setProperty(OrderReminderCommandProperty.UPCOMING);
        
        return result;
    }
    
}
