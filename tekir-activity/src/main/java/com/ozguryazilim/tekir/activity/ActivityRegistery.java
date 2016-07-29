/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.entities.Activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sistemde tanımlı aktivity türlerinin listesini tutar.
 * 
 * Anahtar olarak Activity sınıfının adı kullanılır. Bunun anlamı bir veri modeli için bir adet controller olacağıdır.
 * 
 * @author Hakan Uygun
 */
public class ActivityRegistery {
    
    private static final Logger LOG = LoggerFactory.getLogger(ActivityRegistery.class);
    
    private static Map<String,ActivityController> activities = new HashMap<>();
    private static Map<String,Class<? extends AbstractActivityController>> activityControllers = new HashMap<>();
    private static List<String> editableActivities = new ArrayList<>();
    
    public static void register( ActivityController activityController, Class<? extends AbstractActivityController> conrollerClass ){
        activities.put(activityController.activity().getSimpleName(), activityController);
        activityControllers.put(activityController.activity().getSimpleName(), conrollerClass);
        if( !activityController.editor().equals(ViewConfig.class)){
            editableActivities.add(activityController.activity().getSimpleName());
        }
        
        LOG.info("Activity {} registered", activityController.activity().getSimpleName());
    }


    /**
     * Geriye verilen activity için controller CDI instance döndürür.
     * @param act
     * @return 
     */
    public static AbstractActivityController getControllerInstance( Activity act ){
        //FIXME: burada NPE kontrolleri lazım.
        Class<? extends AbstractActivityController> cls = activityControllers.get(act.getClass().getSimpleName());
        return BeanProvider.getContextualReference(cls, true);
    }
    
    
    /**
     * Geriye verilen activity için controller CDI instance döndürür.
     * @param act
     * @return 
     */
    public static AbstractActivityController getControllerInstance( String act ){
        //FIXME: burada NPE kontrolleri lazım.
        Class<? extends AbstractActivityController> cls = activityControllers.get(act);
        return BeanProvider.getContextualReference(cls, true);
    }
    

    /**
     * Geriye verilen activity için metadata annotation döner
     * @param act
     * @return 
     */
    public static ActivityController getMetaData( Activity act ){
        //FIXME: burada NPE kontrolleri lazım.
        return activities.get(act.getClass().getSimpleName());
    }

    /**
     * Geriye editorü olan Activity türlerinin listesi döner
     * @return 
     */
    public static List<String> getEditableActivities() {
        return editableActivities;
    }
    
    
}
