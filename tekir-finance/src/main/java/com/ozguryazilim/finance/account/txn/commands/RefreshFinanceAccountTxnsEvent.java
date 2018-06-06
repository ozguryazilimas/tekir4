package com.ozguryazilim.finance.account.txn.commands;

import com.ozguryazilim.telve.reports.ReportDate;

public class RefreshFinanceAccountTxnsEvent {

    private ReportDate beginDate;
    private ReportDate endDate;

    public RefreshFinanceAccountTxnsEvent(ReportDate beginDate,
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
