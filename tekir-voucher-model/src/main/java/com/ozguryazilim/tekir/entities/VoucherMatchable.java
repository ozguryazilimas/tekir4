/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author oyas
 */
@Entity
@Table(name = "TVO_MATCHABLE")
public class VoucherMatchable extends EntityBase{

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    @Column(name="PROCESS_NO", length=30)
    @NotNull @Size(max = 30)
    private String processNo;
    
    @Column(name = "TOPIC")
    private String topic;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "TXNDATE")
    private Date txnDate;
    
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_MATCH_ACC"))
    private Contact account;
    
    @Enumerated(EnumType.STRING)
    @Column( name="TYPE")
    private ProcessType type;
    
    @Enumerated(EnumType.STRING)
    @Column( name="STATUS")
    private ProcessStatus status = ProcessStatus.OPEN;

    @Column(name = "CCY")
    private Currency currency;
    
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    
    @Column(name = "LOCAL_AMOUNT")
    private BigDecimal localAmount;
    
    @Column(name = "REMAINDER")
    private BigDecimal remainder;
    
    @Column(name = "LOCAL_REMAINDER")
    private BigDecimal localRemainder;
    
    @Embedded
    private FeaturePointer feature;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessNo() {
        return processNo;
    }

    public void setProcessNo(String processNo) {
        this.processNo = processNo;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Contact getAccount() {
        return account;
    }

    public void setAccount(Contact account) {
        this.account = account;
    }

    public ProcessType getType() {
        return type;
    }

    public void setType(ProcessType type) {
        this.type = type;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
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

    public FeaturePointer getFeature() {
        return feature;
    }

    public void setFeature(FeaturePointer feature) {
        this.feature = feature;
    }

    public BigDecimal getRemainder() {
        return remainder;
    }

    public void setRemainder(BigDecimal remainder) {
        this.remainder = remainder;
    }

    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public BigDecimal getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(BigDecimal localAmount) {
        this.localAmount = localAmount;
    }

    public BigDecimal getLocalRemainder() {
        return localRemainder;
    }

    public void setLocalRemainder(BigDecimal localRemainder) {
        this.localRemainder = localRemainder;
    }
    
    
}
