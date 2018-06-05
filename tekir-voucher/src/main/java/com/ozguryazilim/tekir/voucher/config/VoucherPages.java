package com.ozguryazilim.tekir.voucher.config;

import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.nav.SideNavigationSection;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

/**
 * @author oyas
 */
@ApplicationScoped
@Folder(name = "./voucher")
public interface VoucherPages extends Pages {

    interface Process extends VoucherPages {

        @View
        @SecuredPage("process")
        @PageTitle("module.caption.Process")
        @Navigation(label = "module.caption.Process",
                icon = "fa fa-random",
                section = SideNavigationSection.class)
        class ProcessBrowse implements Process {
        }

        @View
        @SecuredPage("process")
        @PageTitle("module.caption.Process")
        class ProcessView implements Process {
        }

        @View
        @SecuredPage("process")
        @PageTitle("module.caption.Process")
        class ProcessMasterView implements Process {
        }

        @View
        @SecuredPage
        class ProcessLookup implements Process {
        }

        @View
        @SecuredPage
        @PageTitle("report.name.ProcessReport")
        class ProcessReport implements Process {

        }
    }

    interface Group extends VoucherPages {

        @View
        @SecuredPage("group")
        @PageTitle("module.caption.VoucherGroup")
        @Navigation(label = "module.caption.VoucherGroup",
                icon = "fa fa-object-group",
                section = SideNavigationSection.class)
        class VoucherGroupBrowse implements Group {
        }


        @View
        @SecuredPage("group")
        @PageTitle("module.caption.VoucherGroup")
        class VoucherGroupView implements Group {
        }

        @View
        @SecuredPage("group")
        @PageTitle("module.caption.VoucherGroup")
        class VoucherGroupMasterView implements Group {
        }

        @View
        @SecuredPage("group")
        @PageTitle("module.caption.VoucherGroup")
        class VoucherGroup implements Group {
        }

        @View
        @SecuredPage
        class NewVoucherGroupPopup implements Group {
        }

        @View
        @SecuredPage
        class VoucherGroupLookup implements Group {
        }

        @View
        @SecuredPage
        class VoucherGroupTxnsRefreshCommand implements Group {

        }

    }

    @View
    @SecuredPage
    class VoucherMatchableLookup implements VoucherPages {
    }


    @View
    @SecuredPage("voucherSerial")
    class VoucherSerial implements VoucherPages {
    }
}
