/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.quote.config;

import com.ozguryazilim.tekir.core.config.SalesNavigationSection;
import com.ozguryazilim.telve.view.Pages;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import com.ozguryazilim.tekir.quote.config.QuotePages;
import org.apache.deltaspike.jsf.api.config.view.View;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.nav.Navigation;

/**
 *
 * @author oyas
 */
@ApplicationScoped
@Folder(name = "./quote")
public interface QuotePages extends Pages {

	@View
	@SecuredPage("quote")
	@PageTitle("module.caption.QuoteBrowse")
	@Navigation(label = "module.caption.QuoteBrowse", icon = "flaticon-speech-bubble", section = SalesNavigationSection.class)
	class QuoteBrowse implements QuotePages {
	}

	@View
	@SecuredPage("quote")
	@PageTitle("module.caption.Quote")
	class Quote implements com.ozguryazilim.tekir.quote.config.QuotePages {
	}

	@View
	@SecuredPage("quote")
	@PageTitle("module.caption.QuoteView")
	class QuoteView implements com.ozguryazilim.tekir.quote.config.QuotePages {
	}

	@View
	@SecuredPage("quote")
	@PageTitle("module.caption.QuoteMasterView")
	class QuoteMasterView
			implements
				com.ozguryazilim.tekir.quote.config.QuotePages {
	}

	@View @SecuredPage()
	class QuoteLookup implements com.ozguryazilim.tekir.quote.config.QuotePages {
	}

}
