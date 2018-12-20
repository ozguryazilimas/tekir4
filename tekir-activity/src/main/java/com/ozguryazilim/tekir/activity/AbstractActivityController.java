package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.tekir.entities.AbstractPerson;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.sequence.SequenceManager;
import java.io.Serializable;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.deltaspike.core.api.config.view.navigation.NavigationParameterContext;
import org.apache.deltaspike.core.util.ProxyUtils;

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
    private NavigationParameterContext navigationParameterContext;
    
    @Inject
    private Identity identity;
    
    @Inject
    private ActivityRepository repository;
    
    @Inject
    private ActivityFeeder feeder;
    
    @Inject
    private ActivityWidget activityWidget;
    
    @Inject
    private ActivityHome activityHome;
    
    @Inject
    private SequenceManager sequenceManager;
    
    private E entity;
    
    protected abstract RepositoryBase<E, E> getRepository();

    protected abstract E createNewEntity();
    
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

    public Class<? extends ViewConfig> createActivity( AbstractPerson person, Corporation corporation, FeaturePointer featurePointer, Boolean followUp ){
        entity = createNewEntity();
        entity.setAssignee(identity.getLoginName());
        entity.setPerson(person);
        entity.setCorporation(corporation);
        entity.setRegarding(featurePointer);
        entity.setDueDate(new Date());
        entity.setActivityNo(sequenceManager.getNewSerialNumber("ACT", 6));
        activityHome.setEntity(entity);
        activityHome.setReturnPage(viewConfigResolver.getViewConfigDescriptor(FacesContext.getCurrentInstance().getViewRoot().getViewId()).getConfigClass());
        
        navigationParameterContext.addPageParameter("eid", 0);

        return ActivityPages.Activity.class;
    }
    
}
