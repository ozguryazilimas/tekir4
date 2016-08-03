/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author oyas
 */
//@Entity
//@Table( name = "TCO_UNIT_SET_ITEM" )
public class UnitSetItem extends EntityBase{
    
    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;
    
    @OneToMany
    @JoinColumn(name = "UNIT_SET_ID")
    private UnitSetDefinition master;

    @Column(name="NAME")
    private String name;
    
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
    
    
}
