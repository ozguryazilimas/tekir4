/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Fırsat fişi veri modeli
 * 
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TCO_OPPOTUNITY")
public class Opportunity extends VoucherBase{

    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    /**
     * Fırsatın konusu, başlığı
     */
    @Column(name = "TOPIC")
    private String topic;
    
    /**
     * Hangi müşteri için
     */
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_OPP_ACC"))
    private Contact account;
    
    @ManyToOne
    @JoinColumn(name = "CONTACT_ID", foreignKey = @ForeignKey(name = "FK_OPP_PER"))
    private Person primaryContact;
    
    //Burada aslında farklı bir detay olmalı ve roller yazılmalı
    //private List<Person> stakeHolders;
    
    //Money tipinde olacak
    //@Column(name = "BUDGET")
    //private String budget;
    
    /**
     * Tahmini satış zamanı
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "EST_CLOSE_DATE")
    private Date estimatedCloseDate;
    
    /**
     * satış olasılığı : Yüzde cinsinden
     */
    @Column(name="PROBABILITY")
    private Integer probability;
    
    /**
     * Satış fırsatına konu olan Commodity'ler
     * 
     * Bu detayların girişmesi zorunlu olmamalı. Teklif'e kadar bilinmiyor olabilir. Sistemde tanımlı olmayabilir.
     */
    //private List<String> items;
    
    
    /**
     * Durum nedir? Ön görülen nedir?
     */
    @Column(name="SITUATIION")
    private String situation;
    
    /**
     * Müşteri isterleri nedir?
     */
    @Column(name="CUSTOMER_NEED")
    private String customerNeed;
    
    
    /**
     * Önerilecek çözüm nedir?
     */
    @Column(name="SOLUTION")
    private String solution;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Contact getAccount() {
        return account;
    }

    public void setAccount(Contact account) {
        this.account = account;
    }

    public Person getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(Person primaryContact) {
        this.primaryContact = primaryContact;
    }

    public Date getEstimatedCloseDate() {
        return estimatedCloseDate;
    }

    public void setEstimatedCloseDate(Date estimatedCloseDate) {
        this.estimatedCloseDate = estimatedCloseDate;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getCustomerNeed() {
        return customerNeed;
    }

    public void setCustomerNeed(String customerNeed) {
        this.customerNeed = customerNeed;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
    
    /**
     * Fırsat için durum bilgisi : Bilgi Toplama, Stratejik olarak yapılmayacak? : Aslında bunlar statusReason gibi duruyor. Örneğin Open için : Bilgi Toplama, Strateji Kararı Bekleme, Yapılabilirlik Bekleme gibi
     */
    //private String status;
    
    
    
}
