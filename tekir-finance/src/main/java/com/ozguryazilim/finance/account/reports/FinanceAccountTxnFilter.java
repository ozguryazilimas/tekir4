package com.ozguryazilim.finance.account.reports;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.telve.reports.ReportDate;
import java.io.Serializable;

/**
 * 
 * @author Ceyhun Onur
 */

public class FinanceAccountTxnFilter implements Serializable {

	private ReportDate startDate;
	private ReportDate endDate;
	private FinanceAccount financeAccount;
        private Contact account;
        
        public Contact getAccount() {
            return account;
        }
        
        public void setAccount(Contact account) {
            this.account = account;
        }
        
	public FinanceAccount getFinanceAccount() {
		return financeAccount;
	}
	public void setFinanceAccount(FinanceAccount financeAccount) {
		this.financeAccount = financeAccount;
	}

	public ReportDate getStartDate() {
		return startDate;
	}

	public void setStartDate(ReportDate startDate) {
		this.startDate = startDate;
	}

	public ReportDate getEndDate() {
		return endDate;
	}

	public void setEndDate(ReportDate endDate) {
		this.endDate = endDate;
	}
}
