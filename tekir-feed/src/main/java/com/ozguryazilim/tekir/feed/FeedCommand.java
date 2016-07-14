/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.feed;

import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messagebus.command.AbstractCommand;
import java.util.Date;

/**
 * Feed gönderme komutu
 * 
 * @author Hakan Uygun
 */
public class FeedCommand extends AbstractCommand{
    
    private String type; //Bu tipe göre icon felan bağlanabilir. Filtre yapılabilir
    private String feeder;//Bu acacaba bir register felan mı olsa? Yoksa Type yeterli mi?
    private String user;
    private Date date;
    private String subject;
    private String body;
    private FeaturePointer basePointer;
    private FeaturePointer relatedPointer;

    public FeedCommand(String type, String feeder, String subject, String body, FeaturePointer basePointer) {
        this.type = type;
        this.feeder = feeder;
        this.subject = subject;
        this.body = body;
        this.basePointer = basePointer;
        this.date = new Date();
        this.user = "SYSTEM";
    }

    
    
    public FeedCommand(String type, String feeder, String subject, String body, FeaturePointer basePointer, FeaturePointer relatedPointer) {
        this.type = type;
        this.feeder = feeder;
        this.subject = subject;
        this.body = body;
        this.basePointer = basePointer;
        this.relatedPointer = relatedPointer;
        this.date = new Date();
        this.user = "SYSTEM";
    }

    
    
    public FeedCommand(String type, String feeder, String user, String subject, String body, FeaturePointer basePointer, FeaturePointer relatedPointer) {
        this.type = type;
        this.feeder = feeder;
        this.user = user;
        this.date = date;
        this.subject = subject;
        this.body = body;
        this.basePointer = basePointer;
        this.relatedPointer = relatedPointer;
        this.date = new Date();
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

    public FeaturePointer getBasePointer() {
        return basePointer;
    }

    public void setBasePointer(FeaturePointer basePointer) {
        this.basePointer = basePointer;
    }

    public FeaturePointer getRelatedPointer() {
        return relatedPointer;
    }

    public void setRelatedPointer(FeaturePointer relatedPointer) {
        this.relatedPointer = relatedPointer;
    }

    
    
    
    
}
