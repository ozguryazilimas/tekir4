package com.ozguryazilim.tekir.activity.email.imports.model;

import com.google.common.base.Strings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.internet.InternetAddress;

/**
 * @author oyas
 */
public class EMailMessage implements Serializable {


    private String messageId;
    private Date date;
    private String subject;
    private String content;
    private InternetAddress from;
    private List<InternetAddress> toList = new ArrayList<>();
    private List<InternetAddress> ccList = new ArrayList<>();
    private List<InternetAddress> bccList = new ArrayList<>();


    private String relatedReferenceId;
    private List<String> references = new ArrayList<>();

    private List<EMailAttacment> attachments = new ArrayList<>();

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRelatedReferenceId() {
        return relatedReferenceId;
    }

    public void setRelatedReferenceId(String relatedReferenceId) {
        this.relatedReferenceId = relatedReferenceId;
    }

    public boolean isRelated() {
        return !Strings.isNullOrEmpty(relatedReferenceId);
    }

    public InternetAddress getFrom() {
        return from;
    }

    public void setFrom(InternetAddress from) {
        this.from = from;
    }

    public List<InternetAddress> getToList() {
        return toList;
    }

    public void setToList(List<InternetAddress> toList) {
        this.toList = toList;
    }

    public List<InternetAddress> getCcList() {
        return ccList;
    }

    public void setCcList(List<InternetAddress> ccList) {
        this.ccList = ccList;
    }

    public List<InternetAddress> getBccList() {
        return bccList;
    }

    public void setBccList(List<InternetAddress> bccList) {
        this.bccList = bccList;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }

    public List<EMailAttacment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<EMailAttacment> attachments) {
        this.attachments = attachments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EMailMessage{" + "messageId=" + messageId + ", subject=" + subject + ", from="
            + from + ", toList=" + toList + ", ccList=" + ccList + ", bccList=" + bccList
            + ", relatedReferenceId=" + relatedReferenceId + ", references=" + references
            + ", attachments=" + attachments + '}';
    }

}
