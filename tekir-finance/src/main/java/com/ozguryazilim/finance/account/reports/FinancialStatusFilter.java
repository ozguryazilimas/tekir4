package com.ozguryazilim.finance.account.reports;

import com.ozguryazilim.tekir.entities.AccountType;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.telve.reports.ReportDate;
import java.io.Serializable;

public class FinancialStatusFilter implements Serializable {

    private FinanceAccount financeAccount;
    private String code;
    private ReportDate date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ReportDate getDate() {
        return date;
    }

    public void setDate(ReportDate date) {
        this.date = date;
    }

    public FinanceAccount getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(FinanceAccount financeAccount) {
        this.financeAccount = financeAccount;
    }

}
