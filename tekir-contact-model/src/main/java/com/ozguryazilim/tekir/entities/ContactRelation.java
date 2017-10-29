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
 * Contact'lar arası ilişki tipi.
 * 
 * Kaynak ve Hedef ilişkinin iki ucunu temsil ediyor. Ve bu uçlarda nasıl bir contact olabilir ContactRole filtresi ile belirleniyor.
 * 
 * VectorName Source'dan Target'a olan ilişkinin ismini tanımlıyor
 * RaversName Target'dan Source'a olan ilişkinin ( tersine vector ) ismini tanımlıyor
 *
 * Bu bilgi ilişki tablosunda @see RelatedContact :
 * 
 * 
 * source, relation, target biçmine bürünecek. burada ilişkinin yönü kaynaktan hedefe olacak. Daima ilişkinin tanımladığı şekilde kaynak ve hadef bilgisi yazılacak.
 * 
 * 
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
    @Column(name = "SOURCE_ROLES")
    @Convert(converter = StringListConverter.class)
    private List<String> sourceRoles = new ArrayList<>();

    @Column(name = "TARGET_ROLES")
    @Convert(converter = StringListConverter.class)
    private List<String> targetRoles = new ArrayList<>();
    
    /**
     * İlişkinin ismi source -> target
     */
    private String vectorName;
    
    /**
     * İlişkinin ismi target -> source ( Yani ters ilişkinin ismi ) 
     */
    private String reversName;
    
    /**
     * Sıralama için bu iliişkinin ağırlığı
     */
    @Column(name = "WEIGHT")
    private Integer weigth = 0;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getSourceRoles() {
        return sourceRoles;
    }

    public void setSourceRoles(List<String> sourceRoles) {
        this.sourceRoles = sourceRoles;
    }

    public List<String> getTargetRoles() {
        return targetRoles;
    }

    public void setTargetRoles(List<String> targetRoles) {
        this.targetRoles = targetRoles;
    }

    public String getVectorName() {
        return vectorName;
    }

    public void setVectorName(String vectorName) {
        this.vectorName = vectorName;
    }

    public String getReversName() {
        return reversName;
    }

    public void setReversName(String reversName) {
        this.reversName = reversName;
    }

    public Integer getWeigth() {
        return weigth;
    }

    public void setWeigth(Integer weigth) {
        this.weigth = weigth;
    }

    

}
