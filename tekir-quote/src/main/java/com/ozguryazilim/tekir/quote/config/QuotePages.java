package com.ozguryazilim.tekir.quote.config;

import com.ozguryazilim.tekir.core.config.SalesNavigationSection;
import com.ozguryazilim.telve.view.Pages;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.nav.Navigation;

/**
 * @author oyas
 */
@ApplicationScoped
@Folder(name = "./quote")
public interface QuotePages extends Pages {

    @View
    @SecuredPage("quote")
    @PageTitle("module.caption.QuoteBrowse")
    @Navigation(label = "module.caption.QuoteBrowse", 
            icon = "flaticon-speech-bubble", 
            section = SalesNavigationSection.class)
    class QuoteBrowse implements QuotePages {
    }

    @View
    @SecuredPage("quote")
    @PageTitle("module.caption.Quote")
    class Quote implements QuotePages {
    }

    @View
    @SecuredPage("quote")
    @PageTitle("module.caption.QuoteView")
    class QuoteView implements QuotePages {
    }

    @View
    @SecuredPage("quote")
    @PageTitle("module.caption.QuoteMasterView")
    class QuoteMasterView implements QuotePages {
    }

    @View
    @SecuredPage
    class QuoteLookup implements QuotePages {
    }

    @View
    @SecuredPage
    class QuoteReminderCommand implements QuotePages {
    }

}
