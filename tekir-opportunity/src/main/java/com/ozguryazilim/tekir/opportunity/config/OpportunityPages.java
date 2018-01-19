/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity.config;

import com.ozguryazilim.tekir.core.config.SalesNavigationSection;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

/**
 * Opportunity Pages
 *
 * @author Hakan Uygun
 */
@ApplicationScoped
@Folder(name = "./opportunity")
public interface OpportunityPages extends Pages {

    @View
    @SecuredPage("opportunity")
    @PageTitle("module.caption.OpportunityBrowse")
    @Navigation(label = "module.caption.OpportunityBrowse", icon = "flaticon-light-bulb", section = SalesNavigationSection.class)
    class OpportunityBrowse implements OpportunityPages {
    }

    @View
    @SecuredPage("opportunity")
    @PageTitle("module.caption.Opportunity")
    class Opportunity implements OpportunityPages {
    }

    @View
    @SecuredPage("opportunity")
    @PageTitle("module.caption.Opportunity")
    class OpportunityView
            implements
            OpportunityPages {
    }

    @View
    @SecuredPage("opportunity")
    class OpportunityMasterView
            implements
            OpportunityPages {
    }
    
    @View 
    @SecuredPage()
    class OpportunityReminderCommand implements OpportunityPages {
    }
}
