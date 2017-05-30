/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.dialogs;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.primefaces.context.RequestContext;

import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.view.DialogBase;

/**
 * Belge sahipliğini değiştirmek için kullanıcı seçim dialoğu.
 * 
 * @author Hakan Uygun
 */
@SessionScoped
@Named
public class ChangeOwnerDialog extends DialogBase implements Serializable{
    
    
    private String userName;
    
    @Override
    public void beforeOpenDialog(){
        userName = "";
    }
    
    @Override
    public void closeDialog(){
        RequestContext.getCurrentInstance().closeDialog(userName);
    }
    
    /**
     * Açılacak olan diolog özellikleri setlenir.
     * 
     * Alt sınıflar isterse bu methodu override ederk dialoğ özellikleirni değiştirebilirler.
     * 
     * @param options 
     */
    @Override
    protected void decorateDialog(Map<String, Object> options){
        options.put("modal", true);
        //options.put("draggable", false);  
        options.put("resizable", false);
        options.put("contentHeight", 450);
    }

    @Override
    public Class<? extends ViewConfig> getDialogViewConfig() {
    	return CorePages.Dialogs.ChangeOwnerDialog.class;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    
}
