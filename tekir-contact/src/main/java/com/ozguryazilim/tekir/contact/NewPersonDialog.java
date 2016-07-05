/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Person;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.primefaces.context.RequestContext;

/**
 * Yeni Kişi Tanım Popup Controller
 * 
 * @author Hakan Uygun
 */
@Named
@SessionScoped
public class NewPersonDialog implements Serializable{
    
    @Inject
    private ViewConfigResolver viewConfigResolver;
    
    @Inject 
    private ContactRepository repository;
    
    
    private Person contact;
    
    public void openDialog() {
        contact = new Person();
        
        contact.getContactRoles().add("CONTACT");
        contact.getContactRoles().add("PERSON");
        
        openDialogImpl();
    }
    
    protected void openDialogImpl() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        //options.put("draggable", false);  
        options.put("resizable", false);
        options.put("width", "780");
        options.put("height", "430");
        //options.put("contentWidth", 780);
        options.put("contentHeight", 450);
        options.put("position", "center top");

        RequestContext.getCurrentInstance().openDialog(getDialogName(), options, null);
    }
    
    /**
     * Yeni contact'ı save eder.
     */
    public void closeDialog() {
        //Person'ının ad soyadından bütünleşik isim elde ediyoruz.
        contact.setName( contact.getFirstName() + " " + contact.getLastName());
        
        repository.save(contact);
        
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    /**
     * Bir şey yapmadan çık.
     */
    public void cancelDialog() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    /**
     * startPopup dialog adını döndürür.
     *
     * @return
     */
    public String getDialogName() {
        String viewId = viewConfigResolver.getViewConfigDescriptor(ContactPages.NewPersonPopup.class).getViewId();
        return viewId.substring(0, viewId.indexOf(".xhtml"));
    }

    public Person getContact() {
        return contact;
    }

    public void setContact(Person person) {
        this.contact = person;
    }
    
    
}
