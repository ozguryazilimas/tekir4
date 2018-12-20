package com.ozguryazilim.tekir.core.dialogs;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 * Belge sahipliğini değiştirmek için kullanıcı seçim dialoğu.
 * 
 * @author Hakan Uygun
 */
@SessionScoped
@Named
public class ChangeOwnerDialog implements Serializable{
    
    
    private String userName;
    
    public void openDialog(){
        userName = "";
        
        Map<String, Object> options = new HashMap<>();
        
        decorateDialog(options);
        
        RequestContext.getCurrentInstance().openDialog(getDialogName(), options, null);
    }
    
    public void closeDialog(){
        RequestContext.getCurrentInstance().closeDialog(userName);
    }
    
    public void cancelDialog(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    /**
     * Açılacak olan diolog özellikleri setlenir.
     * 
     * Alt sınıflar isterse bu methodu override ederk dialoğ özellikleirni değiştirebilirler.
     * 
     * @param options 
     */
    protected void decorateDialog(Map<String, Object> options){
        options.put("modal", true);
        //options.put("draggable", false);  
        options.put("resizable", false);
        options.put("contentHeight", 450);
    }

    public String getDialogName() {
        return "/dialogs/changeOwnerDialog";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    
}
