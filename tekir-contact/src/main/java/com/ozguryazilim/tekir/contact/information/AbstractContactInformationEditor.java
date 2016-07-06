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
import org.primefaces.context.RequestContext;

/**
 * ContactInformationEditorler için taban sınıf
 * @author Hakan Uygun
 */
public abstract class AbstractContactInformationEditor<E extends ContactInformation> implements Serializable{

    
    private E entity;
    
    
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
    public abstract String getDialogName();
    
    
    public List<String> getAcceptedRoles(){
        return new ArrayList<>( Arrays.asList(new String[]{"PRIMARY", "BUSINESS", "HOME", "PERSONAL"}));
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }


    
    public abstract String getIcon(E information);
    
    public abstract String getEditorCaption();

    
    public abstract void create(Contact contact);

    public abstract void edit(E information);

    
    public abstract void delete(E information);

    
    public abstract boolean acceptType(ContactInformation information);
    
}
