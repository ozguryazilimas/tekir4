package com.ozguryazilim.tekir.order;

import com.ozguryazilim.tekir.account.commands.SaveAccountTxnsCommand;
import com.ozguryazilim.tekir.entities.Order;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messagebus.command.CommandSender;
import javax.inject.Inject;

public abstract class OrderTxnRefresher<E extends Order> {

    @Inject
    CommandSender commandSender;

    public void sendAccountTxnSaveCommand(E entity) {
        FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

        SaveAccountTxnsCommand saveCommand = new SaveAccountTxnsCommand(voucherPointer,
            entity.getAccount(), entity.getInfo(), entity.getTags(), Boolean.FALSE,
            getProcessType() == ProcessType.PURCHASE, entity.getCurrency(), entity.getTotal(),
            entity.getLocalAmount(), entity.getDate(), entity.getOwner(),
            entity.getProcess().getProcessNo(), entity.getState().toString(),
            entity.getStateReason(), entity.getTopic());

        commandSender.sendCommand(saveCommand);
    }

    protected abstract ProcessType getProcessType();
}