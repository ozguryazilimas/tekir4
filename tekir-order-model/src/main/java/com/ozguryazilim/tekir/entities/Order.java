/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Sipariş taban modeli. 
 * 
 * Alış ve satış siparişleri bu sınıftan türeyecekler
 * 
 * @author Hakan Uygun
 */
@MappedSuperclass
public class Order extends VoucherBase{

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    
    /**
     * Hangi müşteri için
     */
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_OPP_ACC"))
    private Contact account;
    
    
    /**
     * Teslimat tarihi
     */
    @Temporal(TemporalType.DATE)
    @Column( name = "SHIP_DATE")
    private Date shippingDate;
    
    
    //Commodity satırları
    //Toplam tutar
    //Toplam Vergi
    //Indirim v.s. varsa onlar
    
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
