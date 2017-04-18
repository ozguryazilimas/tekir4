/*
 *   Copyleft 2013 IOVA SOFTWARE
 *   
 *  Distributable under LGPL license.
 *  See terms of license at gnu.org.
 *  http://www.gnu.org/licenses/lgpl.html
 *   
 *  www.openohs.com
 *  www.iova.com.tr
 */

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
 *
 */
public class AccountStatusFilter implements Serializable {

	private String code;
	private String name;
	private CorporationType corporationType;
	private ContactCategory contactCategory;
	private Industry industry;
	private Territory territory;
    private ReportDate date;
    
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
	public ReportDate getDate() {
		return date;
	}
	public void setDate(ReportDate date) {
		this.date = date;
	}          
}
