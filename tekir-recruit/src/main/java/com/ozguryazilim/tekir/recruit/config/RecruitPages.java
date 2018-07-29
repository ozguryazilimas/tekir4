/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.recruit.config;

import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
import com.ozguryazilim.tekir.recruit.RecruitFeature;
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
        @PageTitle("module.caption.RecruitView")
        class RecruitView implements RecruitPages {
        }

        @View
        @SecuredPage("recruit")
        @PageTitle("module.caption.RecruitMasterView")
        class RecruitMasterView implements RecruitPages {
        }

    interface applicant extends RecruitPages{
        
        @View
        @SecuredPage("applicant")
        @PageTitle("module.caption.ApplicantBrowse")
        @Navigation(label = "module.caption.ApplicantBrowse",
                feature = ApplicantFeature.class,
                section = RecruitNavigationSection.class)
        class ApplicantBrowse implements RecruitPages{
        }

        @View
        @SecuredPage
        @PageTitle("module.caption.Applicant")
        class Applicant implements RecruitPages{
        }
    }

}
