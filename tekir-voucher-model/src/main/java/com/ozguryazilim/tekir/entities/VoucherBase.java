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
import javax.persistence.Column;
import javax.persistence.Embedded;
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
     * Süreç numarası.
     * Belgeler eğer bir süreç içerisinde yer alıyor ise bu numara farklı belgeler içerisinde aynı olacaktır.
     * 
     * Örneğin satış sürecinde Fırsat için alınan numara Teklif, Sipariş, Fatura ve Ödeme belgelerinde de aynı olacaktır.
     * 
     */
    @Column(name="PROCESS_ID")
    private String processId;
    
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
    
    public String getVoucherNo() {
        return voucherNo;
    }

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

}
