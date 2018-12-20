package com.ozguryazilim.tekir.order.sales.reminder;

import com.ozguryazilim.tekir.order.reminder.OrderReminderCommandType;
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
     * Tip varsayılanda UPCOMING olarak {@link SalesOrderReminderCommand#setType(com.ozguryazilim.tekir.order.sales.reminder.OrderReminderCommandType) }
     * metodları ile ayarlanmıştır.
     * 
     * @return Oluşturulan satış siparişi komutu
     * @see SalesOrderReminderCommand
     */
    @Override
    public SalesOrderReminderCommand createNewCommand() {
        SalesOrderReminderCommand result = new SalesOrderReminderCommand();
        
        result.setInterval("3d");
        result.setType(OrderReminderCommandType.UPCOMING);
        
        return result;
    }
    
}
