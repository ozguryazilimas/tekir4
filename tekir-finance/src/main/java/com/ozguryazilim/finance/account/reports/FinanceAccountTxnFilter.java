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

	private Contact account;
	private String code;
	private ReportDate endDate;
	private FinanceAccount financeAccount;
	private ReportDate startDate;

	public FinanceAccountTxnFilter (ReportDate startDate, ReportDate
			endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Contact getAccount() {
		return account;
	}

	public void setAccount(Contact account) {
		this.account = account;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
