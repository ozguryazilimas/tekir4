package com.ozguryazilim.tekir.recruit.config;

import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
import com.ozguryazilim.tekir.recruit.RecruitFeature;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

/**
 *
 * @author deniz
 */
@ApplicationScoped
@Folder(name = "./recruit")
public interface RecruitPages extends Pages {

    @View
    @SecuredPage("recruit")
    @PageTitle("module.caption.RecruitBrowse")
    @Navigation(label = "module.caption.RecruitBrowse",
            feature = RecruitFeature.class,
            section = RecruitNavigationSection.class)
    class RecruitBrowse implements RecruitPages {
    }

    @View
    @SecuredPage("recruit")
    @PageTitle("module.caption.Recruit")
    class Recruit implements RecruitPages {
    }

    @View
    @SecuredPage("recruit")
    @PageTitle("module.caption.JobAdvert")
    class RecruitView implements RecruitPages {
    }

    @View
    @SecuredPage("recruit")
    @PageTitle("module.caption.JobAdvertMasterView")
    class RecruitMasterView implements RecruitPages {
    }
    
    @View
    @SecuredPage("recruit")
    @PageTitle("module.caption.JobAdvert")
    class Recruit implements RecruitPages {
    }

}
