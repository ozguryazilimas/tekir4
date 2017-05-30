/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.telve.view.DialogBase;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 * Yeni Kişi Tanım Popup Controller
 * 
 * @author Hakan Uygun
 */
@Named
@SessionScoped
public class NewPersonDialog extends DialogBase implements Serializable{
    
    @Inject 
    private ContactRepository repository;
    
    
    private Person contact;
    
    @Override
    public void beforeOpenDialog() {
        contact = new Person();
        
        contact.getContactRoles().add("CONTACT");
        contact.getContactRoles().add("PERSON");
        
    }
    
    @Override
    public void closeDialog() {
    	
    	//Person'ının ad soyadından bütünleşik isim elde ediyoruz.
        contact.setName( contact.getFirstName() + " " + contact.getLastName());
        
        repository.save(contact);

        closeDialogWindow();
    }

	@Override
	public Class<? extends ViewConfig> getDialogViewConfig() {
		return ContactPages.NewPersonPopup.class;
	}

    public Person getContact() {
        return contact;
    }

    public void setContact(Person person) {
        this.contact = person;
    }
    
}
