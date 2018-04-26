package com.ozguryazilim.tekir.contact;

import java.io.Serializable;

public class ContactListModel implements Serializable {

    private Long contactId;
    private String contactCode;
    private String contactName;
    private String contactInfo;

    public ContactListModel(Long contactId, String contactCode, String contactName,
        String contactInfo) {
        this.contactId = contactId;
        this.contactCode = contactCode;
        this.contactName = contactName;
        this.contactInfo = contactInfo;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getContactCode() {
        return contactCode;
    }

    public void setContactCode(String contactCode) {
        this.contactCode = contactCode;
    }

}
