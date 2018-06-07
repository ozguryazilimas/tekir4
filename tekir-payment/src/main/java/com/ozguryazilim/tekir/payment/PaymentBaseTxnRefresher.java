package com.ozguryazilim.tekir.payment;

import com.ozguryazilim.tekir.account.commands.SaveAccountTxnsCommand;
import com.ozguryazilim.tekir.entities.PaymentBase;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.voucher.group.commands.SaveVoucherGroupTxnsCommand;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messagebus.command.CommandSender;
import javax.inject.Inject;

public abstract class PaymentBaseTxnRefresher<E extends PaymentBase> {

    @Inject
    CommandSender commandSender;

    public void sendAccountTxnSaveCommand(E entity) {
        FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

        SaveAccountTxnsCommand saveCommand = new SaveAccountTxnsCommand(voucherPointer,
            entity.getAccount(), entity.getInfo(), entity.getTags(), Boolean.FALSE,
            getProcessType() == ProcessType.PURCHASE, entity.getCurrency(), entity.getAmount(),
            entity.getLocalAmount(), entity.getDate(), entity.getOwner(),
            entity.getProcess().getProcessNo(), entity.getState().toString(),
            entity.getStateReason(), entity.getTopic());

        commandSender.sendCommand(saveCommand);
    }

    public void sendVoucherGroupTxnSaveCommand(E entity) {
        FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

        SaveVoucherGroupTxnsCommand saveCommand = new SaveVoucherGroupTxnsCommand(
            entity.getGroup(),
            voucherPointer,
            entity.getTopic(),
            entity.getDate(),
            entity.getOwner(),
            entity.getState());

        commandSender.sendCommand(saveCommand);
    }

    protected abstract ProcessType getProcessType();
}
