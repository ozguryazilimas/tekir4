/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.AuditBase;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author oyas
 */
@Entity
@Table( name = "TRC_APPLICATION" )
public class JobApplication extends AuditBase{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    
    /**
     * Bu baş vurunun kime ait olduğu
     */
    @ManyToOne
    @JoinColumn(name = "APPLICANT_ID", foreignKey = @ForeignKey(name = "FK_JOBAPP_APPLICANT"))
    private Applicant applicant;
    
    @ManyToOne
    @JoinColumn(name = "ADVERT_ID", foreignKey = @ForeignKey(name = "FK_JOBAPP_ADVERT"))
    private JobAdvert advert;
    
    /**
     * Ek açıklama
     */
    @Column(name = "INFO")
    private String info;
    
    /**
     * Belgenin Düzenlenme Tarih Saati
     */
    @Column(name = "TXNDATE")
    @Temporal(value = TemporalType.DATE)
    private Date date;
    
    /**
     * Bu belge'nin erişim yetkilisi, sorumlusunun kim olduğu
     */
    @Column(name = "OWNER")
    private String owner;
    
    /**
     * Değerlendirme süreç adımları : DRAFT, OPEN, CLOSE v.b.
     * TODO: Enum mu yapsak?
     */
    @Column(name = "STATE")
    private String state;
    
    public static final String SUGGESTIONSTATUSGROUP="Recruit";
    public static final String SUGGESTIONSTATUSKEY="JobApplication.Status";

    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public JobAdvert getAdvert() {
        return advert;
    }

    public void setAdvert(JobAdvert advert) {
        this.advert = advert;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
}
