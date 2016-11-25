/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.config;

import com.ozguryazilim.tekir.core.config.ParamNavigationSection;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
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
@Folder(name = "./finance")
public interface FinancePages extends Pages {

    @View
    @SecuredPage("financeAccount")
    @PageTitle("module.caption.FinanceAccount")
    @Navigation(label = "module.caption.FinanceAccount", icon = "flaticon-tax", section = ParamNavigationSection.class)
    class FinanceAccountBrowse implements FinancePages {
    }

    @View
    @SecuredPage("financeAccount")
    @PageTitle("module.caption.FinanceAccount")
    class FinanceAccount implements FinancePages {
    }

    @View
    @SecuredPage("financeAccount")
    @PageTitle("module.caption.FinanceAccount")
    class FinanceAccountView
            implements
            FinancePages {
    }

    @View
    @SecuredPage("financeAccount")
    @PageTitle("module.caption.FinanceAccount")
    class FinanceAccountMasterView
            implements
            FinancePages {
    }

    @SecuredPage()
    @View
    class FinanceAccountLookup
            implements
            FinancePages {
    }

}
