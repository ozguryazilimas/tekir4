/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.quote.reminder;

import com.ozguryazilim.tekir.quote.config.QuotePages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

/**
 *
 * @author oyas
 */
@CommandEditor(command = QuoteReminderCommand.class, page = QuotePages.QuoteReminderCommand.class)
public class QuoteReminderCommandEditor extends CommandEditorBase<QuoteReminderCommand>{

    @Override
    public QuoteReminderCommand createNewCommand() {
        QuoteReminderCommand result = new QuoteReminderCommand();
        
        result.setInterval("3d");
        
        return result;
    }
    
}
