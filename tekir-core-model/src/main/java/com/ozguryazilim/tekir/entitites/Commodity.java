/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

import com.ozguryazilim.telve.entities.EntityBase;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

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
@Table( name = "TC_COMMOTITY" )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Commodity extends EntityBase{

    @Id
    private Long id;
    
    private String code;
    private String name;
    private String info;
    private Boolean active = Boolean.TRUE;
    
    
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
