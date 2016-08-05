/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
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
 * Bir unit set içerisinde olabilecek Base Unit dışında ki diğer Unit'ler ve çevrim bilgileri
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCO_UNIT_SET_ITEM" )
public class UnitSetItem extends EntityBase{
    
    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "UNIT_SET_ID", foreignKey = @ForeignKey(name = "FK_UNITIT_US"))
    private UnitSetDefinition master;

    /**
     * Unit name
     */
    @Column(name="NAME")
    private String name;
    
    /**
     * Verilen Miktar ve Birim üzerinden çevrim miktarı
     */
    @Embedded
    private Quantity quantity;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UnitSetDefinition getMaster() {
        return master;
    }

    public void setMaster(UnitSetDefinition master) {
        this.master = master;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
    
    
}
