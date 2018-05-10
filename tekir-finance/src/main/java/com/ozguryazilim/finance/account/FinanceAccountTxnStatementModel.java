package com.ozguryazilim.finance.account;

import com.ozguryazilim.telve.entities.FeaturePointer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Objects;

public class FinanceAccountTxnStatementModel implements Serializable{

    private Long accountId;
    private String  accountName;
    private BigDecimal amount;
    private Currency currency;
    private Date date;
    private Boolean debit;
    private FeaturePointer feature;
    private String contactName;
    private BigDecimal localAmount;
    private String referenceNo;
    private String status;

    public FinanceAccountTxnStatementModel(Long accountId, String accountName,
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

    public FinanceAccountTxnStatementModel(Long accountId, String accountName,
                                        Date date, Boolean debit,
                                        BigDecimal localAmount,
                                        FeaturePointer feature ) {
        this.accountId = accountId;
        this.accountName = accountName;
        //FIXME:Eğer null ise Local_CCY olacak. Ama constructor'da nasıl olacak?
        this.currency = null;
        this.date = date;
        this.debit = debit;
        this.feature = feature;
        this.localAmount = debit ? (localAmount.negate()) : localAmount;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FinanceAccountTxnStatementModel other =
                (FinanceAccountTxnStatementModel) obj;
        if (!Objects.equals(this.accountId, other.accountId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.accountId);
        return hash;
    }


}
