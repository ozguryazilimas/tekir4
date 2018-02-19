package com.ozguryazilim.tekir.activity.email.imports.commands;

import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

/**
 *
 * @author oyas
 */
@CommandEditor(command = EMailFetchCommand.class, page = ActivityPages.EMailFetchCommand.class)
public class EMailFetchCommandEditor extends CommandEditorBase<EMailFetchCommand>{

    @Override
    public EMailFetchCommand createNewCommand() {
        return new EMailFetchCommand();
    }
    
}
