/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity.reminder;

import com.ozguryazilim.tekir.opportunity.config.OpportunityPages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

/**
 * Fırsat Hatırlatması için komut editörü.
 *
 * Fırsat komutunun oluşturulduğu ve düzenlendiği sınıf.
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-20
 * @see CommandEditor
 * @see CommandEditorBase
 * @see OpportunityReminderCommand
 * @see OpportunityPages
 */
@CommandEditor(command = OpportunityReminderCommand.class, page = OpportunityPages.OpportunityReminderCommand.class)
public class OpportunityReminderCommandEditor extends CommandEditorBase<OpportunityReminderCommand>{

    /**
     * Fırsat komutunu oluşturur.
     * 
     * {@inheritDoc}
     * 
     * Geçecek varsayılan süre 3d olarak {@link OpportunityReminderCommand#setInterval(java.lang.String)}
     * metodu ile ayarlanmıştır.
     * 
     * @return Oluşturulan fırsat komutu
     * @see OpportunityReminderCommand
     */
    @Override
    public OpportunityReminderCommand createNewCommand() {
        OpportunityReminderCommand result = new OpportunityReminderCommand();
        
        result.setInterval("3d");
        
        return result;
    }
    
}
