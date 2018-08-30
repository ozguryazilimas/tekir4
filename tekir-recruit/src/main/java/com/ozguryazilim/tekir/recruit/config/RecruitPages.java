package com.ozguryazilim.tekir.recruit.config;

import com.ozguryazilim.tekir.recruit.applicant.ApplicantFeature;
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
        @SecuredPage("JobAdvert")
        @PageTitle("module.caption.JobAdvertBrowse")
        @Navigation(label = "module.caption.JobAdvertBrowse",
                feature = JobAdvertFeature.class,
                section = RecruitNavigationSection.class)
        class JobAdvertBrowse implements JobAdvertPages {
        }

        @View
        @SecuredPage("JobAdvert")
        @PageTitle("module.caption.JobAdvert")
        class JobAdvert implements JobAdvertPages {
        }
        
        @View
        @SecuredPage("JobAdvert")
        @PageTitle("module.caption.JobAdvert")
        class JobAdvertView implements JobAdvertPages {
        }
        
        @View
        @SecuredPage("JobAdvert")
        @PageTitle("module.caption.JobAdvertMasterView")
        class JobAdvertMasterView implements JobAdvertPages {
        }

        @View
        @SecuredPage
        class JobAdvertLookup implements RecruitPages {
        }
        
    }

    @Folder(name = "./applicant")
    interface ApplicantPages extends RecruitPages{

        @View
        @SecuredPage("applicant")
        @PageTitle("module.caption.ApplicantBrowse")
        @Navigation(label = "module.caption.ApplicantBrowse",
                feature = ApplicantFeature.class,
                section = RecruitNavigationSection.class)
        class ApplicantBrowse implements ApplicantPages {
        }

        @View
        @SecuredPage("applicant")
        @PageTitle("module.caption.Applicant")
        class Applicant implements ApplicantPages {
        }
        
        @View
        @SecuredPage
        class ApplicantLookup implements ApplicantPages {
        }
    }
    
    @Folder(name = "./jobApplication")
    interface JobApplicationPages extends RecruitPages {

        @View
        @SecuredPage("jobApplication")
        @PageTitle("module.caption.JobApplicationBrowse")
        @Navigation(label = "module.caption.JobApplicationBrowse",
                feature = JobApplicationFeature.class,
                section = RecruitNavigationSection.class)
        class JobApplicationBrowse implements JobApplicationPages {
        }

        @View
        @SecuredPage("jobApplication")
        @PageTitle("module.caption.JobApplication")
        class JobApplication implements JobApplicationPages {
        }

        @View
        @SecuredPage("jobApplication")
        @PageTitle("module.caption.JobApplication")
        class JobApplicationView implements JobApplicationPages {
        }

        @View
        @SecuredPage("jobApplication")
        @PageTitle("module.caption.JobApplicationMasterView")
        class JobApplicationMasterView implements JobApplicationPages {
        }
        
    }
    
}
