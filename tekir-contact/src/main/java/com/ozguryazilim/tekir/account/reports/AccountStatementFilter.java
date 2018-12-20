package com.ozguryazilim.tekir.account.reports;

import com.ozguryazilim.tekir.entities.Contact;
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
    
    private Contact contact;
    private ReportDate beginDate;
    private ReportDate endDate;
    private Boolean withDetail = Boolean.FALSE;

    public AccountStatementFilter(ReportDate beginDate, ReportDate endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ReportDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(ReportDate beginDate) {
        this.beginDate = beginDate;
    }

    public ReportDate getEndDate() {
        return endDate;
    }

    public void setEndDate(ReportDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getWithDetail() {
        return withDetail;
    }

    public void setWithDetail(Boolean withDetail) {
        this.withDetail = withDetail;
    }
    
    
}
