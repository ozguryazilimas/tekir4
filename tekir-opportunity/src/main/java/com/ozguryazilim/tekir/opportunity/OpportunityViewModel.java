/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;


import com.ozguryazilim.tekir.entities.VocuherStatus;
import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author oyas
 */
public class OpportunityViewModel implements ViewModel, Serializable{
    
    private Long id;
    private String code;
    private String voucherNo;
    private String info;
    private String referenceNo;
    private Date date;
    private String processId;
    private String owner;
    private VocuherStatus status;
    private String topic;

    public OpportunityViewModel(Long id, String code, String voucherNo, String info, String referenceNo, Date date, String processId, String owner, VocuherStatus status, String topic) {
        this.id = id;
        this.code = code;
        this.voucherNo = voucherNo;
        this.info = info;
        this.referenceNo = referenceNo;
        this.date = date;
        this.processId = processId;
        this.owner = owner;
        this.status = status;
        this.topic = topic;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public VocuherStatus getStatus() {
        return status;
    }

    public void setStatus(VocuherStatus status) {
        this.status = status;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    
    
    
}
