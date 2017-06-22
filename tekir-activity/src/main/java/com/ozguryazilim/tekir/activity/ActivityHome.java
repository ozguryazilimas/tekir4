/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;

/**
 *
 * @author oyas
 */
@FormEdit(feature = ActivityFeature.class)
public class ActivityHome extends FormBase<Activity, Long>{

    @Inject
    private ViewConfigResolver viewConfigResolver;
    
    @Inject
    private ActivityRepository repository;
    
    @Override
    protected RepositoryBase<Activity, ?> getRepository() {
        return repository;
    }
    
    public String getViewFragment(){
        ActivityController ac = ActivityRegistery.getMetaData(getEntity());
        if( ac != null ){
            return viewConfigResolver.getViewConfigDescriptor(ac.viewer()).getViewId();
        }
        
        return "";
    }
    
    public FeaturePointer getFeaturePointer() {
        FeaturePointer result = new FeaturePointer();
        result.setBusinessKey(getEntity().getSubject());
        result.setFeature(getFeatureClass().getSimpleName());
        result.setPrimaryKey(getEntity().getId());
        return result;
    }
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase contact){
    		return FeatureUtils.getFeaturePointer(contact);
    }
}
