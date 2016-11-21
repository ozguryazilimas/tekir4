/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.tekir.entites.converters.StringListConverter;
import com.ozguryazilim.telve.annotations.BizKey;
import com.ozguryazilim.telve.entities.AuditBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Temel Bağlantı Entity Sınıfı
 * 
 * Individual/Organizastional tüm bağlantılar bu sınıf üzerinde modellenecekler.
 * Alt Sınıflar : Person/Corparation oldular. 
 * 
 * TODO: Aslında bu sınıfta abstract olabilir. Kendisi doğrudan hiç kullanılmayacak
 * 
 * Account/Employee/Lead gibi sınıflar bu sınıfı kullanacaklar.
 * 
 * FIXME: resim ekleme işi nasıl olur? 
 * FIXME: belge havuzu?
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCC_CONTACT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CONTACT_TYPE")
public abstract class Contact extends AuditBase{

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
            
    
    /**
     * Contact için temel isim. Her yerde bu kullanılacak.
     * Individual için firstName + lastName birleşimi
     * Organizastion için verilecek bir kısa isim
     */
    @Column( name = "NAME")
    @NotNull @BizKey
    private String name;
    
    @Column( name = "CODE")
    @NotNull
    private String code;
    
    @Column( name = "INFO")
    private String info;
            
    /**
     * Bir contact'ın hangi rollerde olduğu. 
     * 
     * Virgüllerle ayrılmış bir şekilde birden fazla role sahip olabilir.
     * 
     * Contact, Account, Customer, Vendor, Partner, Competitor, Lead?, Employee?
     * 
     * Bu role bilgileri aslında sistemde tanımlı role registiry'den alınabilirler. Enum olamazlar çünkü farklı modüllerden yeni roller gelecek.
     */
    @Column( name = "ROLES")
    @Convert(converter = StringListConverter.class)
    private List<String> contactRoles = new ArrayList<>();
    
    
    /**
     * Kaydın aktif olup olmadığı
     */
    @Column( name = "ISACTIVE")
    private Boolean active = Boolean.TRUE;
    
    /**
     * Acaba buna ihtiyaç olur mu? Bir contact'ın statu bilgisi : Draft ( LEAD ), Active, Passive, Acrhive v.b.?!
     * FIXME: Burası enum olsa güzel olacak
     */
    @Column( name = "STATUS")
    private String status;
    
    /**
     * Status'e neden düştüğü ile küçük bir açıklama
     */
    @Column( name = "STATUS_REASON")
    private String statusReason;
    
    
    /**
     * Birincil, varsayılan iletişim bilgileri.
     * Bu değerler aslında bir detay olarak tutulacak.
     * Orada tanımlı olan bilgilerden varsayılanlar otomatik getirilecek.
     * 
     * TODO: Bu değerler çekilirken gereksiz bir yığın sorgu da çekilmese iyi olur aslında
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MOBILE_ID", foreignKey = @ForeignKey(name = "FK_CON_MOBILE"))
    private ContactPhone primaryMobile;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PHONE_ID", foreignKey = @ForeignKey(name = "FK_CON_PHONE"))
    private ContactPhone primaryPhone;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "EMAIL_ID", foreignKey = @ForeignKey(name = "FK_CON_EMAIL"))
    private ContactEMail primaryEmail;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FAX_ID", foreignKey = @ForeignKey(name = "FK_CON_FAX"))
    private ContactPhone primaryFax;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ADDR_ID", foreignKey = @ForeignKey(name = "FK_CON_ADDR"))
    private ContactAddress primaryAddress;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "BANK_ID", foreignKey = @ForeignKey(name = "FK_CON_BANK"))
    private ContactBank primaryBank;

    
    
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
     * TODO: Buradaki değerleri bir Enum'dan mı alsak?
     * Liste olup converter ile tutabiliriz belki?
     */
    @Column( name = "CHANNELS")
    @Convert(converter = StringListConverter.class)
    private List<String> communicationChannels = new ArrayList<>();
    
    /**
     * Bütün iletişim bilgileri buraya toparlansın.
     * 
     * Bunun için reletion vermeye gerek yok gerekirse sorgu ile ( Repository ) alabiliriz.
     * Contact çekerken gereksiz bir yığın sorguya neden oluyor.
     * 
     */
    //@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    //@LazyCollection(LazyCollectionOption.FALSE)
    //private List<ContactInformation> contactInformations = new ArrayList<>();
    
    /**
     * Ek veri giriş alanları için key value bir veri modeli olması iyi olur.
     * FIXME: UserAttributes gibi bir yapı düşünelim.
     */
    //private List<String> fields = new ArrayList<>();
    
    
    /**
     * Bu contact ile ilişkili olan diğer contactların listesi
     * 
     * Örneğin bir firmanın bağlantı kişileri.
     */
    //@OneToMany(mappedBy = "sourceContact", cascade = CascadeType.ALL, orphanRemoval = true)
    //@LazyCollection(LazyCollectionOption.FALSE)
    //private List<ReleatedContact> reletadContacts = new ArrayList<>();
    
    /**
     * Tanımlanacak olan bölgelerden hangisine üye olduğu
     */
    @ManyToOne
    @JoinColumn(name = "TERRITORY_ID", foreignKey = @ForeignKey(name = "FK_CON_TER"))
    private Territory territory;
    
    
    /**
     * Bu contact'ın kime ( hangi kullanıcıya ) ait olduğu
     */
    @Column(name="OWNER")
    private String owner;
    
    /**
     * İç organizasyon içinde nereye ait oludğuı ( Merkez, şube v.b. )
     * TODO: Burasını user group ile halledebilir miyiz?
     */
    @Column(name="OWNER_ORG")
    private String organization;
    
    /**
     * Hangi takımın bu contact ile ilgilendiği
     * FIXME: Burası kesin user group ile yapılack bişi dolayısı ile telve-idm'e doğrudan bağlasak mı?
     */
    @Column(name="OWNER_TEAM")
    private String team;
    
    
    /**
     * Bu contact'ın kaynağının neresi olduğu.
     * 
     * Farklı sistemler buraya farklı değerler yazabiirler.
     */
    @Column(name = "SOURCE")
    private String source;
    
    
    /**
     * İlgilendiği ürün/service v.s.
     */
    //@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    //@LazyCollection(LazyCollectionOption.FALSE)
    //private List<ContactInterestedCommodity> interestedCommodities = new ArrayList<>();
    
    /**
     * Ağaç bir model üzerinden category tanımı
     */
    @ManyToOne
    @JoinColumn(name = "CAT_ID", foreignKey = @ForeignKey(name = "FK_CON_CAT"))
    private ContactCategory category;
    
    /**
     * Hangi iş alanında 
     */
    @ManyToOne
    @JoinColumn(name = "INDUSTRY_ID", foreignKey = @ForeignKey(name = "FK_CON_INDUSTRY"))
    private Industry industry;

    //////////////////////////////////////////
    // Account ile ilgili bilgiler
    //////////////////////////////////////////
    
    /**
     * Vergi bilgileri. Bireysel kullanıcılar için de lazım olabilir.
     * Bu bilgiler Account'a mı gitse?
     * Suggestion
     */
    @Column( name = "TAX_OFFICE")
    private String taxOffice;
    
    @Column( name = "TAX_NUMBER")
    private String taxNumber;
    
    
    /**
     * Banka hesap bilgisi : aslında birden fazla olabilir. Nasıl yapsak?
     */
    @Column( name = "BANK_ACCOUNT")
    private String bankAccount;
    
    /**
     * Hangi döviz ile çalışıldığı ISO kodu olarak tutulacak.
     */
    @Column( name = "CCY")
    private String currency;
    
    /**
     * Bu contact'ın üretilmesinde rol oynayan belge.
     * Genelde bir Lead olacaktır.
     */
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "feature", column = @Column(name = "SOURCE_FP")),
        @AttributeOverride(name = "primaryKey", column = @Column(name = "SOURCE_PK")),
        @AttributeOverride(name = "businessKey", column = @Column(name = "SOURCE_BK")),
    })
    private FeaturePointer sourcePointer = new FeaturePointer();
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContactRoles() {
        return contactRoles;
    }

    public void setContactRoles(List<String> contactRoles) {
        this.contactRoles = contactRoles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public ContactPhone getPrimaryMobile() {
        return primaryMobile;
    }

    public void setPrimaryMobile(ContactPhone primaryMobile) {
        this.primaryMobile = primaryMobile;
    }

    public ContactPhone getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(ContactPhone primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public ContactEMail getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(ContactEMail primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public ContactPhone getPrimaryFax() {
        return primaryFax;
    }

    public void setPrimaryFax(ContactPhone primaryFax) {
        this.primaryFax = primaryFax;
    }

    public ContactAddress getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(ContactAddress primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public ContactBank getPrimaryBank() {
        return primaryBank;
    }

    public void setPrimaryBank(ContactBank primaryBank) {
        this.primaryBank = primaryBank;
    }

    public List<String> getCommunicationChannels() {
        return communicationChannels;
    }

    public void setCommunicationChannels(List<String> communicationChannels) {
        this.communicationChannels = communicationChannels;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ContactCategory getCategory() {
        return category;
    }

    public void setCategory(ContactCategory category) {
        this.category = category;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public FeaturePointer getSourcePointer() {
        return sourcePointer;
    }

    public void setSourcePointer(FeaturePointer sourcePointer) {
        this.sourcePointer = sourcePointer;
    }
    
    
    
}
