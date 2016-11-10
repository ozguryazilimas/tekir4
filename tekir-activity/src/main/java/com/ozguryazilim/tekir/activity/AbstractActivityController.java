/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.ActivityStatus;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.deltaspike.core.util.ProxyUtils;
import org.primefaces.context.RequestContext;

/**
 * Activity Controller için taban sınıf.
 * 
 * 
 * @author Hakan Uygun
 */
public abstract class AbstractActivityController<E extends Activity> implements Serializable{
    
    
    @Inject
    private ViewConfigResolver viewConfigResolver;
    
    @Inject
    private Identity identity;
    
    @Inject
    private ActivityRepository repository;
    
    @Inject
    private ActivityFeeder feeder;
    
    private E entity;
    
    protected abstract RepositoryBase<E, E> getRepository();

    protected abstract E createNewEntity();
    
    public void createNew(){
        entity = createNewEntity();
        entity.setAssignee(identity.getLoginName());
        openDialog();
    }
    
    public void createNew( Person person, Corporation corporation, FeaturePointer featurePointer ){
        entity = createNewEntity();
        entity.setAssignee(identity.getLoginName());
        entity.setPerson(person);
        entity.setCorporation(corporation);
        entity.setRegarding(featurePointer);
        openDialog();
    }
    
    
    public void edit( E entity ){
        this.entity = entity;
        openDialog();
    }

    
    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }
    
    
    public void openDialog(){
        Map<String, Object> options = new HashMap<>();
        
        decorateDialog(options);
        
        RequestContext.getCurrentInstance().openDialog(getDialogName(), options, null);
    }

    
    public void save() {
        entity.setStatus(ActivityStatus.SCHEDULED);
        repository.save(entity);
        feeder.feed(entity);        
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void success() {
        entity.setStatus(ActivityStatus.SUCCESS);
        repository.save(entity);
        feeder.feed(entity);
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void faild() {
        entity.setStatus(ActivityStatus.FAILED);
        repository.save(entity);
        feeder.feed(entity);
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void cancel() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void followup() {
        entity.setStatus(ActivityStatus.SUCCESS);
        repository.save(entity);
        feeder.feed(entity);
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
        String viewId = getEditorPageViewId();
        return viewId.substring(0, viewId.indexOf(".xhtml"));
    }
    
    public String getEditorPageViewId() {
        return viewConfigResolver.getViewConfigDescriptor(getEditorPage()).getViewId();
    }
    
    /**
     * Geriye ActivityController annotation'ı ile tanımlanmış EditPage'i döndürür.
     *
     * @return
     */
    public Class<? extends ViewConfig> getEditorPage() {
        return ((ActivityController)(ProxyUtils.getUnproxiedClass(this.getClass()).getAnnotation(ActivityController.class))).editor();
    }

    /**
     * Geriye ActivityController annotation'ı ile tanımlanmış ViewPage'i döndürür.
     *
     * @return
     */
    public Class<? extends ViewConfig> getViewerPage() {
        return ((ActivityController)(ProxyUtils.getUnproxiedClass(this.getClass()).getAnnotation(ActivityController.class))).viewer();
    }
}
