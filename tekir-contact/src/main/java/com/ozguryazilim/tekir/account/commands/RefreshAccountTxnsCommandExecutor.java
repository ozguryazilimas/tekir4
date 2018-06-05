package com.ozguryazilim.tekir.account.commands;

import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@CommandExecutor(command = RefreshAccountTxnsCommand.class)
public class RefreshAccountTxnsCommandExecutor extends
    AbstractCommandExecuter<RefreshAccountTxnsCommand> {

    @Inject
    private Event<RefreshAccountTxnsEvent> refreshAccountTxnsEvent;

    @Override
    public void execute(RefreshAccountTxnsCommand command) {
        refreshAccountTxnsEvent.fire(new RefreshAccountTxnsEvent());
    }
}
