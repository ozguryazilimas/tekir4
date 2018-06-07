package com.ozguryazilim.finance.account.txn.commands;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messagebus.command.AbstractCommand;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;

public class SaveFinanceAccounTxnsCommand extends AbstractCommand {

    private FeaturePointer feature;
    private FinanceAccount account;
    private String info;
    private List<String> tags;
    private Boolean accountable;
    private Boolean debit;
    private Currency currency;
    private BigDecimal amount;
    private BigDecimal localAmount;
    private Date date;
    private String owner;
    private String processId;
    private String status;
    private String statusReason;
    private Contact contact;

    public SaveFinanceAccounTxnsCommand(FeaturePointer feature, FinanceAccount account, String info,
        List<String> tags, Boolean accountable, Boolean debit, Currency currency, BigDecimal amount,
        BigDecimal localAmount, Date date, String owner, String processId, String status,
        String statusReason, Contact contact) {
        this.feature = feature;
        this.account = account;
        this.info = info;
        this.tags = tags;
        this.accountable = accountable;
        this.debit = debit;
        this.currency = currency;
        this.amount = amount;
        this.localAmount = localAmount;
        this.date = date;
        this.owner = owner;
        this.processId = processId;
        this.status = status;
        this.statusReason = statusReason;
        this.contact = contact;
    }

    public FeaturePointer getFeature() {
        return feature;
    }

    public void setFeature(FeaturePointer feature) {
        this.feature = feature;
    }

    public FinanceAccount getAccount() {
        return account;
    }

    public void setAccount(FinanceAccount account) {
        this.account = account;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getAccountable() {
        return accountable;
    }

    public void setAccountable(Boolean accountable) {
        this.accountable = accountable;
    }

    public Boolean getDebit() {
        return debit;
    }

    public void setDebit(Boolean debit) {
        this.debit = debit;
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

    public BigDecimal getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(BigDecimal localAmount) {
        this.localAmount = localAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
