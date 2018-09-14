/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.tekir.entites.converters.StringListConverter;
import com.ozguryazilim.telve.annotations.BizKey;
import com.ozguryazilim.telve.entities.AuditBase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * İş İlanı
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TRC_ADVERT" )
public class JobAdvert extends AuditBase{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "SERIAL")
    @BizKey @NotNull
    private String serial;
    
    /**
     * İlan adı
     */
    @Column(name = "TOPIC")
    private String topic;
    
    /**
     * İş tanımı
     */
    @Column(name = "INFO")
    private String info;
    
    /**
     * Gereksinimler
     */
    @Lob
    @Column(name = "REQUIREMENT")
    private String requirement;
    
    @Column( name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Column( name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    /**
     * Etiket olarak yetnek/bilgi/beceri bilgileri
     * 
     * Bu ilan için gerekenler.
     */
    @Column( name = "SKILLS")
    @Convert(converter = StringListConverter.class)
    private List<String> skills = new ArrayList<>();
    
    
    /**
     * Bu belge'nin erişim yetkilisi, sorumlusunun kim olduğu
     */
    @Column(name = "OWNER")
    private String owner;
    
    /**
     * İlanın açık olup olmadığı : DRAFT, OPEN, CLOSE v.b.
     * TODO: Enum mu yapsak?
     */
    @Column(name = "STATUS")
    private String status;
    
    public static final String SUGGESTIONSTATUSGROUP="Recruit";
    public static final String SUGGESTIONSTATUSKEY="JobAdvert.Status";
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
     
}
