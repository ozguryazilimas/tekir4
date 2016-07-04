/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.annotations.BizKey;
import com.ozguryazilim.telve.entities.AuditBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Emtia / Ticari Mal tanımı. 
 * 
 * En geniş hali ile Tekir içerisinde alınıp satılan şeyleri tanımlar.
 * 
 * Ürün,Servis v.b. gibi şeyler bu sınıfı miras alarak detaylanırlar.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCO_COMMOTITY" )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Commodity extends AuditBase{

    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;
    
    @Column(name = "CODE")
    @NotNull
    private String code;
    
    @Column(name = "NAME")
    @NotNull @BizKey
    private String name;
    
    @Column(name = "INFO")
    private String info;
        
    @Column(name = "ISACTIVE")
    private Boolean active = Boolean.TRUE;

    @Override
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    
    
    
}
