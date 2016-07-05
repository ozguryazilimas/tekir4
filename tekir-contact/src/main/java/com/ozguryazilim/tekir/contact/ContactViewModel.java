package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.entities.Contact;
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

    public ContactViewModel(Long id, String code, String name, String info, Boolean active, Class<? extends Contact> type) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.info = info;
        this.active = active;
        this.contactClass = type;
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
