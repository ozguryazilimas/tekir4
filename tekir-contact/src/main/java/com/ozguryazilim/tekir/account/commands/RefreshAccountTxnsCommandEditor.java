package com.ozguryazilim.tekir.account.commands;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

@CommandEditor(command = RefreshAccountTxnsCommand.class,
    page = ContactPages.RefreshAccountTxnsCommand.class)
public class RefreshAccountTxnsCommandEditor extends CommandEditorBase<RefreshAccountTxnsCommand> {

    @Override
    public RefreshAccountTxnsCommand createNewCommand() {
        return new RefreshAccountTxnsCommand();
    }
}
