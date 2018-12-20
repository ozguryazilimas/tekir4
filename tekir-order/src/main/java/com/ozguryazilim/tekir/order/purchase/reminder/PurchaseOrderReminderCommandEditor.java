package com.ozguryazilim.tekir.order.purchase.reminder;

import com.ozguryazilim.tekir.order.config.OrderPages;
import com.ozguryazilim.tekir.order.reminder.OrderReminderCommandType;
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
     * tip varsayılanda UPCOMING olarak {@link PurchaseOrderReminderCommand#setType(com.ozguryazilim.tekir.order.sales.reminder.OrderReminderCommandType) }
     * metodları ile ayarlanmıştır.
     * 
     * @return Oluşturulan satın alım siparişi komutu
     * @see PurchaseOrderReminderCommand
     */
    @Override
    public PurchaseOrderReminderCommand createNewCommand() {
        PurchaseOrderReminderCommand result = new PurchaseOrderReminderCommand();
        
        result.setInterval("3d");
        result.setType(OrderReminderCommandType.UPCOMING);
        
        return result;
    }
    
}
