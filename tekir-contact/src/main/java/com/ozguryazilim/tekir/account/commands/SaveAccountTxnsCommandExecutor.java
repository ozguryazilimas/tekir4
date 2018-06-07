package com.ozguryazilim.tekir.account.commands;

import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.telve.messagebus.command.AbstractCommandExecuter;
import com.ozguryazilim.telve.messagebus.command.CommandExecutor;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

@CommandExecutor(command = SaveAccountTxnsCommand.class)
public class SaveAccountTxnsCommandExecutor extends
    AbstractCommandExecuter<SaveAccountTxnsCommand> {

    @Inject
    private AccountTxnRepository repository;

    @Override
    public void execute(SaveAccountTxnsCommand command) {
        AccountTxn txn = new AccountTxn();

        List<String> txnTags = new ArrayList<>();
        if (command.getTags() != null) {
            txnTags.addAll(command.getTags());
        }

        txn.setAccount(command.getAccount());
        txn.setAccountable(command.getAccountable());
        txn.setAmount(command.getAmount());
        txn.setLocalAmount(command.getLocalAmount());
        txn.setCurrency(command.getCurrency());
        txn.setDebit(command.getDebit());
        txn.setDate(command.getDate());
        txn.setFeature(command.getFeaturePointer());
        txn.setTags(txnTags);
        txn.setInfo(command.getInfo());
        txn.setOwner(command.getOwner());
        txn.setProcessId(command.getProcessId());
        txn.setStatus(command.getStatus());
        txn.setStatusReason(command.getStatusReason());
        txn.setTopic(command.getTopic());

        repository.save(txn);
    }
}
