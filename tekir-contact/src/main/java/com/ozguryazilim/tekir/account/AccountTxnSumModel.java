/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account;

import com.ozguryazilim.tekir.entities.Contact;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Account Txn üserinde summary almak için model.
 * 
 * @author Hakan Uygun
 */
public class AccountTxnSumModel implements Serializable{
    
    private Long accountId;
    private String  accountName;
    private Class<? extends Contact> contactClass;
    private BigDecimal amount;
    private Currency currency;
    private Integer rate;

    public AccountTxnSumModel(Long accountId, String accountName, Class<? extends Contact> contactClass, BigDecimal amount, Currency currency) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.contactClass = contactClass;
        this.amount = amount;
        this.currency = currency;
    }
    
    public AccountTxnSumModel(Long accountId, String accountName, BigDecimal amount, Currency currency) {
        this.accountId = accountId;
        this.accountName = accountName;
        //this.contactClass = contactClass;
        this.amount = amount;
        this.currency = currency;
    }
    
    public AccountTxnSumModel(Long accountId, String accountName, BigDecimal amount) {
        this.accountId = accountId;
        this.accountName = accountName;
        //this.contactClass = contactClass;
        this.amount = amount;
        //this.currency = currency;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Class<? extends Contact> getContactClass() {
        return contactClass;
    }

    public void setContactClass(Class<? extends Contact> contactClass) {
        this.contactClass = contactClass;
    }

    
    
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
    
    
    
}
