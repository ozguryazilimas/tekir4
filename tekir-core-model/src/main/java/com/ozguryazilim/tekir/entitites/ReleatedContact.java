/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

import com.ozguryazilim.telve.entities.EntityBase;

/**
 *
 * @author oyas
 */

public class ReleatedContact extends EntityBase{

    //Bu bilgi hangi contactın
    private Contact sourceContact;
    //Hedef bağlantı kim
    private Contact targetContact;
    //İlişki türü ne
    private ContactRelation  releation;
    
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
