/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Feed Api için Feedleri saklama modeli
 *
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TFF_FEED" )
public class Feed extends EntityBase {

    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;
    
    @Column( name="TYPE")
    @NotNull
    private String type; //Bu tipe göre icon felan bağlanabilir. Filtre yapılabilir
    
    @Column( name="FEEDER")
    @NotNull
    private String feeder;//Bu acacaba bir register felan mı olsa? Yoksa Type yeterli mi?
    
    @Column( name="USERNAME")
    @NotNull
    private String user;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column( name="FEED_TIME")
    private Date date;
    
    @Column( name="FEED_SUBJECT")
    private String subject;
    
    @Column( name="FEED_BODY")
    private String body;
    
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<FeedMention> mentions = new ArrayList<>();
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeeder() {
        return feeder;
    }

    public void setFeeder(String feeder) {
        this.feeder = feeder;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public List<FeedMention> getMentions() {
        return mentions;
    }

    public void setMentions(List<FeedMention> mentions) {
        this.mentions = mentions;
    }
}