package com.ozguryazilim.tekir.feed.config;

import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.nav.SideNavigationSection;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

/**
 *
 * @author oyas
 */
@ApplicationScoped
@Folder(name = "./feeds")
public interface FeedPages extends Pages {

  
    @View
    @SecuredPage("feeds")
    @PageTitle("module.caption.FeedsBrowse")
    @Navigation(label = "module.caption.FeedsBrowse", icon = "fa fa-tasks", section = SideNavigationSection.class)
    class FeedBrowse implements FeedPages {
    }

    @View
    @SecuredPage("feeds")
    @PageTitle("module.caption.Feeds")
    class FeedClearCommand implements FeedPages {
        
    }

 
}
