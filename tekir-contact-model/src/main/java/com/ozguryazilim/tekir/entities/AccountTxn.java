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
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * Cari ile ilişkili tüm fişlerin bilgileri bu model ile toplanır.
 * 
 * Bu sayede modüllerden gelen bilgiler hızlı bir şekilde Cari Hareket olarak sunulabilir.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCC_ACCOUNT_TXN")
public class AccountTxn extends EntityBase{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_ACCTXN_ACC"))
    private Contact account;
    
    /**
     * Ek kod alanı. Raporlar v.s. için
     */
    @Column(name="CODE", length=30)
    @Size(max=30)
    private String code;
    
    
    /**
     * Fiş açıklama alanı
     */
    @Column(name="INFO")
    private String info;
    
    
    /**
     * Resmi/Matbuu belge üzerinde bulunan numara.
     * Belge Numarası
     */
    @Column(name="REFERENCE_NO", length=30)
    @Size(max=30)
    private String referenceNo;
    
    /**
     * Belgenin Düzenlenme Tarih Saati
     */
    @Column(name="TXNDATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;
    
    /**
     * Süreç numarası.
     * Belgeler eğer bir süreç içerisinde yer alıyor ise bu numara farklı belgeler içerisinde aynı olacaktır.
     * 
     * Örneğin satış sürecinde Fırsat için alınan numara Teklif, Sipariş, Fatura ve Ödeme belgelerinde de aynı olacaktır.
     * 
     */
    @Column(name="PROCESS_ID")
    private String processId;
    
    /**
     * Bu belge'nin erişim yetkilisi, sorumlusunun kim olduğu
     */
    @Column(name="OWNER")
    private String owner;
    
    /**
     * Bu hareketin kaynağı olan fiş/özellik
     * 
     */
    @Embedded
    private FeaturePointer feature;
 
    /**
     * Fiş durum bilgisi : açık kapalı v.b.
     */
    @Column(name="STATUS")
    private String status = "DRAFT";
    
    /**
     * Özellikle başarısız kapanışlarda başarısızlık nedeni
     */
    @Column(name="STATUS_REASON")
    private String statusReason;

    @Column(name="TOPIC")
    private String topic;

    @Column(name="ACCOUNTABLE")
    private Boolean accountable = Boolean.FALSE;
    
    @Column(name="CCY")
    private Currency currency;
    
    @Column(name="AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;
    
    @Column(name="LOCAL_AMOUNT")
    private BigDecimal localAmount = BigDecimal.ZERO;
    
    @Column(name="DEBIT")
    private Boolean debit = Boolean.FALSE;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getAccount() {
        return account;
    }

    public void setAccount(Contact account) {
        this.account = account;
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

    public FeaturePointer getFeature() {
        return feature;
    }

    public void setFeature(FeaturePointer feature) {
        this.feature = feature;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Boolean getAccountable() {
        return accountable;
    }

    public void setAccountable(Boolean accountable) {
        this.accountable = accountable;
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

    public Boolean getDebit() {
        return debit;
    }

    public void setDebit(Boolean debit) {
        this.debit = debit;
    }

    public BigDecimal getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(BigDecimal localAmount) {
        this.localAmount = localAmount;
    }
    
    
    
    
}
