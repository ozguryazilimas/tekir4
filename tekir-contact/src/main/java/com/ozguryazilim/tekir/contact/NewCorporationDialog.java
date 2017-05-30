/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.telve.view.DialogBase;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.NavigationParameterContext;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;

/**
 * Yeni Kurum Tanım Popup Controller
 * @author Hakan Uygun
 */
@Named
@SessionScoped
public class NewCorporationDialog extends DialogBase implements Serializable{
   
    @Inject
    private ViewNavigationHandler viewNavigationHandler;
    
    @Inject
    private NavigationParameterContext navigationParameterContext;

    
    @Inject 
    private ContactRepository repository;
    
    private Corporation contact;
    
    @Override
    public void beforeOpenDialog() {
    	contact = new Corporation();
        
        contact.getContactRoles().add("CONTACT");
        contact.getContactRoles().add("CORPORATION");
    }
    
    /**
     * Yeni person'ı save eder.
     */
    @Override
    public void closeDialog() {
        repository.save(contact);
        
        navigationParameterContext.addPageParameter("eid", contact.getId());
        viewNavigationHandler.navigateTo(ContactPages.ContactView.class);
        
        //RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    @Override
    public Class<? extends ViewConfig> getDialogViewConfig() {
    	return ContactPages.NewCorporationPopup.class;
    }

    public Corporation getContact() {
        return contact;
    }

    public void setContact(Corporation contact) {
        this.contact = contact;
    }

    
     
}
