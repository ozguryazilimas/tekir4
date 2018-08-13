/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.annotations.BizKey;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Bir contact ile gerçekleştirilen her hangi bir iletişim aktivitesini tanımlar.
 *
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TCA_ACTIVITY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACTIVITY_TYPE")
public class Activity extends EntityBase {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "ACT_NO")
    @BizKey
    private String activityNo;

    /**
     * Başka kaynaklardan içe aktarındığında kaynak tekil id'si
     */
    @Column(name = "REF_ID")
    private String referenceId;

    /**
     * Kimle : bir aktivite aslında hep bir person ile olacak.
     */
    @ManyToOne
    @JoinColumn(name = "PERSON_ID", foreignKey = @ForeignKey(name = "FK_ACC_PER"))
    private AbstractPerson person;
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
     * Saniye cinsinden ne kadar sürdüğü
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

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ActivityMention> mentions = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public AbstractPerson getPerson() {
        return person;
    }

    public void setPerson(AbstractPerson person) {
        this.person = person;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

    public FeaturePointer getRegarding() {
        return regarding;
    }

    public void setRegarding(FeaturePointer regarding) {
        this.regarding = regarding;
    }

    public ActivityDirection getDirection() {
        return direction;
    }

    public void setDirection(ActivityDirection direction) {
        this.direction = direction;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public ActivityPriority getPriority() {
        return priority;
    }

    public void setPriority(ActivityPriority priority) {
        this.priority = priority;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getResultNote() {
        return resultNote;
    }

    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }

    public Activity getFollowup() {
        return followup;
    }

    public void setFollowup(Activity followup) {
        this.followup = followup;
    }


    public List<String> getResultStatus() {
        List<String> ls = new ArrayList<>();

        ls.add("Closed");
        ls.add("Canceled");

        return ls;
    }

    public List<ActivityMention> getMentions() {
        return mentions;
    }

    public void setMentions(List<ActivityMention> mentions) {
        this.mentions = mentions;
    }

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }


}
