/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Gerçek Kişi
 * 
 * @author Hakan  Uygun
 */
@Entity
@DiscriminatorValue("PERSON")
public class Person extends Contact{
    
    
    @Column(name = "FIRST_NAME")
    private String firstName;
    
    @Column(name = "SECOND_NAME")
    private String secondName;
    
    @Column(name = "LAST_NAME")
    private String lastName;
    
    /**
     * Kullandığı isim. Bazı kişilerin birden fazla adı olabiliyor ve bunlardan birini tercih edebiliyor
     * Burada acaba hitap şekli ile birlikte bir yöntem mi izlesek? Mr. Ms. v.b.?
     */
    @Column(name = "USE_NAME")
    private String useName;
    
    
    /**
     * Bireysel contactlar için
     * TC Kimlik Numarası / Sosyal Güvenlik Numarası / Pasaport Numarası v.b.
     */
    @Column(name = "SSN")
    private String ssn;
            
    /**
     * Bireyler için iş ünvanı / çalıştığı yerdeki pozisyonu
     *
     */
    @Column(name = "JOB_TITLE")
    private String jobTitle;
            
    /**
     * Biresyler için eğer kurumsal bağlantı kartı açılmamış ise çelıştığı şirket adı
     */
    @Column(name = "COMPANY_TITLE")
    private String corporationName;
    
    /**
     * Kişinin çalıştığı şirtket bağlantısı.
     */
    @ManyToOne
    @JoinColumn(name = "CORP_ID", foreignKey = @ForeignKey(name = "FK_PER_CORP"))
    private Corporation corporation;
    
    @Column(name = "GENDER")
    private Gender gender = Gender.UNKNOWN;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public Contact getPrimaryContact(){
        return this;
    }
    
}
