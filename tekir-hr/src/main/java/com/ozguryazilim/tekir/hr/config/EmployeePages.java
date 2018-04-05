package com.ozguryazilim.tekir.hr.config;

import com.ozguryazilim.tekir.hr.credit.EmployeeCreditNoteFeature;
import com.ozguryazilim.tekir.hr.employee.EmployeeFeature;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

/**
 * @author oyas
 */
@ApplicationScoped
@Folder(name = "./employee")
public interface EmployeePages extends Pages {

    @View
    @SecuredPage("employee")
    @PageTitle("module.caption.EmployeeBrowse")
    @Navigation(label = "module.caption.EmployeeBrowse",
            feature = EmployeeFeature.class,
            section = HRNavigationSection.class)
    class EmployeeBrowse implements EmployeePages {
    }

    @View
    @SecuredPage("employee")
    @PageTitle("module.caption.Employee")
    class Employee implements EmployeePages {
    }

    @View
    @SecuredPage("employee")
    @PageTitle("module.caption.Employee")
    class EmployeeView implements EmployeePages {
    }

    @View
    @SecuredPage("employee")
    @PageTitle("module.caption.EmployeeMasterView")
    class EmployeeMasterView implements EmployeePages {
    }

    @View
    @SecuredPage
    class EmployeeLookup implements EmployeePages {
    }

    @View
    @SecuredPage("employeeCreditNote")
    @PageTitle("module.caption.EmployeeCreditNoteBrowse")
    @Navigation(label = "module.caption.EmployeeCreditNoteBrowse",
            feature = EmployeeCreditNoteFeature.class,
            section = HRNavigationSection.class)
    class EmployeeCreditNoteBrowse implements EmployeePages {
    }

    @View
    @SecuredPage("employeeCreditNote")
    @PageTitle("module.caption.EmployeeCreditNote")
    class EmployeeCreditNote implements EmployeePages {
    }

    @View
    @SecuredPage("employeeCreditNote")
    @PageTitle("module.caption.EmployeeCreditNote")
    class EmployeeCreditNoteView implements EmployeePages {
    }

    @View
    @SecuredPage("employeeCreditNote")
    @PageTitle("module.caption.EmployeeCreditNoteMasterView")
    class EmployeeCreditNoteMasterView implements EmployeePages {
    }

}
