/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Bir contact ile gerçekleştirilen her hangi bir iletişim aktivitesini tanımlar.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCA_ACTIVITY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACTIVITY_TYPE")
public class Activity extends EntityBase{


    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    /**
     * Kimle : bir aktivite aslında hep bir person ile olacak.
     */
    @ManyToOne
    @JoinColumn(name = "PERSON_ID", foreignKey = @ForeignKey(name = "FK_ACC_PER"))
    private Person person;
    /**
     * Hangi firma adına. 
     * Contact/Person zaman içinde farklı firmalarda çalışıyor olabilir.
     */
    @ManyToOne
    @JoinColumn(name = "CORPORATION_ID", foreignKey = @ForeignKey(name = "FK_ACC_COR"))
    private Corporation corporation;
    
    /**
     * Hangi belge ile ilşikili?
     * Aslında burada 3lü bir yapı gerek.
     * Belge Türü, PK, ve BK
     */
    @Embedded
    private FeaturePointer regarding;
    
    
    /**
     * Bu iletişimin yönü nedir?
     * Incoming / Outgoing
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "DIRECTION")
    private ActivityDirection direction = ActivityDirection.INCOMING;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ActivityStatus status = ActivityStatus.DRAFT;
    
    @Column(name = "STATUS_REASON")
    private String statusReason;
    
    
    /**
     * Bu görüşme ne zaman yapılacak?
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DUE_DATE")
    private Date dueDate;
    
    /**
     * Bu iletişimin yapıldığı tarih / saat
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACT_DATE")
    private Date date;
    
    /**
     * Dakika? Saniye? cinsinden ne kadar sürdüğü
     */
    @Column(name = "DURATION")
    private Long duration;
    
    /**
     * Öncelik
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "PRIORITY")
    private ActivityPriority priority = ActivityPriority.MEDIUM;
    
    /**
     * Kimin yapacağı : User
     */
    @Column(name = "ASSIGNEE")
    private String assignee;
    
    /**
     * Görüşme konusu
     */
    @Column(name = "SUBJECT")
    private String subject;
    /**
     * Görüşme içeriği
     */
    @Column(name = "BODY")
    private String body;
    
    /**
     * Konu hakkında ek not
     */
    @Column(name = "INFO")
    private String info;
    
    
    /**
     * Sonuç notu
     */
    @Column(name = "RESULT_NOTE")
    private String resultNote;
    
    /**
     * Bir sonraki iletişim adımı varsa o. 
     */
    @ManyToOne
    @JoinColumn(name = "FOLLOWUP_ID", foreignKey = @ForeignKey(name = "FK_ACC_FOLL"))
    private Activity followup;
    
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<String> getResultStatus(){
        List<String> ls = new ArrayList<>();
        
        ls.add("Closed");
        ls.add("Canceled");
        
        return ls;
    }
    
}
