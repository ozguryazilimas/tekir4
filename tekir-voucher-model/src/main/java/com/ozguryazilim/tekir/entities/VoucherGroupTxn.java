/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;

/**
 * voucherGroup'a sahip olan tüm fişlerin bilgileri bu model ile toplanır.
 * 
 * @author oktay
 *
 */
@Entity
@Table(name = "TVO_GROUP_TXN")
public class VoucherGroupTxn extends EntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
	
    @ManyToOne
    @JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_TVOTXN_TVO"))
    private VoucherGroup group;
    
    /**
     * Bu hareketin kaynağı olan fiş/özellik
     * 
     */
    @Embedded
    private FeaturePointer feature;
    
    /**
     * Fiş konu alanı
     */
    @Column(name="TOPIC")
    private String topic;
    
    /**
     * Belgenin Düzenlenme Tarih Saati
     */
    @Column(name = "TXNDATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;
    
    /*
     * Bu belge'nin erişim yetkilisi, sorumlusunun kim olduğu
     */
    @Column(name="OWNER")
    private String owner;
    
    /**
     * JPA Converter ile String'e çevrilecek.
     */
    @Column(name="STATE")
    private VoucherState state;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VoucherGroup getGroup() {
		return group;
	}

	public void setGroup(VoucherGroup group) {
		this.group = group;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public FeaturePointer getFeature() {
		return feature;
	}

	public void setFeature(FeaturePointer feature) {
		this.feature = feature;
	}

	public VoucherState getState() {
		return state;
	}

	public void setState(VoucherState state) {
		this.state = state;
	}
	
}
