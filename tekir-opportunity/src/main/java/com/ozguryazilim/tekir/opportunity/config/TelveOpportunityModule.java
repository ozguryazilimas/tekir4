/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity.config;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.api.module.TelveModule;
import com.ozguryazilim.telve.view.ContextMenuResolverRegistery;
import javax.annotation.PostConstruct;

/**
 *
 * @author Hakan Uygun
 */
@TelveModule
public class TelveOpportunityModule {


    @PostConstruct
    public void init(){
        
        ContextMenuResolverRegistery.registerMenu(ContactPages.ContactView.class, "/menu/contactOpportunity.xhtml");
        
    }
}
