/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
 * Sözleşme taban modeli.
 *
 *
 * @author oyas
 */
@Entity
@Table(name = "TCR_CONTRACT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Contract extends VoucherProcessBase{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    
    @ManyToOne
    @JoinColumn(name = "PAYMENTPLAN_ID", foreignKey = @ForeignKey(name = "FK_ORD_PP"))
    private PaymentPlan paymentPlan;   
    
    
    /**
     * Başlangıç tarihi
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    private Date startDate;
    
    /**
     * Bitiş tarihi
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate;
    
    /**
     * Sözleşme maddeleri
     */
    @Column(name = "TERMS")
    private String terms;

    @Column(name = "TOT_CCY")
    private Currency currency;

    @Column(name = "TOT_AMT")
    private BigDecimal total = BigDecimal.ZERO;
    
    @Column(name = "LOCAL_AMOUNT")
    private BigDecimal localAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ContractItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKeyColumn(name = "ROW_KEY")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Map<String, ContractSummary> summaries = new HashMap<>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ContractItem> getItems() {
        return items;
    }

    public void setItems(List<ContractItem> items) {
        this.items = items;
    }

    public Map<String, ContractSummary> getSummaries() {
        return summaries;
    }

    public void setSummaries(Map<String, ContractSummary> summaries) {
        this.summaries = summaries;
    }

    public PaymentPlan getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public BigDecimal getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(BigDecimal localAmount) {
        this.localAmount = localAmount;
    }

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}
    
    
}
