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

/**
 *
 * @author oyas
 */
@Entity
@Table(name = "TVO_MATCHER")
public class VoucherMatcher extends EntityBase{
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MASTER_ID", foreignKey = @ForeignKey(name = "FK_MATCHER_MATCH"))
    private VoucherMatchable matchable;
    
    @Column(name = "CCY")
    private Currency currency;
    
    @Column(name = "AMOUNT")
    private BigDecimal ammount = BigDecimal.ZERO;
    
    @Embedded
    private FeaturePointer feature;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VoucherMatchable getMatchable() {
        return matchable;
    }

    public void setMatchable(VoucherMatchable matchable) {
        this.matchable = matchable;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public void setAmmount(BigDecimal ammount) {
        this.ammount = ammount;
    }

    public FeaturePointer getFeature() {
        return feature;
    }

    public void setFeature(FeaturePointer feature) {
        this.feature = feature;
    }
    
    

}
