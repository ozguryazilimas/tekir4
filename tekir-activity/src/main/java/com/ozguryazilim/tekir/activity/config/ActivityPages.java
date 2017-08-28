/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.config;

import com.ozguryazilim.tekir.activity.ActivityFeature;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.MainNavigationSection;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

/**
 * Activity View Configs
 * @author Hakan Uygun
 */
@ApplicationScoped
@Folder(name = "./activities")
public interface ActivityPages extends Pages{
    
    @SecuredPage() @View
    class ActivityWidget implements ActivityPages {}
    
    @View
    @SecuredPage()
    @PageTitle("module.caption.ActivityBrowse")
    @Navigation(label = "module.caption.ActivityBrowse", feature = ActivityFeature.class, section = MainNavigationSection.class)
    class ActivityBrowse implements ActivityPages {}
    
    @View
    @SecuredPage()
    @PageTitle("module.caption.Activity")
    class Activity implements ActivityPages {}
    
    @View
    @SecuredPage()
    @PageTitle("module.caption.Activity")
    class ActivityView implements ActivityPages {}
    
    @View
    @SecuredPage()
    @PageTitle("module.caption.Activity")
    class ActivityMasterView implements ActivityPages {}
    
    @View 
    @SecuredPage()
    class ActivityReminderCommand implements ActivityPages {}
    
    @SecuredPage()
    interface Phone extends ActivityPages{
        
        @SecuredPage() @View
        class PhoneActivityEditor implements Phone {}

        @SecuredPage() @View
        class PhoneActivityFragment implements Phone {}
    }
    
    @SecuredPage()
    interface EMail extends ActivityPages{
        
        @SecuredPage() @View
        class EMailActivityEditor implements EMail {}

        @SecuredPage() @View
        class EMailActivityFragment implements EMail {}
    }
    
    @SecuredPage()
    interface Meeting extends ActivityPages{
        
        @SecuredPage() @View
        class MeetingActivityEditor implements Meeting {}

        @SecuredPage() @View
        class MeetingActivityFragment implements Meeting {}
    }
    
    @SecuredPage()
    interface Task extends ActivityPages{
        
        @SecuredPage() @View
        class TaskActivityEditor implements Task {}

        @SecuredPage() @View
        class TaskActivityFragment implements Task {}
    }
}
