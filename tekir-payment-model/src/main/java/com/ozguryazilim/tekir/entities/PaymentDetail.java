/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.math.BigDecimal;
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
 *
 */
@Entity
@Table(name = "TPC_PAYMENT_DET")
public class PaymentDetail extends EntityBase{

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "PAYMENT_ID", foreignKey = @ForeignKey(name = "FK_PAYF_PAY"))
    private PaymentBase master;
    
    @Embedded
    private FeaturePointer feature;
    
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentBase getMaster() {
        return master;
    }

    public void setMaster(PaymentBase master) {
        this.master = master;
    }

    public FeaturePointer getFeature() {
        return feature;
    }

    public void setFeature(FeaturePointer feature) {
        this.feature = feature;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    

}
