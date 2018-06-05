package com.ozguryazilim.tekir.voucher.group.commands;

import com.ozguryazilim.tekir.voucher.config.VoucherPages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;

@CommandEditor(command = RefreshVoucherGroupTxnsCommand.class,
    page = VoucherPages.Group.VoucherGroupTxnsRefreshCommand.class)
public class VoucherGroupTxnsRefreshCommandEditor extends
    CommandEditorBase<RefreshVoucherGroupTxnsCommand> {

    @Override
    public RefreshVoucherGroupTxnsCommand createNewCommand() {
        return new RefreshVoucherGroupTxnsCommand();
    }
}
