/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.lead.reminder;

import com.ozguryazilim.tekir.lead.config.LeadPages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

/**
 * İpucu Hatırlatması için komut editörü.
 *
 * İpucu komutunun oluşturulduğu ve düzenlendiği sınıf.
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-19
 * @see CommandEditor
 * @see CommandEditorBase
 * @see LeadReminderCommand
 * @see LeadPages
 */
@CommandEditor(command = LeadReminderCommand.class, page = LeadPages.LeadReminderCommand.class)
public class LeadReminderCommandEditor extends CommandEditorBase<LeadReminderCommand>{

    /**
     * İpucu komutunu oluşturur.
     * 
     * {@inheritDoc}
     * 
     * Geçecek varsayılan süre 3d olarak {@link LeadReminderCommand#setInterval(java.lang.String)}
     * metodu ile ayarlanmıştır.
     * 
     * @return Oluşturulan ipucu komutu
     * @see LeadReminderCommand
     */
    @Override
    public LeadReminderCommand createNewCommand() {
        LeadReminderCommand result = new LeadReminderCommand();
        
        result.setInterval("3d");
        
        return result;
    }
    
}
