/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

import com.ozguryazilim.telve.entities.EntityBase;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Temel Bağlantı Entity Sınıfı
 * 
 * Individual/Organizastional tüm bağlantılar bu sınıf üzerinde modellenecekler.
 * 
 * Account/Employee/Lead gibi sınıflar bu sınıfı kullanacaklar.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TKR_CONTACT")
public class Contact extends EntityBase{

    @Id 
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
            
    
    /**
     * Contact için temel isim. Her yerde bu kullanılacak.
     * Individual için firstName + lastName birleşimi
     * Organizastion için verilecek bir kısa isim
     */
    @Column( name = "NAME")
    private String name;
            
    
    
    /**
     * Vergi bilgileri. Bireysel kullanıcılar için de lazım olabilir.
     * Bu bilgiler Account'a mı gitse?
     * Suggestion
     */
    private String taxOffice;
    private String taxNumber;
    
    
    
    private ContactType type = ContactType.Individual;
    
    /**
     * Bir contact'ın hangi rollerde olduğu. 
     * 
     * Virgüllerle ayrılmış bir şekilde birden fazla role sahip olabilir.
     * 
     * Contact, Account, Customer, Vendor, Partner, Competitor, Lead?, Employee?
     * 
     * Bu role bilgileri aslında sistemde tanımlı role registiry'den alınabilirler. Enum olamazlar çünkü farklı modüllerden yeni roller gelecek.
     */
    private String contactRoles = "Contact";
    
    
    /**
     * Kaydın aktif olup olmadığı
     */
    private Boolean active = Boolean.TRUE;
    
    /**
     * Acaba buna ihtiyaç olur mu? Bir contact'ın statu bilgisi : Draft ( LEAD ), Active, Passive, Acrhive v.b.?!
     * 
     */
    private String status;
    
    /**
     * Status'e neden düştüğü ile küçük bir açıklama
     */
    private String statusReason;
    
    
    /**
     * Birincil, varsayılan iletişim bilgileri.
     * Bu değerler aslında bir detay olarak tutulacak.
     * Orada tanımlı olan bilgilerden varsayılanlar otomatik getirilecek.
     */
    private String primaryMobile;
    private String primaryPhone;
    private String primaryEmail;
    private String primaryFax;
    private String primaryAddress;
    private String primaryIM;
    
    
    /**
     * Tercih edilen iletişim yöntemi. 
     * 
     * Birden fazla olabilir. Virgüllerle ayrılır.
     * 
     * ANY - Hepsi
     * None - Hiç biri
     * 
     * SMS, Phone, Mobile, Email, Fax, Post, IM v.b.
     * 
     */
    private String communicationChannels = "ANY";
    
    /**
     * Bütün iletişim bilgileri buraya toparlansın.
     */
    private List<ContactInformation> contactInformations = new ArrayList<>();
    
    /**
     * Ek veri giriş alanları için key value bir veri modeli olması iyi olur.
     */
    private List<String> fields = new ArrayList<>();
    
    
    /**
     * Bu contact ile ilişkili olan diğer contactların listesi
     * 
     * Örneğin bir firmanın bağlantı kişileri.
     */
    private List<ReleatedContact> reletadContacts = new ArrayList<>();
    
    /**
     * Tanımlanacak olan bölgelerden hangisine üye olduğu
     */
    private Territory territory;
    
    
    /**
     * Bu contact'ın kime ( hangi kullanıcıya ) ait olduğu
     */
    private String owner;
    
    /**
     * İç organizasyon içinde nereye ait oludğuı ( Merkez, şube v.b. )
     */
    private String organization;
    
    /**
     * Hangi takımın bu contact ile ilgilendiği
     */
    private String team;
    
    
    /**
     * Bu contact'ın kaynağının neresi olduğu.
     * 
     * Farklı sistemler buraya farklı değerler yazabiirler.
     */
    private String source;
    
    
    /**
     * İlgilendiği ürün/service v.s.
     */
    private List<ContactInterestedCommodity> interestedCommodities = new ArrayList<>();
    
    /**
     * Ağaç bir model üzerinden category tanımı
     */
    private ContactCategory category;
    
    /**
     * Hangi iş alanında 
     */
    private Industry industry;
    
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
