package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactAddress;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactPhone;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.telve.entities.ViewModel;
import java.io.Serializable;

/**
 * View Model Class
 *
 * @author
 */
public class ContactViewModel implements ViewModel, Serializable {

    private Long id;
    private String code;
    private String name;
    private String info;
    private Boolean active;
    private Class<? extends Contact> contactClass;
    private ContactPhone primaryMobile;
    private ContactPhone primaryPhone;
    private ContactPhone primaryFax;
    private ContactEMail primaryEmail;
    private ContactAddress primaryAddress;

    public ContactViewModel(Long id, 
            String code, 
            String name, 
            String info, 
            Boolean active, 
            Class<? extends Contact> type, 
            Long pmMobileId, String pmMobile, 
            Long pmPhoneId, String pmPhone, 
            Long pmEmailId, String pmEmail
            ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.info = info;
        this.active = active;
        this.contactClass = type;
        
        this.primaryMobile = new ContactPhone();
        this.primaryMobile.setId(pmMobileId);
        this.primaryMobile.setAddress(pmMobile);
        
        this.primaryPhone = new ContactPhone();
        this.primaryPhone.setId(pmPhoneId);
        this.primaryPhone.setAddress(pmPhone);
        
        /*
        this.primaryFax = new ContactPhone();
        this.primaryFax.setId(pmFaxId);
        this.primaryFax.setAddress(pmFax);
        */
        
        this.primaryEmail = new ContactEMail();
        this.primaryEmail.setId(pmEmailId);
        this.primaryEmail.setAddress(pmEmail);
        
        /*
        this.primaryAddress = new ContactAddress();
        this.primaryAddress.setId(pmAddressId);
        this.primaryAddress.setAddress(pmAddress);
        */
    }

    
    
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Class<? extends Contact> getContactClass() {
        return contactClass;
    }

    public void setContactClass(Class<? extends Contact> contactClass) {
        this.contactClass = contactClass;
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

    public ContactPhone getPrimaryFax() {
        return primaryFax;
    }

    public void setPrimaryFax(ContactPhone primaryFax) {
        this.primaryFax = primaryFax;
    }

    public ContactEMail getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(ContactEMail primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public ContactAddress getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(ContactAddress primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getContactType(){
        if( contactClass.equals( Person.class ) ){
            return "Person";
        } else if( contactClass.equals( Corporation.class ) ){
            return "Corporation";
        } else {
            return "Contact";
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContactViewModel)) {
            return false;
        }
        ContactViewModel other = (ContactViewModel) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
