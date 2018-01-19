/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.config;

import com.ozguryazilim.tekir.contact.ContactFeature;
import com.ozguryazilim.tekir.core.config.ParamNavigationSection;
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
	@Navigation(label = "module.caption.CorporationType", icon = "fa fa-building-o", section = ParamNavigationSection.class)
	class CorporationType implements ContactPages {
	}

	@SecuredPage() @View
	class CorporationTypeLookup
			implements ContactPages {
	}

	@View
	@SecuredPage("contactRelation")
	@PageTitle("module.caption.ContactRelation")
	@Navigation(label = "module.caption.ContactRelation", icon = "fa fa-crosshairs", section = ParamNavigationSection.class)
	class ContactRelation
			implements
				 ContactPages {
	}

	@SecuredPage() @View
	class ContactRelationLookup
			implements
				ContactPages {
	}

	@View
	@SecuredPage("contactCategory")
	@PageTitle("module.caption.ContactCategory")
	@Navigation(label = "module.caption.ContactCategory", icon = "fa fa-sitemap", section = ParamNavigationSection.class)
	class ContactCategory implements ContactPages {
	}

	@SecuredPage() @View
	class ContactCategoryLookup
			implements
				ContactPages {
	}

	@View
	@SecuredPage("contact")
	@PageTitle("module.caption.ContactBrowse")
	@Navigation(label = "module.caption.ContactBrowse", feature = ContactFeature.class, section = SideNavigationSection.class)
	class ContactBrowse implements ContactPages {
	}

	@View
	@SecuredPage("contact")
	@PageTitle("module.caption.Contact")
	class Contact implements ContactPages {
	}

	@View
	@SecuredPage("contact")
	@PageTitle("module.caption.Contact")
	class ContactView
			implements
				ContactPages {
	}

	@View
	@SecuredPage("contact")
	@PageTitle("module.caption.ContactMasterView")
	class ContactMasterView
			implements
				ContactPages {
	}

	@SecuredPage() @View
	class ContactLookup
			implements
				ContactPages {
	}
	
 	@SecuredPage
 	@View
 	@PageTitle("report.name.accountTxnReport")
 	class AccountTxnReport implements ContactPages {
	}

        @SecuredPage("relatedContact") @View 
        class RelatedContactSubView implements Admin {};
        
        @SecuredPage("accountTxn") @View 
        class AccountTxnSubView implements Admin {};
        
        @SecuredPage("document") @View 
        class DocumentSubView implements Admin {};
        
        @SecuredPage() @View
	class NewPersonPopup
			implements
				ContactPages {
	}
        
        @SecuredPage() @View
	class NewCorporationPopup
			implements
				ContactPages {
	}
        
        @SecuredPage() @View
	class PhoneEditor
			implements
				ContactPages {
	}
        
        @SecuredPage() @View
	class BankEditor
			implements
				ContactPages {
	}
        
        @SecuredPage() @View
	class EmailEditor
			implements
				ContactPages {
	}
        
        @SecuredPage() @View
	class AddressEditor
			implements
				ContactPages {
	}
        
        @SecuredPage() @View
	class SocialAccountEditor
			implements
				ContactPages {
	}
        
	@SecuredPage
	@View
	@PageTitle("report.name.accountStatusReport")
	class AccountStatusReport implements ContactPages {
	}
        
        @SecuredPage() @View	
        class QuickPersonPanel implements ContactPages {	}
        
        @SecuredPage() @View	
        class QuickCorporationPanel implements ContactPages {}
        
        @SecuredPage() @View	
        class QuickContactSetPanel implements ContactPages {}

}
