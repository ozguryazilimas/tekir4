package com.ozguryazilim.tekir.recruit.config;

import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
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
    @PageTitle("module.caption.JobAdvert")
    class RecruitView implements RecruitPages {
    }

    @View
    @SecuredPage("recruit")
    @PageTitle("module.caption.JobAdvertMasterView")
    class RecruitMasterView implements RecruitPages {
    }

}
