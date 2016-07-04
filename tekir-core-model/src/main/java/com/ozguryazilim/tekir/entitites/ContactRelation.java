/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

import com.ozguryazilim.telve.entities.ParamEntityBase;

/**
 * Contact'lar arası ilişki tipi
 * @author Hakan Uygun
 */
public class ContactRelation extends ParamEntityBase{

    
    /**
     * Kullanılabileceği contact roleler
     * 
     * Virgüllerle ayrılmış bir şekilde tutulur.
     * 
     * ALL diye bir tip de eklemk lazım mı?
     */
     private String roles; 
    
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
