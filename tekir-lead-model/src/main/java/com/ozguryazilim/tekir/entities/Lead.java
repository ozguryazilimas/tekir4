package com.ozguryazilim.tekir.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TLE_LEAD")
public class Lead extends VoucherBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
	@Column(name = "ID")
	private Long id;

	@Column(name = "RELATED_PERSON_NAME")
	private String relatedPersonName;

	@Column(name = "RELATED_PERSON_SURNAME")
	private String relatedPersonSurname;

	@Column(name = "RELATED_COMPANY_NAME")
	private String relatedCompanyName;

	@Column(name = "RELATED_PHONE")
	private String relatedPhone;

	@Column(name = "RELATED_ADDRESS")
	private String relatedAddress;

	@Column(name = "RELATED_EMAIL")
	private String relatedEmail;

	@ManyToOne
	@JoinColumn(name = "INDUSTRY_ID", foreignKey = @ForeignKey(name = "FK_LEAD_INDUSTRY"))
	private Industry industry;

	@ManyToOne
	@JoinColumn(name = "LOCATION_ID", foreignKey = @ForeignKey(name = "FK_LEAD_LOC"))
	private Location location;

	@ManyToOne
	@JoinColumn(name = "TERRITORY_ID", foreignKey = @ForeignKey(name = "FK_LEAD_TER"))
	private Territory territory;

	@ManyToOne
	@JoinColumn(name = "LEAD_SOURCE_ID", foreignKey = @ForeignKey(name = "FK_LEAD_LESOURCE"))
	private LeadSource leadSource;

	@ManyToOne
	@JoinColumn(name = "LEAD_CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_LEAD_LECATEGORY"))
	private LeadCategory leadCategory;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setTerritory(Territory territory) {
		this.territory = territory;
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
