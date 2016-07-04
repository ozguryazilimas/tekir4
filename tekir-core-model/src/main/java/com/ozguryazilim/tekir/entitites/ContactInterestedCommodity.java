/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

import com.ozguryazilim.telve.entities.EntityBase;
import javax.persistence.Entity;

/**
 * Contact'ın ilgilendiği ürünlerin bilgisi.
 * 
 * @author Hakan Uygun
 */
@Entity
public class ContactInterestedCommodity extends EntityBase{

    
    private Contact contact;
    
    private Commodity commodity;
    
    private String info;
    
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
