package com.ozguryazilim.tekir.contact.reports;

import com.ozguryazilim.tekir.entities.ContactCategory;
import com.ozguryazilim.tekir.entities.CorporationType;
import com.ozguryazilim.tekir.entities.Industry;
import com.ozguryazilim.tekir.entities.Territory;
import com.ozguryazilim.telve.reports.ReportDate;
import java.io.Serializable;

public class ContactListFilter implements Serializable {

    private String code;
    private String name;
    private CorporationType corporationType;
    private ContactCategory contactCategory;
    private Industry industry;
    private Territory territory;
    private String owner;
    private Boolean isDetail;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Boolean getDetail() {
        return isDetail;
    }

    public void setDetail(Boolean detail) {
        isDetail = detail;
    }

    public ReportDate getDate() {
        return date;
    }

    public void setDate(ReportDate date) {
        this.date = date;
    }
}