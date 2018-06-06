package com.ozguryazilim.tekir.voucher.group.commands;

import com.ozguryazilim.tekir.entities.VoucherGroupTxn;
import com.ozguryazilim.tekir.voucher.group.VoucherGroupTxnRepository;
import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;
import javax.inject.Inject;

@CommandExecutor(command = SaveVoucherGroupTxnsCommand.class)
public class SaveVoucherGroupTxnsCommandExecutor extends
    AbstractCommandExecuter<SaveVoucherGroupTxnsCommand> {

    @Inject
    private VoucherGroupTxnRepository repository;

    @Override
    public void execute(SaveVoucherGroupTxnsCommand command) {
        VoucherGroupTxn txn = new VoucherGroupTxn();

        txn.setDate(command.getDate());
        txn.setFeature(command.getFeaturePointer());
        txn.setGroup(command.getVoucherGroup());
        txn.setOwner(command.getOwner());
        txn.setState(command.getVoucherState());
        txn.setTopic(command.getTopic());

        repository.save(txn);
    }
}
