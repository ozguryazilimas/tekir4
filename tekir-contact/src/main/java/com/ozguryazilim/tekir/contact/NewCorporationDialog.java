/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Corporation;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.deltaspike.core.api.config.view.navigation.NavigationParameterContext;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.primefaces.context.RequestContext;

/**
 * Yeni Kurum Tanım Popup Controller
 * @author Hakan Uygun
 */
@Named
@SessionScoped
public class NewCorporationDialog implements Serializable{
   
    @Inject
    private ViewConfigResolver viewConfigResolver;
    
    @Inject
    private ViewNavigationHandler viewNavigationHandler;
    
    @Inject
    private NavigationParameterContext navigationParameterContext;

    
    @Inject 
    private ContactRepository repository;
    
    private Corporation contact;
    
    public void openDialog() {
        contact = new Corporation();
        
        contact.getContactRoles().add("CONTACT");
        contact.getContactRoles().add("CORPORATION");
        
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
     * Yeni person'ı save eder.
     */
    public void closeDialog() {
        repository.save(contact);
        
        navigationParameterContext.addPageParameter("eid", contact.getId());
        viewNavigationHandler.navigateTo(ContactPages.ContactView.class);
        
        //RequestContext.getCurrentInstance().closeDialog(null);
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
        String viewId = viewConfigResolver.getViewConfigDescriptor(ContactPages.NewCorporationPopup.class).getViewId();
        return viewId.substring(0, viewId.indexOf(".xhtml"));
    }

    public Corporation getContact() {
        return contact;
    }

    public void setContact(Corporation contact) {
        this.contact = contact;
    }

    
     
}
