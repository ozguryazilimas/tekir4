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
 * Bir contact'ın ilişkide olduğu diğer contactlar...
 * ve ilişki türleri
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCC_CONTACT_REL" )
public class RelatedContact extends EntityBase{

    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;
    
    //Bu bilgi hangi contactın
    @ManyToOne
    @JoinColumn(name = "SOURCE_ID", foreignKey = @ForeignKey(name = "FK_CONREL_SOURCE"))
    private Contact sourceContact;
    //Hedef bağlantı kim
    
    @ManyToOne
    @JoinColumn(name = "TARGET_ID", foreignKey = @ForeignKey(name = "FK_CONREL_TARGET"))
    private Contact targetContact;
    //İlişki türü ne
    
    @ManyToOne
    @JoinColumn(name = "RELEATION_ID", foreignKey = @ForeignKey(name = "FK_CONREL_REL"))
    private ContactRelation  relation;
    
    @Column(name = "INFO")
    private String info;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getSourceContact() {
        return sourceContact;
    }

    public void setSourceContact(Contact sourceContact) {
        this.sourceContact = sourceContact;
    }

    public Contact getTargetContact() {
        return targetContact;
    }

    public void setTargetContact(Contact targetContact) {
        this.targetContact = targetContact;
    }

    public ContactRelation getRelation() {
        return relation;
    }

    public void setRelation(ContactRelation relation) {
        this.relation = relation;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    
    
}
