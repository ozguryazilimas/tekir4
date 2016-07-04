/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bir contact ile gerçekleştirilen her hangi bir iletişim aktivitesini tanımlar.
 * 
 * @author Hakan Uygun
 */
public class Activity extends EntityBase{

    
    private Long id;
    
    /**
     * Kimle : bir aktivite aslında hep bir person ile olacak.
     */
    private Person person;
    /**
     * Hangi firma adına. 
     * Contact/Person zaman içinde farklı firmalarda çalışıyor olabilir.
     */
    private Corporation corporation;
    
    /**
     * Hangi belge ile ilşikili?
     * Aslında burada 3lü bir yapı gerek.
     * Belge Türü, PK, ve BK
     */
    private String regarding;
    
    
    /**
     * Bu iletişimin yönü nedir?
     * Incoming / Outgoing
     */
    private String direction;
    
    private String status;
    private String statusReason;
    
    
    /**
     * Bu görüşme ne zaman yapılacak?
     */
    private Date dueDate;
    
    /**
     * Bu iletişimin yapıldığı tarih / saat
     */
    private Date date;
    
    /**
     * Dakika cinsinden ne kadar sürdüğü
     */
    private Long duration;
    
    /**
     * Öncelik
     */
    private String priority;
    
    /**
     * Kimin yapacağı : User
     */
    private String assignee;
    
    /**
     * Görüşme konusu
     */
    private String subject;
    /**
     * Görüşme içeriği
     */
    private String body;
    
    /**
     * Konu hakkında ek not
     */
    private String note;
    
    
    /**
     * Sonuç notu
     */
    private String resultNote;
    
    /**
     * Bir sonraki iletişim adımı varsa o. Nasıl olacak bilemiyorum...
     */
    private String followup;
    
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<String> getResultStatus(){
        List<String> ls = new ArrayList<>();
        
        ls.add("Closed");
        ls.add("Canceled");
        
        return ls;
    }
    
}
