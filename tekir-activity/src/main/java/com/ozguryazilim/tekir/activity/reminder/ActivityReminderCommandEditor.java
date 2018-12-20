package com.ozguryazilim.tekir.activity.reminder;

import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

/**
 * Aktivite Hatırlatması için komut editörü.
 *
 * Aktivite komutunun oluşturulduğu ve düzenlendiği sınıf.
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-21
 * @see CommandEditor
 * @see CommandEditorBase
 * @see ActivityReminderCommand
 * @see ActivityPages
 */
@CommandEditor(command = ActivityReminderCommand.class, page = ActivityPages.ActivityReminderCommand.class)
public class ActivityReminderCommandEditor extends CommandEditorBase<ActivityReminderCommand>{

    /**
     * Aktivite komutunu oluşturur.
     * 
     * {@inheritDoc}
     * 
     * Geçecek varsayılan süre 3d olarak {@link ActivityReminderCommand#setInterval(java.lang.String)}
     * metodu ile ayarlanmıştır.
     * 
     * @return Oluşturulan aktivite komutu
     * @see ActivityReminderCommand
     */
    @Override
    public ActivityReminderCommand createNewCommand() {
        ActivityReminderCommand result = new ActivityReminderCommand();
        
        result.setInterval("3d");
        
        return result;
    }
    
}
