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
