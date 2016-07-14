/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Kullanıcıların takip ettikleri feed Base ya da Related şeylerini tutar
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TFF_FEED_FOLLOW" )
public class FeedFollow extends EntityBase{
    
    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;

    @Embedded
    private FeaturePointer basePointer = new FeaturePointer();

    @Temporal(TemporalType.TIMESTAMP)
    @Column( name="FOLLOW_TIME")
    private Date date;
    
    @Column( name="USERNAME")
    private String user;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FeaturePointer getBasePointer() {
        return basePointer;
    }

    public void setBasePointer(FeaturePointer basePointer) {
        this.basePointer = basePointer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
}
