package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.contact.ContactHome;
import com.ozguryazilim.tekir.entities.Corporation;
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
public class OpportunityContactMenuAction implements Serializable{

    @Inject
    private ContactHome contactHome;
    
    @Inject 
    private OpportunityHome opportunityHome;
    
    public Class<? extends ViewConfig> newOpportunity(){
        Class<? extends ViewConfig> result = opportunityHome.create();
        
        //TODO: Aslında burada Account olup olmadığına bir bakılması lazım. Bazen person'lar da account olabilir.
        if( contactHome.getEntity() instanceof Person ){
            Person p = (Person) contactHome.getEntity();
            opportunityHome.getEntity().setPrimaryContact(p);
            opportunityHome.getEntity().setAccount(p.getCorporation());
        } else if( contactHome.getEntity() instanceof Corporation ){
            Corporation c = (Corporation) contactHome.getEntity();
            opportunityHome.getEntity().setPrimaryContact(c.getPrimaryContact());
            opportunityHome.getEntity().setAccount(c);
        }
        
        return result;
    }
    
}
