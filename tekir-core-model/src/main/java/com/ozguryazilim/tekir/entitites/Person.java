/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

/**
 * Gerçek Kişi
 * 
 * @author Hakan  Uygun
 */
public class Person extends Contact{
    
    
    private String firstname;
    private String secondname;
    private String lastname;
    /**
     * Kullandığı isim. Bazı kişilerin birden fazla adı olabiliyor ve bunlardan birini tercih edebiliyor
     * Burada acaba hitap şekli ile birlikte bir yöntem mi izlesek? Mr. Ms. v.b.?
     */
    private String useName;
    
    
    /**
     * Bireysel contactlar için
     * TC Kimlik Numarası / Sosyal Güvenlik Numarası / Pasaport Numarası v.b.
     */
    private String ssn;
            
    /**
     * Bireyler için iş ünvanı / çalıştığı yerdeki pozisyonu
     *
     */
    private String jobTitle;
            
    /**
     * Biresyler için eğer kurumsal bağlantı kartı açılmamış ise çelıştığı şirket adı
     */
    private String corporationName;
    
    /**
     * Kişinin çalıştığı şirtket bağlantısı.
     */
    private Corporation corporation;
    
    private String sex;
    
}
