/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.annotations.BizKey;
import com.ozguryazilim.telve.entities.AuditBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Tekir içerisinde oluşturulacak olan Fiş Modelleri için temel sınıf
 * 
 * @author Hakan Uygun
 */
@MappedSuperclass
public abstract class VoucherBase extends AuditBase{
    
    /**
     * Uygulama tarafında takip için kullanılan temel numara
     * Fiş Numarası
     */
    @Column(name="VOUCHER_NO", length=30, nullable=false, unique=true)
    @NotNull @Size(max = 30)
    @BizKey
    private String voucherNo;
    
    /**
     * Ek kod alanı. Raporlar v.s. için
     */
    @Column(name="CODE", length=30)
    @Size(max=30)
    private String code;
    
    
    /**
     * Fiş açıklama alanı
     */
    @Column(name="INFO")
    private String info;
    
    /**
     * Fiş konusu. Aslında process'le birlikte ilerliyor.
     */
    @Column(name="TOPIC")
    private String topic;
    
    /**
     * Resmi/Matbuu belge üzerinde bulunan numara.
     * Belge Numarası
     */
    @Column(name="REFERENCE_NO", length=30)
    @Size(max=30)
    private String referenceNo;
    
    /**
     * Belgenin Düzenlenme Tarih Saati
     */
    @Column(name="TXNDATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;
    
    /**
     * Bu belge'nin erişim yetkilisi, sorumlusunun kim olduğu
     */
    @Column(name="OWNER")
    private String owner;
    
    /**
     * Bu belgenin açılmasına neden olan belge'nin linki.
     * 
     * Örneğin bir Fırsat'tan Teklif oluşturulduğunda Teklif modelinde Fırsat'ın bilgileri
     */
    @Embedded
    private FeaturePointer starter;
 
    /**
     * JPA Converter ile String'e çevrilecek.
     */
    @Column(name="STATE")
    private VoucherState state = VoucherState.DRAFT;
    
    /**
     * Suggestion2dan gelecek
     */
    @Column(name="STATE_REASON")
    private String stateReason;
    
    /**
     * Durum ile ilgili ek açıklama alanı
     */
    @Column(name="STATE_INFO")
    private String stateInfo;
    
    
    /**
     * Grup numarası.
     * 
     * 
     * Farklı tür belge ve süreci bir araya gruplamak için kullanılır. "İşlem No" "İşlem Grup"
     * 
     */
    @ManyToOne
    @JoinColumn(name = "GROUP_ID", foreignKey = @ForeignKey(name = "FK_VOG_VOG"))
    private VoucherGroup group;

    public String getVoucherNo() {
        return voucherNo;
    }

    @ManyToMany(
            targetEntity = Tag.class,
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "TCO_TAG_MAP",
            joinColumns = @JoinColumn(name = "REF_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )
    private Set<Tag> tags = new HashSet<>();

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public FeaturePointer getStarter() {
        return starter;
    }

    public void setStarter(FeaturePointer starter) {
        this.starter = starter;
    }

    public VoucherState getState() {
        return state;
    }

    public void setState(VoucherState state) {
        this.state = state;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public VoucherGroup getGroup() {
        return group;
    }

    public void setGroup(VoucherGroup group) {
        this.group = group;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
