/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.tekir.entites.converters.StringListConverter;
import com.ozguryazilim.telve.entities.ParamEntityBase;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contact'lar arası ilişki tipi
 *
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TCC_CONTACT_REL_DEF")
public class ContactRelation extends ParamEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    /**
     * Kullanılabileceği contact roleler
     *
     * Virgüllerle ayrılmış bir şekilde tutulur.
     *
     * ALL diye bir tip de eklemk lazım mı?
     */
    @Column(name = "ROLES")
    @Convert(converter = StringListConverter.class)
    private List<String> contactRoles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getContactRoles() {
        return contactRoles;
    }

    public void setContactRoles(List<String> contactRoles) {
        this.contactRoles = contactRoles;
    }

}
