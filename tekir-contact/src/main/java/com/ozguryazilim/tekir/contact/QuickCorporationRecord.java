/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.contact.information.ContactInformationConsts;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactPhone;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.quick.QuickRecord;
import com.ozguryazilim.telve.quick.QuickRecordBase;
import javax.inject.Inject;

/**
 *Hızlı Kurum Kaydı.
 * 
 * @author Hakan Uygun
 */
@QuickRecord(page = ContactPages.QuickCorporationPanel.class)
public class QuickCorporationRecord extends QuickRecordBase{
    
    @Inject
    private ContactRepository repository;
    
    @Inject
    private Identity identity;
    
    private Corporation entity;
    
    private String email;
    private String mobile;

    
    protected Corporation newCorporation(){
        Corporation p = new Corporation();
        p.getContactRoles().add("CONTACT");
        p.getContactRoles().add("CORPORATION");
        p.setOwner(identity.getLoginName());
        return p;
    }
    
    public Corporation getEntity() {
        if( entity == null ){
            entity = newCorporation();
        }
        return entity;
    }

    public void setEntity(Corporation entity) {
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
            
            //PrimaryMobile and EMail
            if( !Strings.isNullOrEmpty(mobile)){
                getEntity().setPrimaryMobile(newMobilePhone());
            }
            
            if( !Strings.isNullOrEmpty(email)){
                getEntity().setPrimaryEmail(newEmail());
            }
            
            repository.save(getEntity());
            
            //FIXME: Feeder çalıştırılmalı
            //FIXME: i18n
            FacesMessages.info("Yeni Kurum Kaydı Başarılı", getEntity().getName() + " isimli kurum kaydedildi.");
            return true;
        } catch (Exception e ){
            FacesMessages.error("Hata oluştu", e.getLocalizedMessage());
            return false;
        }
    }
}
