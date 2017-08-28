/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.entities.AbstractPerson;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;

/**
 * Temel Activity Listesini yöneten controller.
 *
 * @author Hakan Uygun
 */
@Named
@GroupedConversationScoped
public class ActivityWidget implements Serializable {

    @Inject
    private ActivityRepository repository;

    @Inject
    private ViewConfigResolver viewConfigResolver;

    private AbstractPerson person;
    private Corporation corporation;
    private FeaturePointer featurePointer;
    private String attached = "Person"; //Person | Corporation | Feature
    private ActivityWidgetFilter filter = ActivityWidgetFilter.ALL;

    public void init(Person person) {

    }

    public void init(Corporation corporation) {

    }

    public void init(AbstractPerson person, Corporation corporation, FeaturePointer featurePointer, String attached ) {
        this.person = person;
        this.corporation = corporation;
        this.featurePointer = featurePointer;
        this.attached = attached;
    }

    public List<Activity> getActivityList() {

        
        FeaturePointer fp;
        switch( attached ){
            case "Person" : 
                fp = FeatureUtils.getFeaturePointer(person);
                return repository.findByMention(fp, filter); 
            case "ContactPerson" : 
                fp = FeatureUtils.getFeaturePointer(person);
                return repository.findByMention(fp, filter); 
            case "Employee" : 
                fp = FeatureUtils.getFeaturePointer(person);
                return repository.findByMention(fp, filter); 
            case "Corporation" : 
                fp = FeatureUtils.getFeaturePointer(corporation);
                return repository.findByMention(fp, filter); 
            case "Feature" : 
                return repository.findByMention(featurePointer, filter); 
            default: return Collections.emptyList();
        }
    }

    /**
     * Geriye eklenebilecek aktivity tiplerinin listesini döndürür.
     *
     * Bu bilgi ile UI üzerinde yeni düğmeleri oluştutulur.
     *
     * TODO: Yetki mekanizmasına ihtiyaç var mı?
     *
     * @return
     */
    public List<String> getAddableActivities() {
        return ActivityRegistery.getEditableActivities();
    }

    /**
     * Verilen activity sınıf adını kullanarak gerekli yeni editorünü açar
     *
     * @param activity
     */
    public Class<? extends ViewConfig> createNew(String activity) {
        //FIXME: NPE Kontrolü yapılacak
        AbstractActivityController ctrl = ActivityRegistery.getControllerInstance(activity);
        return ctrl.createActivity( person, corporation, featurePointer, false);
    }
    
    /**
     * UI üzerinde activity sunumu için kullanılack olan fragment view id'sini
     * döndürür.
     *
     * @param activity
     * @return
     */
    public String getViewFragmentID(Activity activity) {
        //FIXME: BPE kontrol edilip en azından empty birşey döndürmek lazım 
        ActivityController meta = ActivityRegistery.getMetaData(activity);
        return viewConfigResolver.getViewConfigDescriptor(meta.viewer()).getViewId();

    }

    public ActivityWidgetFilter getFilter() {
        return filter;
    }

    public void setFilter(ActivityWidgetFilter filter) {
        this.filter = filter;
    }

    public String getAttached() {
        return attached;
    }

    public void setAttached(String attached) {
        this.attached = attached;
    }

}
