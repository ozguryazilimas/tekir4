/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactInformation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.primefaces.context.RequestContext;

/**
 * ContactInformationEditorler için taban sınıf
 * @author Hakan Uygun
 */
public abstract class AbstractContactInformationEditor<E extends ContactInformation> implements Serializable{

    @Inject
    private ViewConfigResolver viewConfigResolver;

    @Inject
    private ContactInformationRepository contactInformationRepository;
    
    private E entity;

    private Contact contact;
    
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
     * Dialogu OK ile kapatıyoruz.
     */
    public void closeDialog() {
        if( !onBeforeClose() ) return;
        
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    protected boolean onBeforeClose(){
        return true;
    }

    /**
     * Bir şey yapmadan çık.
     */
    public void cancelDialog() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    /**
     * Açılacak dialog adını döndürür.
     *
     * @return
     */
    public String getDialogName(){
        Class<? extends ViewConfig> page = this.getClass().getAnnotation(ContactInformationEditor.class).page();
        String viewId = viewConfigResolver.getViewConfigDescriptor(page).getViewId();
        return viewId.substring(0, viewId.indexOf(".xhtml"));
    }
    
    
    public List<String> getAcceptedRoles(){
        return new ArrayList<>( Arrays.asList(new String[]{"PRIMARY", "BUSINESS", "HOME", "PERSONAL"}));
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

        openDialogImpl();
    }

    public void edit(E information){
        init();

        this.contact = information.getContact();
        setEntity(information );

        openDialogImpl();
    }

    
    public void delete(E information){
        contactInformationRepository.remove(information);
    }

    
    public abstract boolean acceptType(ContactInformation information);
    
}
