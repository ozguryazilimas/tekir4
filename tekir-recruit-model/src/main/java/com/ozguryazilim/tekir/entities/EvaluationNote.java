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
 * Başvuru değerlendirme notu
 * 
 * Her başvuru için birden fazla kişi tarafından verilebilir.
 * 
 * TODO: İleride bu değerlendirme notları için otomatik açma işlemleri de yapılabilir. Teknik, İdari v.s. için ayrı kişilere görev olarak atanmak üzere.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TRC_EVALUATION" )
public class EvaluationNote extends AuditBase{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    /**
     * Bu değerlendrimenin kime ait olduğu 
     * 
     * Applicant tarafından değerlendirme bilgileri hızla bulunsun diye.
     */
    @ManyToOne
    @JoinColumn(name = "APPLICANT_ID", foreignKey = @ForeignKey(name = "FK_EVAL_APPLICANT"))
    private Applicant applicant;
    
    @ManyToOne
    @JoinColumn(name = "APPLICATION_ID", foreignKey = @ForeignKey(name = "FK_EVAL_APP"))
    private JobApplication application;
    
    /**
     * Değerlendirmeyi kim yaptı
     */
    @Column( name = "OWNER")
    private String owner;
    
    /**
     * Değerlendirme açıklamaları
     */
    @Column( name = "INFO")
    private String info;
    
    /**
     * Değerlendirme sonuçlarına göre verilebilecek 1-5 arası bir puan arayüzde yıldız v.s. yapılır.
     */
    @Column( name = "RATING")
    private Integer rating = 0;
    
    /**
     * Belgenin Düzenlenme Tarih Saati
     */
    @Column(name = "TXNDATE")
    @Temporal(value = TemporalType.DATE)
    private Date date;
    
    /**
     * Değerlendirme sonucu : ACCEPT, REJECT v.b.
     * TODO: Enum mu yapsak?
     */
    @Column(name = "STATE")
    private String state;
    
    /**
     * State için ek bilgi. Suggestion'dan gelebilir.
     */
    @Column(name = "STATE_REASON")
    private String stateReason;
    
    /**
     * Suggestion'dan alınabilir. Teknik, İdari v.b. 
     * TODO: config'den felan mı alsak?
     */
    @Column(name = "TYPE")
    private String type;
    
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

    public JobApplication getApplication() {
        return application;
    }

    public void setApplication(JobApplication application) {
        this.application = application;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
