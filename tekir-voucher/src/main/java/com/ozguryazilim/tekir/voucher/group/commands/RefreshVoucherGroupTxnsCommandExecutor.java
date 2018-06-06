package com.ozguryazilim.tekir.voucher.group.commands;

import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@CommandExecutor(command = RefreshVoucherGroupTxnsCommand.class)
public class RefreshVoucherGroupTxnsCommandExecutor
    extends AbstractCommandExecuter<RefreshVoucherGroupTxnsCommand> {

    @Inject
    private Event<RefreshVoucherGroupTxnsEvent> refreshVoucherGroupTxnsEvent;

    @Override
    public void execute(RefreshVoucherGroupTxnsCommand command) {
        refreshVoucherGroupTxnsEvent
            .fire(new RefreshVoucherGroupTxnsEvent(command.getBeginDate(), command.getEndDate()));
    }

}
