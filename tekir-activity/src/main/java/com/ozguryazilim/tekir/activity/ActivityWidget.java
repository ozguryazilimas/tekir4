/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

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

    private Person person;
    private Corporation corporation;
    private FeaturePointer featurePointer;
    private String attached = "Person"; //Person | Corporation | Feature
    private ActivityWidgetFilter filter = ActivityWidgetFilter.ALL;

    public void init(Person person) {

    }

    public void init(Corporation corporation) {

    }

    public void init(Person person, Corporation corporation, FeaturePointer featurePointer, String attached ) {
        this.person = person;
        this.corporation = corporation;
        this.featurePointer = featurePointer;
        this.attached = attached;
    }

    public List<Activity> getActivityList() {

        //TODO: Feature'e göre arama da eklenecek
        switch( attached ){
            case "Person" : return repository.findByPerson(person, filter); 
            case "ContactPerson" : return repository.findByPerson(person, filter); 
            case "Employee" : return repository.findByPerson(person, filter); 
            case "Corporation" : return repository.findByCorporation(corporation, filter); 
            case "Feature" : return repository.findByFeature(featurePointer, filter); 
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
    public void createNew(String activity) {
        //FIXME: NPE Kontrolü yapılacak
        AbstractActivityController ctrl = ActivityRegistery.getControllerInstance(activity);
        ctrl.createNew( person, corporation, featurePointer, false);
    }
    
    public void createNewFollowup(String activity) {
        //FIXME: NPE Kontrolü yapılacak
        AbstractActivityController ctrl = ActivityRegistery.getControllerInstance(activity);
        ctrl.createNew( person, corporation, featurePointer, true);
    }

    /**
     * Verilen activity için gerekli editorü bulup açar
     *
     * @param activity
     */
    public void edit(Activity activity) {
        //FIXME: NPE Kontrolü yapılacak
        AbstractActivityController ctrl = ActivityRegistery.getControllerInstance(activity);
        ctrl.edit(activity);
    }

    /**
     * Verilen activity'i siler.
     *
     * TODO: Yetki kontrolü? Kendininkileri siler, başkasının kileri siler,
     * sadece şu iş ile ilgili olanları siler?
     *
     * @param activity
     */
    @Transactional
    public void delete(Activity activity) {
        repository.remove(activity);
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


}
