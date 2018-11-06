/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.tekir.entites.converters.StringListConverter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * İşe Alım işlemi için ya da CV havuzu için Başvuran Kişi : Aday
 * 
 * 
 * Eğitim Bilgileri, Çalışma Bilgileri, Sertifika Bilgileri gibi şeyler ayrı tablolardan arayüz üzerinden çekilecek. Model olarak doğrudan bir bağlantı kurmayacağız.
 * 
 * Bunun etkisi olarak Repository becerisi olarak silindiğinde önce detaylar silinmeli. 
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("APPLICANT")
public class Applicant extends AbstractPerson{
    
    /**
     * Etiket olarak yetnek/bilgi/beceri bilgileri
     */
    @Column( name = "SKILLS")
    @Convert(converter = StringListConverter.class)
    private List<String> skills = new ArrayList<>();
    
    
    /**
     * Değerlendirme sonuçlarının etiket olarak tanımlanacağı bir yapı
     */
    @Column( name = "CLASSIFICATIONS")
    @Convert(converter = StringListConverter.class)
    private List<String> classifications = new ArrayList<>();
    
    
    /**
     * Değerlendirme sonuçlarına göre verilebilecek 1-5 arası bir puan arayüzde yıldız v.s. yapılır.
     */
    @Column( name = "RATING")
    private Integer rating = 0;

    /**
     * Evli mi?
     * TODO: Bu alan boolean dışına çıkabilir. Evli, Bekar, Dul gibi vs.
     */
    @Column( name = "MARRIED")
    private Boolean married = Boolean.FALSE;

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<String> classifications) {
        this.classifications = classifications;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }
    
    
    
}
