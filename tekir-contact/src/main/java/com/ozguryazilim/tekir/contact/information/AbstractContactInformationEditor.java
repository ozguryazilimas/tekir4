/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactInformation;
import com.ozguryazilim.telve.view.DialogBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.primefaces.context.RequestContext;

/**
 * ContactInformationEditorler için taban sınıf
 * @author Hakan Uygun
 */
public abstract class AbstractContactInformationEditor<E extends ContactInformation> extends DialogBase implements Serializable{

    @Inject
    private ContactInformationRepository contactInformationRepository;
    
    private List<String> acceptedRoles = new ArrayList<>( Arrays.asList(new String[]{ContactInformationConsts.Roles.PRIMARY, 
                        ContactInformationConsts.Roles.BUSINESS, ContactInformationConsts.Roles.HOME, ContactInformationConsts.Roles.PERSONAL}));
    
    private E entity;

    private Contact contact;
    
    @Override
    protected void decorateDialog(Map<String, Object> options) {
    	options.put("modal", true);
        //options.put("draggable", false);  
        options.put("resizable", false);
        options.put("width", "780");
        options.put("height", "430");
        //options.put("contentWidth", 780);
        options.put("contentHeight", 450);
        options.put("position", "center top");
    }
    
    /**
     * Dialogu OK ile kapatıyoruz.
     */
    @Override
    public void closeDialog() {
        if( !onBeforeClose() ) return;
        
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    protected boolean onBeforeClose(){
        return true;
    }
    
    @Override
    public Class<? extends ViewConfig> getDialogViewConfig() {
    	return this.getClass().getAnnotation(ContactInformationEditor.class).page();
    }
    
    
    public List<String> getAcceptedRoles(){
        return acceptedRoles;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


    protected abstract void init();
    
    public abstract String getIcon(E information);
    
    public abstract String getEditorCaption();

    
    public abstract E createNewModel();
    
    public void create(Contact contact){
        init();

        this.contact = contact;
        setEntity( createNewModel());
        getEntity().setContact(contact);

        openDialog();
    }

    public void edit(E information){
        init();

        this.contact = information.getContact();
        setEntity(information );

        openDialog();
    }

    
    public void delete(E information){
        contactInformationRepository.remove(information);
    }

    
    public abstract boolean acceptType(ContactInformation information);
    
}
