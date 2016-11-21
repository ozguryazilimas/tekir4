/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.ParamEntityBase;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Banka ve Nakit ( Kasa ) hesap tanım modeli.
 * 
 * Her iki kavram ( hatta kredi kartı ile 3 ) kavram için de aynı tanım modelini kullanacağız.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TCO_BANK_CASH_ACC")
public class BankCashAccount extends ParamEntityBase{

    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;
    
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
    
    @Override
    public String getCaption(){
        if( type == AccountType.BANK ){
            return getBank() + " " + getBranch()  + " " + getCurrency().getCurrencyCode() + " " + getName() + " " + getIban();
        } else {
            return getName() + " " + getCurrency().getCurrencyCode();
        }
    }
    
}
