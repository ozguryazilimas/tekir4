package com.ozguryazilim.tekir.account.reports;

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

public class AccountTxnFilter implements Serializable {
	
	private String code;
	private String name;
	private CorporationType corporationType;
	private ContactCategory contactCategory;
	private Industry industry;
	private Territory territory;
	private ReportDate startDate;
	private ReportDate endDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CorporationType getCorporationType() {
		return corporationType;
	}

	public void setCorporationType(CorporationType corporationType) {
		this.corporationType = corporationType;
	}

	public ContactCategory getContactCategory() {
		return contactCategory;
	}

	public void setContactCategory(ContactCategory contactCategory) {
		this.contactCategory = contactCategory;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
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