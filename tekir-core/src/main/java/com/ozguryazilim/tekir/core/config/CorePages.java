/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.config;

import com.ozguryazilim.telve.view.Pages;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import com.ozguryazilim.telve.nav.SideNavigationSection;
import com.ozguryazilim.tekir.core.config.CorePages;
import org.apache.deltaspike.jsf.api.config.view.View;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.nav.Navigation;

/**
 *
 * @author oyas
 */
@ApplicationScoped
@Folder(name = "./core")
public interface CorePages extends Pages {

	@View
	@SecuredPage("location")
	@PageTitle("module.caption.Location")
	@Navigation(label = "module.caption.Location", icon = "/todo.png", section = SideNavigationSection.class)
	class Location implements CorePages {
	}

	@View
	class LocationLookup
			implements
				com.ozguryazilim.tekir.core.config.CorePages {
	}

	@View
	@SecuredPage("industry")
	@PageTitle("module.caption.Industry")
	@Navigation(label = "module.caption.Industry", icon = "/todo.png", section = SideNavigationSection.class)
	class Industry implements com.ozguryazilim.tekir.core.config.CorePages {
	}

	@View
	class IndustryLookup
			implements
				com.ozguryazilim.tekir.core.config.CorePages {
	}

	@View
	@SecuredPage("territory")
	@PageTitle("module.caption.Territory")
	@Navigation(label = "module.caption.Territory", icon = "/todo.png", section = SideNavigationSection.class)
	class Territory implements com.ozguryazilim.tekir.core.config.CorePages {
	}

	@View
	class TerritoryLookup
			implements
				com.ozguryazilim.tekir.core.config.CorePages {
	}

}
