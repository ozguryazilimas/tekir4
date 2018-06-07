package com.ozguryazilim.finance.account.txn.commands;

import com.ozguryazilim.finance.account.txn.FinanceAccountTxnRepository;
import com.ozguryazilim.tekir.entities.FinanceAccountTxn;
import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

@CommandExecutor(command = SaveFinanceAccounTxnsCommand.class)
public class SaveFinanceAccountTxnsCommandExecutor extends
    AbstractCommandExecuter<SaveFinanceAccounTxnsCommand> {

    @Inject
    private FinanceAccountTxnRepository repository;

    @Override
    public void execute(SaveFinanceAccounTxnsCommand command) {
        FinanceAccountTxn txn = new FinanceAccountTxn();

        List<String> txnTags = new ArrayList<>();
        if (command.getTags() != null) {
            txnTags.addAll(command.getTags());
        }

        txn.setAccount(command.getAccount());
        txn.setAmount(command.getAmount());
        txn.setLocalAmount(command.getLocalAmount());
        txn.setCurrency(command.getCurrency());
        txn.setDebit(command.getDebit());
        txn.setDate(command.getDate());
        txn.setFeature(command.getFeature());
        txn.setTags(txnTags);
        txn.setInfo(command.getInfo());
        txn.setOwner(command.getOwner());
        txn.setProcessId(command.getProcessId());
        txn.setStatus(command.getStatus());
        txn.setStatusReason(command.getStatusReason());
        txn.setContact(command.getContact());

        repository.save(txn);
    }
}
