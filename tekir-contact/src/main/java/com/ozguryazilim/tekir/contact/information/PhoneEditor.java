/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactPhone;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.primefaces.context.RequestContext;

/**
 * Contact Phone Editor.
 * 
 * Yeni ya da var olan bir taneyi editlemek için controller sınıf
 * 
 * @author Hakan Uygun
 */
@Named
@SessionScoped
public class PhoneEditor implements Serializable {
    
    @Inject
    private ViewConfigResolver viewConfigResolver;
    
    @Inject
    private ContactRepository contactRepository;
    
    @Inject
    private ContactInformationRepository contactInContactRepository;
    
    private ContactPhone phone;
    
    private Contact contact;
    
    private Boolean primaryMobile = Boolean.FALSE;
    private Boolean primaryPhone = Boolean.FALSE;
    private Boolean primaryFax = Boolean.FALSE;
    
    
    public void newPhone( Contact contact ){
        init();
        
        this.contact = contact;
        this.phone = new ContactPhone();
        phone.setContact(contact);
    
        openDialogImpl();
    }
    
    public void editPrimaryMobile( Contact contact, ContactPhone phone ){
        
        init();
                
        this.contact = contact;
        if( phone != null ){
            this.phone = phone;
        } else {
            this.phone = new ContactPhone();
        }
        
        this.phone.setContact(contact);
        this.phone.getRoles().add("MOBILE");
        this.phone.getRoles().add("PRIMARY");
    
        primaryMobile = Boolean.TRUE;
        
        openDialogImpl();
    }
    
    public void editPrimaryPhone( Contact contact, ContactPhone phone ){
        
        init();
        
        this.contact = contact;
        if( phone != null ){
            this.phone = phone;
        } else {
            this.phone = new ContactPhone();
        }
        this.phone.setContact(contact);
        this.phone.getRoles().add("PHONE");
        this.phone.getRoles().add("PRIMARY");
    
        primaryPhone = Boolean.TRUE;
        
        openDialogImpl();
    }
    
    public void editPrimaryFax( Contact contact, ContactPhone phone ){
        init();
        
        this.contact = contact;
        if( phone != null ){
            this.phone = phone;
        } else {
            this.phone = new ContactPhone();
        }
        this.phone.setContact(contact);
        this.phone.getRoles().add("FAX");
        this.phone.getRoles().add("PRIMARY");
    
        primaryFax = Boolean.TRUE;
        
        openDialogImpl();
    }
    
    protected void init(){
        primaryMobile = Boolean.FALSE;
        primaryPhone = Boolean.FALSE;
        primaryFax = Boolean.FALSE;
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
        //Save edilmemiş bir contact için sadece primaryler eklenebilir.
        if( primaryPhone ){
            contact.setPrimaryPhone(phone);
        } else if( primaryMobile ){
            contact.setPrimaryMobile(phone);
        } else if( primaryFax ){
            contact.setPrimaryFax(phone);
        }
        
        if( contact.isPersisted() ){
            //Önce bileşeni save edelim.
            contactInContactRepository.save(phone);
            
            //Demek ki yeni giriş değiş dolayısı ile verileri saklayalım
            contactRepository.save(contact);
        }
        
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
        String viewId = viewConfigResolver.getViewConfigDescriptor(ContactPages.PhoneEditor.class).getViewId();
        return viewId.substring(0, viewId.indexOf(".xhtml"));
    }

    public ContactPhone getPhone() {
        return phone;
    }

    public void setPhone(ContactPhone phone) {
        this.phone = phone;
    }

    public List<String> getAcceptedRoles(){
        return new ArrayList<>( Arrays.asList(new String[]{"PRIMARY", "BUSINESS", "HOME", "PERSONAL"}));
    }
    
}
