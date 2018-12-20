package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.telve.entities.EntityBase;
import java.util.Date;

/**
 * Bir contact ile ilgili gerçekleşen her türlü olay buraya bir kayıt atacak
 * 
 * FIXME: Bu sınıf burada mı durmalı? Yoksa başka bir yere mi taşınmalı?
 * 
 * @author Hakan Uygun
 */
public class ContactStream extends EntityBase{

    private Long id;
    
    private Contact contact;
    private Corporation corporation;
    /**
     * Bu stream'in kaynağı neresi?
     */
    private String source;
    private Long sourceId;
    private String sourceBizKey;
    
    /**
     * Stream tarih/saat
     */
    private Date date;
    
    private String subject;
    private String body;
    
    /**
     * Stream'in b
     * ir durum bilgisi var ise onlar.
     */
    private String status;
    private String statusReason;
    
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
