/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.tekir.entites.converters.StringListConverter;
import com.ozguryazilim.telve.annotations.BizKey;
import com.ozguryazilim.telve.entities.AuditBase;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Banka ve Nakit ( Kasa ) hesap tanım modeli.
 * 
 * Her iki kavram ( hatta kredi kartı ile 3 ) kavram için de aynı tanım modelini kullanacağız.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TFN_FINANCE_ACC")
public class FinanceAccount extends AuditBase{

    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;
    
    @Column( name = "NAME")
    @NotNull @BizKey
    private String name;

    @Column(name = "CODE", unique = true)
    @NotNull
    private String code;
    
    @Column( name = "INFO")
    private String info;
    
    @Enumerated(EnumType.STRING)
    @Column( name = "TYPE")
    private AccountType type = AccountType.BANK;
    
    @Column(name="BANK")
    private String bank;
    
    @Column(name="BRANCH")
    private String branch;
    
    @Column(name = "CCY")
    private Currency currency;
    
    @Column(name = "IBAN")
    private String iban;
    
    @Column(name = "ACCOUNT_NO")
    private String accountNo;

    @Temporal(TemporalType.DATE)
    @Column(name = "OPEN_DATE")
    private Date openDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "CLOSE_DATE")
    private Date closeDate;
    
    /**
     * Bu hesabın hangi şartlarda kullanılabileceğini belirler.
     * 
     * Ödeme alma, Ödeme yapma, MultiCurrency, Yatırım
     */
    @Column( name = "ROLES")
    @Convert(converter = StringListConverter.class)
    private List<String> accountRoles = new ArrayList<>();
    
    /**
     * Acaba buna ihtiyaç olur mu? Bir hesabın'ın statu bilgisi : Draft ( LEAD ), Active, Passive, Acrhive v.b.?!
     * FIXME: Burası enum olsa güzel olacak
     */
    @Column( name = "STATUS")
    private String status;
    
    /**
     * Status'e neden düştüğü ile küçük bir açıklama
     */
    @Column( name = "STATUS_REASON")
    private String statusReason;
    
    @Column( name = "OWNER")
    private String owner;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public List<String> getAccountRoles() {    	
        return accountRoles;
    }

    public void setAccountRoles(List<String> accountRoles) {
        this.accountRoles = accountRoles;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCaption(){
        if( type == AccountType.BANK ){
            return getBank() + " " + getBranch()  + " " + getCurrency().getCurrencyCode() + " " + getName() + " " + getIban();
        } else {
            return getName() + " " + getCurrency().getCurrencyCode();
        }
    }
    
}
