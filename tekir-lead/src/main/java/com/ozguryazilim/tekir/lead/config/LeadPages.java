package com.ozguryazilim.tekir.lead.config;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

import com.ozguryazilim.tekir.core.config.SalesNavigationSection;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;

@ApplicationScoped
@Folder(name = "./lead")
public interface LeadPages extends Pages {

	@View
	@SecuredPage("lead")
	@PageTitle("module.caption.LeadBrowse")
	@Navigation(label = "module.caption.LeadBrowse", icon = "fa fa-puzzle-piece", section = SalesNavigationSection.class)
	class LeadBrowse implements LeadPages {
	}

}
