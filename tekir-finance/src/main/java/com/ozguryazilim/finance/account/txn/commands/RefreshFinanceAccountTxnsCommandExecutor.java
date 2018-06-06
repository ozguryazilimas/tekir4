package com.ozguryazilim.finance.account.txn.commands;

import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@CommandExecutor(command = RefreshFinanceAccountTxnsCommand.class)
public class RefreshFinanceAccountTxnsCommandExecutor extends
    AbstractCommandExecuter<RefreshFinanceAccountTxnsCommand> {

    @Inject
    private Event<RefreshFinanceAccountTxnsEvent> refreshFinanceAccountTxnsEvent;

    @Override
    public void execute(RefreshFinanceAccountTxnsCommand command) {
        refreshFinanceAccountTxnsEvent.fire(new RefreshFinanceAccountTxnsEvent(
            command.getBeginDate(), command.getEndDate()));
    }
}
