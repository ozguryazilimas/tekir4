/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.config;

import com.ozguryazilim.telve.view.Pages;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import com.ozguryazilim.telve.nav.SideNavigationSection;
import org.apache.deltaspike.jsf.api.config.view.View;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.nav.Navigation;

/**
 * Contact Pages
 * 
 * @author Hakan Uygun
 */
@ApplicationScoped
@Folder(name = "./contact")
public interface ContactPages extends Pages {

	@View
	@SecuredPage("corporationType")
	@PageTitle("module.caption.CorporationType")
	@Navigation(label = "module.caption.CorporationType", icon = "fa fa-building-o", section = SideNavigationSection.class)
	class CorporationType implements ContactPages {
	}

	@View
	class CorporationTypeLookup
			implements
				com.ozguryazilim.tekir.contact.config.ContactPages {
	}

	@View
	@SecuredPage("contactRelation")
	@PageTitle("module.caption.ContactRelation")
	@Navigation(label = "module.caption.ContactRelation", icon = "fa fa-crosshairs", section = SideNavigationSection.class)
	class ContactRelation
			implements
				com.ozguryazilim.tekir.contact.config.ContactPages {
	}

	@View
	class ContactRelationLookup
			implements
				com.ozguryazilim.tekir.contact.config.ContactPages {
	}

	@View
	@SecuredPage("contactCategory")
	@PageTitle("module.caption.ContactCategory")
	@Navigation(label = "module.caption.ContactCategory", icon = "fa fa-sitemap", section = SideNavigationSection.class)
	class ContactCategory implements ContactPages {
	}

	@View
	class ContactCategoryLookup
			implements
				com.ozguryazilim.tekir.contact.config.ContactPages {
	}

}
