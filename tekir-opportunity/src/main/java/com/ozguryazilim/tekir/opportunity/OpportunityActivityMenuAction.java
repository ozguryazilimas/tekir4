package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.activity.ActivityHome;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.Person;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 *
 * @author oyas
 */
@Named
@RequestScoped
public class OpportunityActivityMenuAction implements Serializable{

    @Inject
    private ActivityHome activityHome;
    
    @Inject 
    private OpportunityHome opportunityHome;
    
    public Class<? extends ViewConfig> newOpportunity(){
        Class<? extends ViewConfig> result = opportunityHome.create();
        
        //TODO: Aslında burada Account olup olmadığına bir bakılması lazım. Bazen person'lar da account olabilir.
        if( activityHome.getEntity() instanceof Activity ){
            Activity act = (Activity) activityHome.getEntity();
            if( act.getPerson() != null && act.getPerson() instanceof Person ){
                opportunityHome.getEntity().setPrimaryContact((Person)act.getPerson());
            }
            
            if( act.getCorporation() != null ){
                opportunityHome.getEntity().setAccount(act.getCorporation());
            }
            
            opportunityHome.getEntity().setTopic(act.getSubject());
            opportunityHome.getEntity().setStarter(activityHome.getFeaturePointer());
        } else {
            return null;
        }
        
        return result;
    }
    
}
