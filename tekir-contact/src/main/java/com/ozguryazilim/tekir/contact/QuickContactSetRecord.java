/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.contact.information.ContactInformationConsts;
import com.ozguryazilim.tekir.entities.ContactCategory;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactPhone;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.CorporationType;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.quick.QuickRecord;
import com.ozguryazilim.telve.quick.QuickRecordBase;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@QuickRecord(page = ContactPages.QuickContactSetPanel.class)
public class QuickContactSetRecord extends QuickRecordBase{

    
    @Inject
    private ContactRepository repository;
    
    @Inject
    private Identity identity;
    
    private String corporationName;
    private String corporationFullName;
    private String corporationEmail;
    private String corporationPhone;
    private CorporationType corporationType;
    private ContactCategory category;
    
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String jobTitle;
    
    private String code;
    private String info;

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public String getCorporationFullName() {
        return corporationFullName;
    }

    public void setCorporationFullName(String corporationFullName) {
        this.corporationFullName = corporationFullName;
    }

    public String getCorporationEmail() {
        return corporationEmail;
    }

    public void setCorporationEmail(String corporationEmail) {
        this.corporationEmail = corporationEmail;
    }

    public String getCorporationPhone() {
        return corporationPhone;
    }

    public void setCorporationPhone(String corporationPhone) {
        this.corporationPhone = corporationPhone;
    }

    public CorporationType getCorporationType() {
        return corporationType;
    }

    public void setCorporationType(CorporationType corporationType) {
        this.corporationType = corporationType;
    }

    public ContactCategory getCategory() {
        return category;
    }

    public void setCategory(ContactCategory category) {
        this.category = category;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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


    
    
    protected Corporation createCorporation(){
        Corporation p = new Corporation();
        p.getContactRoles().add("CONTACT");
        p.getContactRoles().add("CORPORATION");
        p.setOwner(identity.getLoginName());
        return p;
    }
    
    protected Person createPerson(){
        Person p = new Person();
        p.getContactRoles().add("CONTACT");
        p.getContactRoles().add("PERSON");
        p.setOwner(identity.getLoginName());
        return p;
    }
    
    protected ContactPhone newPhone(){
        ContactPhone phone = new ContactPhone();
        phone.setNumber(corporationPhone);
        phone.getSubTypes().add(ContactInformationConsts.PhoneSubTypes.LAND);
        phone.getRoles().add(ContactInformationConsts.Roles.PRIMARY);
        return phone;
    }
    
    protected ContactPhone newMobilePhone(){
        ContactPhone phone = new ContactPhone();
        phone.setNumber(mobile);
        phone.getSubTypes().add(ContactInformationConsts.PhoneSubTypes.MOBILE);
        phone.getRoles().add(ContactInformationConsts.Roles.PRIMARY);
        return phone;
    }
    
    protected ContactEMail newEmail( String email){
        ContactEMail eMail = new ContactEMail();
        eMail.setEmailAddress(email);
        eMail.getRoles().add(ContactInformationConsts.Roles.PRIMARY);
        return eMail;
    }
    
    
    @Override
    protected boolean doSave() {
        try{
            
            //Önce şirketi kaydedelim.
            Corporation corp = createCorporation();
            corp.setName(corporationName);
            corp.setOrganizastionName(corporationFullName);
            corp.setInfo(info);
            corp.setCorporationType(corporationType);
            corp.setCategory(category);
            corp.setCode(code + "C");
            
            
            //PrimaryMobile and EMail
            if( !Strings.isNullOrEmpty(corporationPhone)){
                ContactPhone phone = newPhone();
                phone.setContact(corp);
                corp.setPrimaryPhone(phone);
            }
            
            if( !Strings.isNullOrEmpty(corporationEmail)){
                ContactEMail email = newEmail(corporationEmail);
                email.setContact(corp);
                corp.setPrimaryEmail(email);
            }
            
            //Geriye kaydelen şeyi alalım çünkü id lazım.
            corp = (Corporation) repository.save(corp);
            
            
            //Şimdi Person kaydı
            
            Person person = createPerson();
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setName(firstName + " " + lastName);
            person.setJobTitle(jobTitle);
            person.setCode(code + "P");
            
            //PrimaryMobile and EMail
            if( !Strings.isNullOrEmpty(mobile)){
                ContactPhone phone = newMobilePhone();
                phone.setContact(person);
                person.setPrimaryPhone(phone);
            }
            
            if( !Strings.isNullOrEmpty(this.email)){
                ContactEMail email = newEmail( this.email);
                email.setContact(person);
                person.setPrimaryEmail(email);
            }
            
            //Şirketi bağlayalım
            person.setCorporation(corp);
            person = (Person) repository.save(person);
            
            //Şimdi de şirkete person bağlayalım
            corp.setPrimaryContact(person);
            corp = (Corporation) repository.save(corp);
            
            //FIXME: Feeder çalıştırılmalı
            //FIXME: i18n
            FacesMessages.info("Yeni Bağlantı Kaydı Başarılı", person.getName() + " ve "+ corp.getName() + " isimli kişi ve kurum kaydedildi.");
            return true;
        } catch (Exception e ){
            FacesMessages.error("Hata oluştu", e.getLocalizedMessage());
            return false;
        }
    }
    
}
