/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.virement;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 *
 * @author oyas
 */
public class AccountVirementViewModel implements ViewModel, Serializable{
    
    private Long id;
    private Contact fromAccount;
    private Contact toAccount;
    private Currency currency;
    private BigDecimal amount = BigDecimal.ZERO;
    private String code;
    private String voucherNo;
    private String info;
    private String referenceNo;
    private Date date;
    private String processId;
    private String owner;
    private VoucherState state;

    public AccountVirementViewModel(Long id, Contact fromAccount, Contact toAccount, Currency currency, BigDecimal amount, String code, String voucherNo, String info, String referenceNo, Date date, String processId, String owner, VoucherState state) {
        this.id = id;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.currency = currency;
        this.amount = amount;
        this.code = code;
        this.voucherNo = voucherNo;
        this.info = info;
        this.referenceNo = referenceNo;
        this.date = date;
        this.processId = processId;
        this.owner = owner;
        this.state = state;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Contact fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Contact getToAccount() {
        return toAccount;
    }

    public void setToAccount(Contact toAccount) {
        this.toAccount = toAccount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public VoucherState getState() {
        return state;
    }

    public void setState(VoucherState state) {
        this.state = state;
    }
    
    
    
}
