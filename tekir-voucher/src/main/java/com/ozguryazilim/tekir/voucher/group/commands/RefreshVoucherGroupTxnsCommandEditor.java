package com.ozguryazilim.tekir.voucher.group.commands;

import com.ozguryazilim.tekir.voucher.config.VoucherPages;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditor;
import com.ozguryazilim.telve.messagebus.command.ui.CommandEditorBase;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.reports.ReportDate;

@CommandEditor(command = RefreshVoucherGroupTxnsCommand.class,
    page = VoucherPages.Group.RefreshVoucherGroupTxnsCommand.class)
public class RefreshVoucherGroupTxnsCommandEditor extends
    CommandEditorBase<RefreshVoucherGroupTxnsCommand> {

    @Override
    public RefreshVoucherGroupTxnsCommand createNewCommand() {
        return new RefreshVoucherGroupTxnsCommand(
            new ReportDate(DateValueType.TenDaysBefore),
            new ReportDate(DateValueType.Today));
    }
}
