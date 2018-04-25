/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.contact.information.ContactInformationConsts;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
import com.ozguryazilim.tekir.entities.AbstractPerson;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactPhone;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.messages.Messages;
import com.ozguryazilim.telve.quick.QuickRecord;
import com.ozguryazilim.telve.quick.QuickRecordBase;
import javax.inject.Inject;

/**
 * Hızlı Kişi Kaydı.
 * 
 * @author Hakan Uygun
 */
@QuickRecord(page = ContactPages.QuickPersonPanel.class)
public class QuickPersonRecord extends QuickRecordBase{

    @Inject
    private ContactRepository repository;
    
    @Inject
    private Identity identity;
    
    @Inject
    private AutoCodeService codeService;
    
    private Person entity;
    
    private String email;
    private String mobile;

    
    protected Person newPerson(){
        Person p = new Person();
        p.getContactRoles().add("CONTACT");
        p.getContactRoles().add("PERSON");
        p.setOwner(identity.getLoginName());
        p.setCode(codeService.getNewSerialNumber(Person.class.getSimpleName()));
        return p;
    }
    
    public Person getEntity() {
        if( entity == null ){
            entity = newPerson();
        }
        return entity;
    }

    public void setEntity(Person entity) {
        this.entity = entity;
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
    
    protected ContactPhone newMobilePhone(){
        ContactPhone phone = new ContactPhone();
        phone.setContact(getEntity());
        phone.setNumber(mobile);
        phone.getSubTypes().add(ContactInformationConsts.PhoneSubTypes.MOBILE);
        phone.getRoles().add(ContactInformationConsts.Roles.PRIMARY);
        return phone;
    }
    
    protected ContactEMail newEmail(){
        ContactEMail eMail = new ContactEMail();
        eMail.setContact(getEntity());
        eMail.setEmailAddress(email);
        eMail.getRoles().add(ContactInformationConsts.Roles.PRIMARY);
        return eMail;
    }
    
    
    @Override
    protected boolean doSave() {
        try{
            getEntity().setName(((AbstractPerson) getEntity()).getFirstName() + " " + ((AbstractPerson) getEntity()).getLastName());
            
            //PrimaryMobile and EMail
            if( !Strings.isNullOrEmpty(mobile)){
                getEntity().setPrimaryMobile(newMobilePhone());
            }
            
            if( !Strings.isNullOrEmpty(email)){
                getEntity().setPrimaryEmail(newEmail());
            }
            
            repository.save(getEntity());
            
            //FIXME: Feeder çalıştırılmalı
            
            FacesMessages.info("contact.messages.PersonSuccessfullySaved", Messages.getMessage("contact.messages.PersonSuccessfullySavedDetail", getEntity().getName()));
            return true;
        } catch (Exception e ){
            FacesMessages.error("general.message.exception.Exception", e.getLocalizedMessage());
            return false;
        }
    }
    
}
