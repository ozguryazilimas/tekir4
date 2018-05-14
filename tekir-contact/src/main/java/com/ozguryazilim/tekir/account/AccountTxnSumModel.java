/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.telve.entities.FeaturePointer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * Account Txn üserinde summary almak için model.
 * 
 * @author Hakan Uygun
 */
public class AccountTxnSumModel implements Serializable{
    
    private Long accountId;
    private String  accountName;
    private BigDecimal amount;
    private Class<? extends Contact> contactClass;
    private Currency currency;
    private Date date;
    private Boolean debit;
    private FeaturePointer feature;
    private String info;
    private BigDecimal localAmount;
    private Integer rate;
    private String referenceNo;
    private String status;

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

    public AccountTxnSumModel(Long accountId, String accountName, BigDecimal
            amount, BigDecimal localAmount, Currency currency, FeaturePointer
            feature, Date date, String info, String status, String
            referenceNo, Boolean debit) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.debit = debit;
        this.feature = feature;
        this.info = info;
        this.localAmount = debit ? localAmount.negate() : localAmount;
        this.referenceNo = referenceNo;
        this.status = status;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Class<? extends Contact> getContactClass() {
        return contactClass;
    }

    public void setContactClass(Class<? extends Contact> contactClass) {
        this.contactClass = contactClass;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getDebit() {
        return debit;
    }

    public void setDebit(Boolean debit) {
        this.debit = debit;
    }

    public FeaturePointer getFeature() {
        return feature;
    }

    public void setFeatureName(String featureName) {
        this.feature = feature;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(BigDecimal localAmount) {
        this.localAmount = localAmount;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
