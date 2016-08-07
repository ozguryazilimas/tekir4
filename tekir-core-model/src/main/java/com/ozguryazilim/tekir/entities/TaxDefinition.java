/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.ParamEntityBase;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Vergi türleri Enum olarak tanımlandılar.
 * 
 * Bu modelde sadece isim, oran ve geçerlilik tarihleri tanımlanıyor.
 * 
 * @author Vergi Tanımları
 */
@Entity
@Table(name = "TCO_TAX")
public class TaxDefinition extends ParamEntityBase{
    
    
    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column( name = "TYPE")
    private TaxType type;
    
    /**
     * Vergi oranaı
     */
    @Column( name = "RATE")
    private BigDecimal rate = BigDecimal.ZERO;
    
    
    /**
     * Vergi tanımının geçerli olduğu tarih başlangıcı
     */
    @Column( name = "FROM_DATE")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    
    /**
     * Vergi tanımının geçerli olduğu tarih bitişi
     */
    @Column( name = "TO_DATE")
    @Temporal(TemporalType.DATE)
    private Date toDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaxType getType() {
        return type;
    }

    public void setType(TaxType type) {
        this.type = type;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    

    
}
