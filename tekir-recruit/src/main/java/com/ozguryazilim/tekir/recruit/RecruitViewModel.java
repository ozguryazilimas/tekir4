/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.recruit;

import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author deniz
 */
public class RecruitViewModel implements ViewModel, Serializable{
    
    private Long id;
    private String serial;
    private String topic;
    private String info;
    private Date startDate;
    private Date endDate;
    private List<String> skills;
    private String owner;
    private String status;

    public RecruitViewModel(Long id, String serial, String topic, String info, Date startDate, Date endDate, List<String> skills, String owner, String status) {
        this.id = id;
        this.serial = serial;
        this.topic = topic;
        this.info = info;
        this.startDate = startDate;
        this.endDate = endDate;
        this.skills = skills;
        this.owner = owner;
        this.status = status;
    }

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

 

 
   
    
    
}
