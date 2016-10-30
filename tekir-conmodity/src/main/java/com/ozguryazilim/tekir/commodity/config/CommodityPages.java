/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.commodity.config;

import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.nav.SideNavigationSection;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;
import com.ozguryazilim.tekir.commodity.config.CommodityPages;
import com.ozguryazilim.tekir.core.config.ParamNavigationSection;

/**
 *
 * @author oyas
 */
@ApplicationScoped
@Folder(name = "./commodity")
public interface CommodityPages extends Pages {

	@View
	@SecuredPage("commodity")
	@PageTitle("module.caption.CommodityBrowse")
	@Navigation(label = "module.caption.CommodityBrowse", icon = "fa fa-cube", section = SideNavigationSection.class)
	class CommodityBrowse implements CommodityPages {
	}

	@View
	@SecuredPage("commodity")
	@PageTitle("module.caption.Commodity")
	class Commodity implements CommodityPages {
	}

	@View
	@SecuredPage("commodity")
	@PageTitle("module.caption.Commodity")
	class CommodityView implements CommodityPages {
	}

	@View
	@SecuredPage("commodity")
	@PageTitle("module.caption.CommodityMasterView")
	class CommodityMasterView implements CommodityPages {
	}

	@SecuredPage()
	@View
	class CommodityLookup implements CommodityPages {
	}

	@View
	@SecuredPage("commodityCategory")
	@PageTitle("module.caption.CommodityCategory")
	@Navigation(label = "module.caption.CommodityCategory", icon = "fa fa-sitemap", section = ParamNavigationSection.class)
	class CommodityCategory implements CommodityPages {
	}

	@View @SecuredPage()
	class CommodityCategoryLookup
			implements
				com.ozguryazilim.tekir.commodity.config.CommodityPages {
	}

}
