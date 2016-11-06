/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.config;

import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

/**
 * Cari Virman ve Dekont Fiş Sayfaları
 * @author Hakan Uygun
 */
@ApplicationScoped
@Folder(name = "./accountNotes")
public interface AccountNotePages extends Pages{

    @View
    @SecuredPage("accountCreditNote")
    @PageTitle("module.caption.AccountCreditNoteBrowse")
    @Navigation(label = "module.caption.AccountCreditNoteBrowse", icon = "fa fa-book", section = AccountNoteNavigationSection.class)
    class AccountCreditNoteBrowse implements AccountNotePages {
    }

    @View
    @SecuredPage("accountCreditNote")
    @PageTitle("module.caption.AccountCreditNote")
    class AccountCreditNote implements AccountNotePages {
    }

    @View
    @SecuredPage("accountCreditNote")
    @PageTitle("module.caption.AccountCreditNote")
    class AccountCreditNoteView
            implements
            AccountNotePages {
    }

    @View
    @SecuredPage("accountCreditNote")
    class AccountCreditNoteMasterView
            implements
            AccountNotePages {
    }
    
    @View
    @SecuredPage("accountDebitNote")
    @PageTitle("module.caption.AccountDebitNoteBrowse")
    @Navigation(label = "module.caption.AccountDebitNoteBrowse", icon = "fa fa-book", section = AccountNoteNavigationSection.class)
    class AccountDebitNoteBrowse implements AccountNotePages {
    }

    @View
    @SecuredPage("accountDebitNote")
    @PageTitle("module.caption.AccountDebitNote")
    class AccountDebitNote implements AccountNotePages {
    }

    @View
    @SecuredPage("accountDebitNote")
    @PageTitle("module.caption.AccountDebitNote")
    class AccountDebitNoteView
            implements
            AccountNotePages {
    }

    @View
    @SecuredPage("accountDebitNote")
    class AccountDebitNoteMasterView
            implements
            AccountNotePages {
    }


    @View
    @SecuredPage("accountVirement")
    @PageTitle("module.caption.AccountVirementBrowse")
    @Navigation(label = "module.caption.AccountVirementBrowse", icon = "fa fa-book", section = AccountNoteNavigationSection.class)
    class AccountVirementBrowse implements AccountNotePages {
    }

    @View
    @SecuredPage("accountVirement")
    @PageTitle("module.caption.AccountVirement")
    class AccountVirement implements AccountNotePages {
    }

    @View
    @SecuredPage("accountVirement")
    @PageTitle("module.caption.AccountVirement")
    class AccountVirementView
            implements
            AccountNotePages {
    }

    @View
    @SecuredPage("accountVirement")
    class AccountVirementMasterView
            implements
            AccountNotePages {
    }
    
}
