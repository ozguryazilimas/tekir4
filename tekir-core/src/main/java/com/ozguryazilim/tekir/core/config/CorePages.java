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
	@Navigation(label = "module.caption.Location", icon = "fa fa-map-marker", section = SideNavigationSection.class)
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
	@Navigation(label = "module.caption.Industry", icon = "fa fa-building", section = SideNavigationSection.class)
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
	@Navigation(label = "module.caption.Territory", icon = "fa fa-arrows", section = SideNavigationSection.class)
	class Territory implements com.ozguryazilim.tekir.core.config.CorePages {
	}

	@View
	class TerritoryLookup
			implements
				com.ozguryazilim.tekir.core.config.CorePages {
	}

	@View
	@SecuredPage("commodity")
	@PageTitle("module.caption.CommodityBrowse")
	@Navigation(label = "module.caption.CommodityBrowse", icon = "fa fa-cube", section = SideNavigationSection.class)
	class CommodityBrowse
			implements
				com.ozguryazilim.tekir.core.config.CorePages {
	}

	@View
	@SecuredPage("commodity")
	@PageTitle("module.caption.Commodity")
	class Commodity implements com.ozguryazilim.tekir.core.config.CorePages {
	}

	@View
	@SecuredPage("commodity")
	@PageTitle("module.caption.CommodityView")
	class CommodityView implements com.ozguryazilim.tekir.core.config.CorePages {
	}

	@View
	@SecuredPage("commodity")
	@PageTitle("module.caption.CommodityMasterView")
	class CommodityMasterView
			implements
				com.ozguryazilim.tekir.core.config.CorePages {
	}

	@View
	class CommodityLookup
			implements
				com.ozguryazilim.tekir.core.config.CorePages {
	}

}
