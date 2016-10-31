/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Teklif Modeli
 * 
 * Sadece satış teklifleri için alış teklifleri bambaşka bir modülün işi. Satın alma süreçlerinde teklif karşılaştırma v.s. olur.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TSQ_QUOTE")
public class Quote extends VoucherBase{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;


        /**
     * Hangi müşteri için
     */
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_OPP_ACC"))
    private Contact account;

    /**
     * Teklif versioyonu
     */
    @Column( name = "REVISION")
    private Integer revision;

    
    @Temporal(TemporalType.DATE)
    @Column( name = "EXP_DATE")
    private Date expireDate;
    
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<QuoteItem> items = new ArrayList<>();
    
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKeyColumn(name = "ROW_KEY")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Map<String, QuoteSummary> summaries = new HashMap<>();

    //FIXME: Teslimat ve Ödeme kuralları alınmalı
    
    @Column(name = "TOT_CCY")
    private String currency;
    
    @Column(name = "TOT_AMT")
    private BigDecimal total = BigDecimal.ZERO;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public List<QuoteItem> getItems() {
        return items;
    }

    public void setItems(List<QuoteItem> items) {
        this.items = items;
    }

    public Contact getAccount() {
        return account;
    }

    public void setAccount(Contact account) {
        this.account = account;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Map<String, QuoteSummary> getSummaries() {
        return summaries;
    }

    public void setSummaries(Map<String, QuoteSummary> summaries) {
        this.summaries = summaries;
    }

    
}
