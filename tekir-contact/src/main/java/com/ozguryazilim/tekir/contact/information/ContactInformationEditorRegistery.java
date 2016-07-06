/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.entities.ContactInformation;
import java.util.HashMap;
import java.util.Map;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oyas
 */
public class ContactInformationEditorRegistery {

    private static final Logger LOG = LoggerFactory.getLogger(ContactInformationEditorRegistery.class);
    
    private static Map<Class<? extends ContactInformation>, String> editors = new HashMap<>();
    
    /**
     * Sisteme yeni bir ContactInformationEditor ekler.
     *
     * @param name komut sınıfının EL adı
     * @param a ContactInformationEditor annotation
     */
    public static void register(String name, ContactInformationEditor a) {
        editors.put(a.handle(), name);
        LOG.info("Registered ContactInformationEditor : {}=>{}", new Object[]{name, a.handle()});
    }
    
    
    public static String getEditorName( Class<? extends ContactInformation> clazz ){
        return editors.get(clazz);
    }
    
    
    public static AbstractContactInformationEditor getEditor( Class<? extends ContactInformation> clazz ){
        return (AbstractContactInformationEditor) BeanProvider.getContextualReference(getEditorName(clazz), true);
    }
}

