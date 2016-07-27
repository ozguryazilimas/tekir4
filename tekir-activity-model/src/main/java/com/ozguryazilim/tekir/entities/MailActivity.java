/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Normal posta ile gönderi.
 * 
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("MAIL")
public class MailActivity extends Activity{
    
    /**
     * Gönderinin yapıldığı adres
     */
    @ManyToOne()
    @JoinColumn(name = "ADDR_ID", foreignKey = @ForeignKey(name = "FK_ACC_ADDR"))
    private ContactAddress address;
    
}
