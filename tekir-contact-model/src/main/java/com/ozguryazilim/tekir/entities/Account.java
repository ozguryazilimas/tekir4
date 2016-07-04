/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.telve.entities.AuditBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Fişlerde kullanılacak olan müşteri hesapları ( cari )
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCC_ACCOUNT" )
public class Account extends AuditBase{

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "CONTACT_ID", foreignKey = @ForeignKey(name = "FK_ACC_CONTACT"))
    private Contact contact;
    
    /**
     * Account status
     * 
     * FIXME: Enum olsa?
     */
    @Column(name = "STATUS")
    private String status;
    
    /**
     * İlgili status sebebi
     */
    @Column(name = "STATUS_REASON")
    private String statusReason;
    
    /**
     * Vergi bilgileri. Bireysel kullanıcılar için de lazım olabilir.
     * Bu bilgiler Account'a mı gitse?
     * Suggestion
     */
    @Column( name = "TAX_OFFICE")
    private String taxOffice;
    
    @Column( name = "TAX_NUMBER")
    private String taxNumber;
    
    
    /**
     * Banka hesap bilgisi : aslında birden fazla olabilir. Nasıl yapsak?
     */
    @Column( name = "BANK_ACCOUNT")
    private String bankAccount;
    
    /**
     * Hangi döviz ile çalışıldığı ISO kodu olarak tutulacak.
     */
    @Column( name = "CCY")
    private String currency;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    
    /**
     * Contact üzerinde bir category var ama cari hesap için de ek bir category makul olabilir 
     * TODO: Bu işe yarar mı?
     */
    //private String category;
    
    
    
}
