package com.ozguryazilim.finance.account.txn.commands;

import com.ozguryazilim.finance.config.FinancePages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

@CommandEditor(command = RefreshFinanceAccountTxnsCommand.class,
    page = FinancePages.RefreshFinanceAccountTxnsCommand.class)
public class RefreshFinanceAccountTxnsCommandEditor extends
    CommandEditorBase<RefreshFinanceAccountTxnsCommand> {

    @Override
    public RefreshFinanceAccountTxnsCommand createNewCommand() {
        return new RefreshFinanceAccountTxnsCommand();
    }
}
