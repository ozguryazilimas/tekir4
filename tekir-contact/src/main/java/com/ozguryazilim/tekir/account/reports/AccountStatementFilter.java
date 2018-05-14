/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.reports;

import com.ozguryazilim.tekir.entities.AccountTxnState;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.reports.ReportDate;
import java.io.Serializable;

/**
 * Cari Ekstre için filtre.
 * 
 * TODO: Raporda hangi tür belgeler verilecek filtresi de lazım. Sanırım feature olarak lamak lazım.
 * 
 * @author Hakan Uygun
 */
public class AccountStatementFilter implements Serializable{
    
    private AccountTxnState status;
    private Boolean showCurrency = Boolean.TRUE;
    private Boolean withDetail = Boolean.TRUE;
    private Contact contact;
    private String featureName;
    private ReportDate beginDate;
    private ReportDate endDate;
    private String code;

    public AccountStatementFilter(ReportDate beginDate, ReportDate endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public ReportDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(ReportDate beginDate) {
        this.beginDate = beginDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


    public ReportDate getEndDate() {
        return endDate;
    }

    public void setEndDate(ReportDate endDate) {
        this.endDate = endDate;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Boolean getShowCurrency() {
        return showCurrency;
    }

    public void setShowCurrency(Boolean showCurrency) {
        this.showCurrency = showCurrency;
    }

    public AccountTxnState getStatus() {
        return status;
    }

    public void setStatus(AccountTxnState status) {
        this.status = status;
    }

    public Boolean getWithDetail() {
        return withDetail;
    }

    public void setWithDetail(Boolean withDetail) {
        this.withDetail = withDetail;
    }

    
}
