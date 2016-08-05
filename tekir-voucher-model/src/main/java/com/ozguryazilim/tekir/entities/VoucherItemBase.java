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
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Aslında buna daha makul bir isim mi versek? Ya da bu sınıfı zaten buraya koymasak mı? Mesela Commodity'ye daha çok yaraşır gibi.
 * @author Hakan Uygun
 */
@MappedSuperclass
public abstract class VoucherItemBase<E extends VoucherBase> extends EntityBase{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "MASTER_ID", foreignKey = @ForeignKey(name = "FK_QUOTEIT_QUOTE"))
    private E master;
    
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
    private Money price;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "amount", column = @Column(name = "TOT_AMT")),
        @AttributeOverride(name = "currency", column = @Column(name = "TOT_CCY")),
    })
    private Money total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public E getMaster() {
        return master;
    }

    public void setMaster(E master) {
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

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Money getTotal() {
        return total;
    }

    public void setTotal(Money total) {
        this.total = total;
    }
    
    
}
