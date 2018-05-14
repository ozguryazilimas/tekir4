package com.ozguryazilim.finance.account;

import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.telve.entities.FeaturePointer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * Finance Account Txn üserinde summary almak için model.
 * 
 * @author Ceyhun Onur
 */
public class FinanceAccountTxnSumModel implements Serializable{

    private FinanceAccount  account;
    private Long accountId;
    private String accountName;
    private BigDecimal amount;
    private BigDecimal balance;
    private String contactName;
    private Currency currency;
    private Date date;
    private Boolean debit;
    private FeaturePointer feature;
    private BigDecimal localAmount;
    private BigDecimal localBalance;
    private Integer rate;
    private String referenceNo;
    private String status;

    public FinanceAccountTxnSumModel(Long accountId, String accountName,
                                           Date date, Boolean debit, Currency currency,
                                           BigDecimal localAmount, FeaturePointer feature,
                                           String contactName, String referenceNo,
                                           String status,
                                           BigDecimal amount) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.debit = debit;
        this.feature = feature;
        this.contactName = contactName;
        this.localAmount = debit ? (localAmount.negate()) : localAmount;
        this.referenceNo = referenceNo;
        this.status = status;

    }

    public FinanceAccountTxnSumModel(FinanceAccount account, BigDecimal localBalance, BigDecimal balance, Currency currency) {
        this.account = account;
        this.localBalance = localBalance;
        this.currency = currency;
        this.balance = balance;
        this.accountName = account.getName();
    }
     
    public FinanceAccountTxnSumModel(FinanceAccount account, BigDecimal balance, Currency currency){
    	this(account,new BigDecimal(0),balance,currency);
    	
    }
     
    public FinanceAccountTxnSumModel(FinanceAccount account, BigDecimal localBalance) {
    	this(account,localBalance,new BigDecimal(0),null);

    }

    public FinanceAccount getAccount() {
        return account;
    }

    public void setAccount(FinanceAccount account) {
        this.account = account;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    public void setFeature(FeaturePointer feature) {
        this.feature = feature;
    }

    public BigDecimal getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(BigDecimal localAmount) {
        this.localAmount = localAmount;
    }

    public BigDecimal getLocalBalance() {
        return localBalance;
    }

    public void setLocalBalance(BigDecimal localBalance) {
        this.localBalance = localBalance;
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
