/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.config;

import com.ozguryazilim.tekir.activity.ActivityFeature;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.nav.SideNavigationSection;
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
    @Navigation(label = "module.caption.ActivityBrowse", feature = ActivityFeature.class, section = SideNavigationSection.class)
    class ActivityBrowse implements ActivityPages {}
    
    @SecuredPage()
    interface Phone extends ActivityPages{
        @SecuredPage() @View
        class PhoneActivityEditor implements Phone {}
        
        @SecuredPage() @View
        class PhoneActivityFragment implements Phone {}
    }
}
