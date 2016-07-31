/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;

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
 * Teklif satırı
 * 
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TSQ_QUOTE_ITEM")
public class QuoteItem extends EntityBase{

    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "MASTER_ID", foreignKey = @ForeignKey(name = "FK_QUOTEIT_QUOTE"))
    private Quote master;
    
    @ManyToOne
    @JoinColumn(name = "COMMODITY_ID", foreignKey = @ForeignKey(name = "FK_QUOTEIT_COMMO"))
    private Commodity commodity;
    
    @Column(name = "INFO")
    private String info;
    
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "QTY_AMT")),
        @AttributeOverride(name = "unit", column = @Column(name = "QTY_UNIT")),
    })
    private Quantity quantity;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "PRICE_AMT")),
        @AttributeOverride(name = "currency", column = @Column(name = "PROICE_CCY")),
    })
    private Money unitPrice;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "TOT_AMT")),
        @AttributeOverride(name = "currency", column = @Column(name = "TOT_CCY")),
    })
    private Money totalAmount;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quote getMaster() {
        return master;
    }

    public void setMaster(Quote master) {
        this.master = master;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Money unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Money totalAmount) {
        this.totalAmount = totalAmount;
    }

    
    
}
