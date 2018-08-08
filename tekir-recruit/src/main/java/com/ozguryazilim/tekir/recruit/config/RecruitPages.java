package com.ozguryazilim.tekir.recruit.config;

import com.ozguryazilim.tekir.recruit.jobadvert.JobAdvertFeature;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
import com.ozguryazilim.tekir.recruit.jobapplication.JobApplicationFeature;
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

    @Folder(name = "./jobAdvert")
    interface JobAdvertPages extends RecruitPages {

        @View
        @SecuredPage("recruit")
        @PageTitle("module.caption.JobAdvertBrowse")
        @Navigation(label = "module.caption.RecruitBrowse",
                feature = JobAdvertFeature.class,
                section = RecruitNavigationSection.class)
        class JobAdvertBrowse implements JobAdvertPages {
        }

        @View
        @SecuredPage("recruit")
        @PageTitle("module.caption.JobAdvert")
        class JobAdvert implements JobAdvertPages {
        }

        @View
        @SecuredPage("recruit")
        @PageTitle("module.caption.JobAdvertMasterView")
        class JobAdvertMasterView implements JobAdvertPages {
        }

        @View
        @SecuredPage("recruit")
        @PageTitle("module.caption.JobAdvert")
        class JobAdvertView implements JobAdvertPages {
        }
    }

    @View
    @SecuredPage
    class JobAdvertLookup implements RecruitPages {
    }

    interface applicant extends RecruitPages {

        @View
        @SecuredPage
        class ApplicantLookup implements RecruitPages {
        }

    }

    interface jobapplication extends RecruitPages {

        @View
        @SecuredPage("application")
        @PageTitle("module.caption.JobApplicationBrowse")
        @Navigation(label = "module.caption.JobApplicationBrowse",
                feature = JobApplicationFeature.class,
                section = RecruitNavigationSection.class)
        class JobApplicationBrowse implements RecruitPages {
        }

        @View
        @SecuredPage
        @PageTitle("module.caption.JobApplication")
        class JobApplication implements RecruitPages {
        }

        @View
        @SecuredPage
        @PageTitle("module.caption.JobApplicationView")
        class JobApplicationView implements RecruitPages {
        }

        @View
        @SecuredPage
        @PageTitle("module.caption.JobApplicationMasterView")
        class JobApplicationMasterView implements RecruitPages {
        }
    }

}
