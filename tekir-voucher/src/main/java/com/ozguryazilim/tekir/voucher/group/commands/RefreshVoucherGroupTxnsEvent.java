package com.ozguryazilim.tekir.voucher.group.commands;

import com.ozguryazilim.telve.reports.ReportDate;

public class RefreshVoucherGroupTxnsEvent {

    private ReportDate beginDate;
    private ReportDate endDate;

    public RefreshVoucherGroupTxnsEvent(ReportDate beginDate,
        ReportDate endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public ReportDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(ReportDate beginDate) {
        this.beginDate = beginDate;
    }

    public ReportDate getEndDate() {
        return endDate;
    }

    public void setEndDate(ReportDate endDate) {
        this.endDate = endDate;
    }
}