package com.ozguryazilim.tekir.contact.reports;

import com.ozguryazilim.tekir.entities.ContactCategory;
import com.ozguryazilim.tekir.entities.CorporationType;
import com.ozguryazilim.tekir.entities.Industry;
import com.ozguryazilim.tekir.entities.Territory;
import com.ozguryazilim.telve.reports.ReportDate;
import java.io.Serializable;
import java.util.List;

public class InactiveContactsFilter implements Serializable {

    private String code;
    private String name;
    private List<String> roles;
    private CorporationType corporationType;
    private ContactCategory contactCategory;
    private Industry industry;
    private Territory territory;
    private String owner;
    private Integer DayInterval = 30;
    private Boolean includeActivity;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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

    public Integer getDayInterval() {
        return DayInterval;
    }

    public void setDayInterval(Integer dayInterval) {
        this.DayInterval = dayInterval;
    }

    public Boolean getIncludeActivity() {
        return includeActivity;
    }

    public void setIncludeActivity(Boolean includeActivity) {
        this.includeActivity = includeActivity;
    }

    public ReportDate getDate() {
        return date;
    }

    public void setDate(ReportDate date) {
        this.date = date;
    }
}