package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.entities.AccountTxn;
import java.io.Serializable;
import java.util.List;

public class ContactListModel implements Serializable {

    private Long contactId;
    private String contactCode;
    private String contactName;
    private String contactInfo;
    private List<AccountTxn> txnList;

    public ContactListModel(Long contactId, String contactCode, String contactName,
        String contactInfo) {
        this.contactId = contactId;
        this.contactCode = contactCode;
        this.contactName = contactName;
        this.contactInfo = contactInfo;
    }

    public ContactListModel(Long contactId, String contactCode, String contactName,
        String contactInfo, List<AccountTxn> txnList) {
        this.contactId = contactId;
        this.contactCode = contactCode;
        this.contactName = contactName;
        this.contactInfo = contactInfo;
        this.txnList = txnList;
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

    public List<AccountTxn> getTxnList() {
        return txnList;
    }

    public void setTxnList(List<AccountTxn> txnList) {
        this.txnList = txnList;
    }
}
