package com.ozguryazilim.tekir.opportunity.config;

import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.api.module.TelveModule;
import com.ozguryazilim.telve.view.ContextMenuResolverRegistery;
import javax.annotation.PostConstruct;

/**
 *
 * @author Hakan Uygun
 */
@TelveModule
public class TekirOpportunityModule {


    @PostConstruct
    public void init(){
        
        ContextMenuResolverRegistery.registerMenu(ContactPages.ContactView.class, "/menu/contactOpportunity.xhtml");
        ContextMenuResolverRegistery.registerMenu(ActivityPages.ActivityView.class, "/menu/activityOpportunity.xhtml");
        
    }
}
