package com.ozguryazilim.tekir.recruit.config;

import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
import com.ozguryazilim.tekir.recruit.jobadvert.JobAdvertFeature;
import com.ozguryazilim.tekir.recruit.applicant.ApplicantFeature;
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
    @PageTitle("module.caption.JobAdvertBrowse")
    @Navigation(label = "module.caption.RecruitBrowse",
            feature = JobAdvertFeature.class,
            section = RecruitNavigationSection.class)
    class JobAdvertBrowse implements RecruitPages {
    }

    @View
    @SecuredPage("recruit")
    @PageTitle("module.caption.JobAdvert")
    class JobAdvert implements RecruitPages {
    }

    @View
    @SecuredPage("recruit")
    @PageTitle("module.caption.JobAdvert")
    class JobAdvertView implements RecruitPages {
    }

    @View
    @SecuredPage("recruit")
    @PageTitle("module.caption.JobAdvertMasterView")
    class JobAdvertMasterView implements RecruitPages {
    }

    interface applicant extends RecruitPages {

        @View
        @SecuredPage("applicant")
        @PageTitle("module.caption.ApplicantBrowse")
        @Navigation(label = "module.caption.ApplicantBrowse",
                feature = ApplicantFeature.class,
                section = RecruitNavigationSection.class)
        class ApplicantBrowse implements RecruitPages {
        }

        @View
        @SecuredPage
        @PageTitle("module.caption.Applicant")
        class Applicant implements RecruitPages {
        }
    }
}
