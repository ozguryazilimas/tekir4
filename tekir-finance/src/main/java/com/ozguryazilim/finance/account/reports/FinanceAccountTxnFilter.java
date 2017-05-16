package com.ozguryazilim.finance.account.reports;

import com.ozguryazilim.tekir.entities.ContactCategory;
import com.ozguryazilim.tekir.entities.CorporationType;
import com.ozguryazilim.tekir.entities.Industry;
import com.ozguryazilim.tekir.entities.Territory;
import com.ozguryazilim.telve.reports.ReportDate;
import java.io.Serializable;

/**
 * 
 * @author Ceyhun Onur 
 */

public class FinanceAccountTxnFilter implements Serializable {
	
	private ReportDate startDate;
	private ReportDate endDate;

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