package com.ozguryazilim.tekir.account.commands;

import com.ozguryazilim.telve.reports.ReportDate;

public class RefreshAccountTxnsEvent {

    private ReportDate beginDate;
    private ReportDate endDate;

    public RefreshAccountTxnsEvent(ReportDate beginDate,
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