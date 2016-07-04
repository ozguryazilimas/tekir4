/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Contact'ın ilgilendiği ürünlerin bilgisi.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCC_CONTACT_COMMODITY" )
public class ContactInterestedCommodity extends EntityBase{

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CONTACT_ID", foreignKey = @ForeignKey(name = "FK_CONCOMM_CONTACT"))
    private Contact contact;
    
    @ManyToOne
    @JoinColumn(name = "COMMODITY_ID", foreignKey = @ForeignKey(name = "FK_CONCOMM_COMMODITY"))
    private Commodity commodity;
    
    @Column(name = "INFO")
    private String info;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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
    
    
    
}
