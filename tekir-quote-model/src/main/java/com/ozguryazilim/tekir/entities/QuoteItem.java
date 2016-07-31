/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.unit.Quantity;

import com.ozguryazilim.telve.entities.EntityBase;
import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

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
    
    @Columns( columns = {
        @Column( name = "QTY_AMT"),
        @Column( name = "QTY_UNIT")
    })
    @Type(type = "com.ozguryazilim.tekir.entities.QuantityType")
    private Quantity quantity;
    
    @Columns( columns = {
        @Column( name = "PRICE_AMT"),
        @Column( name = "PROICE_CCY")
    })
    @Type(type = "com.ozguryazilim.tekir.entities.MoneyType")
    private MonetaryAmount unitPrice;
    
    @Columns( columns = {
        @Column( name = "TOT_AMT"),
        @Column( name = "TOT_CCY")
    })
    @Type(type = "com.ozguryazilim.tekir.entities.MoneyType")
    private MonetaryAmount totalAmount;

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

    public MonetaryAmount getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(MonetaryAmount unitPrice) {
        this.unitPrice = unitPrice;
    }

    public MonetaryAmount getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(MonetaryAmount totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
    
}
