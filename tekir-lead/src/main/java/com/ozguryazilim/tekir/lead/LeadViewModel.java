package com.ozguryazilim.tekir.lead;

import java.util.Date;
import java.util.List;

import com.ozguryazilim.tekir.entities.LeadCategory;
import com.ozguryazilim.tekir.entities.LeadSource;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherViewModel;

public class LeadViewModel extends VoucherViewModel {

	private String relatedPersonName;
	private String relatedPersonSurname;
	private String relatedCompanyName;
	private String relatedPhone;
	private String relatedAddress;
	private String relatedEmail;
	private LeadSource leadSource;
	private LeadCategory leadCategory;

	public LeadViewModel(Long id, List<String> tags, String voucherNo, String info, String referenceNo, Date date,
						 String owner, VoucherState state, String stateReason, String stateInfo, Long groupId, String groupNo,
						 String topic, String relatedPersonName, String relatedPersonSurname, String relatedCompanyName,
						 String relatedPhone, String relatedAddress, String relatedEmail, Long leadSourceId, String leadSourceName,
						 Long leadCategoryId, String leadCategoryName) {
		super(id, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, groupId, groupNo,
				topic);
		this.relatedPersonName = relatedPersonName;
		this.relatedPersonSurname = relatedPersonSurname;
		this.relatedCompanyName = relatedCompanyName;
		this.relatedPhone = relatedPhone;
		this.relatedAddress = relatedAddress;
		this.relatedEmail = relatedEmail;

		leadSource = new LeadSource();
		leadSource.setId(leadSourceId);
		leadSource.setName(leadSourceName);

		leadCategory = new LeadCategory();
		leadCategory.setId(leadCategoryId);
		leadCategory.setName(leadCategoryName);
	}

	public LeadViewModel(Long id, List<String> tags, String voucherNo, String info, String referenceNo, Date date,
			String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic,
			String relatedPersonName, String relatedPersonSurname, String relatedCompanyName, String relatedPhone,
			String relatedAddress, String relatedEmail, Long leadSourceId, String leadSourceName, Long leadCategoryId,
			String leadCategoryName) {
		super(id, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
		this.relatedPersonName = relatedPersonName;
		this.relatedPersonSurname = relatedPersonSurname;
		this.relatedCompanyName = relatedCompanyName;
		this.relatedPhone = relatedPhone;
		this.relatedAddress = relatedAddress;
		this.relatedEmail = relatedEmail;
		
		leadSource = new LeadSource();
		leadSource.setId(leadSourceId);
		leadSource.setName(leadSourceName);

		leadCategory = new LeadCategory();
		leadCategory.setId(leadCategoryId);
		leadCategory.setName(leadCategoryName);
	}

	public String getRelatedPersonName() {
		return relatedPersonName;
	}

	public void setRelatedPersonName(String relatedPersonName) {
		this.relatedPersonName = relatedPersonName;
	}

	public String getRelatedPersonSurname() {
		return relatedPersonSurname;
	}

	public void setRelatedPersonSurname(String relatedPersonSurname) {
		this.relatedPersonSurname = relatedPersonSurname;
	}

	public String getRelatedCompanyName() {
		return relatedCompanyName;
	}

	public void setRelatedCompanyName(String relatedCompanyName) {
		this.relatedCompanyName = relatedCompanyName;
	}

	public String getRelatedPhone() {
		return relatedPhone;
	}

	public void setRelatedPhone(String relatedPhone) {
		this.relatedPhone = relatedPhone;
	}

	public String getRelatedAddress() {
		return relatedAddress;
	}

	public void setRelatedAddress(String relatedAddress) {
		this.relatedAddress = relatedAddress;
	}

	public String getRelatedEmail() {
		return relatedEmail;
	}

	public void setRelatedEmail(String relatedEmail) {
		this.relatedEmail = relatedEmail;
	}

	public LeadSource getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(LeadSource leadSource) {
		this.leadSource = leadSource;
	}

	public LeadCategory getLeadCategory() {
		return leadCategory;
	}

	public void setLeadCategory(LeadCategory leadCategory) {
		this.leadCategory = leadCategory;
	}

}
