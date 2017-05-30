/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.config;

import com.ozguryazilim.tekir.core.config.ParamNavigationSection;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.nav.SideNavigationSection;
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
@Folder(name = "./")
public interface VoucherPages extends Pages {

	@Folder
	interface Voucher extends VoucherPages {

		interface Process extends VoucherPages {
			@View
			@SecuredPage("process")
			@PageTitle("module.caption.Process")
			@Navigation(label = "module.caption.Process", icon = "fa fa-random", section = SideNavigationSection.class)
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

			@SecuredPage()
			@View
			class ProcessLookup implements Process {
			}
		}

		interface Group extends VoucherPages {
			@View
			@SecuredPage("group")
			@PageTitle("module.caption.VoucherGroup")
			@Navigation(label = "module.caption.VoucherGroup", icon = "fa fa-object-group", section = SideNavigationSection.class)
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

			@SecuredPage()
			@View
			class NewVoucherGroupPopup implements Group {
			}

			@SecuredPage()
			@View
			class VoucherGroupLookup implements Group {
			}

		}

		@SecuredPage()
		@View
		class VoucherMatchableLookup implements VoucherPages {
		}

		@SecuredPage("voucherSerial")
		@View
		@Navigation(label = "module.caption.VoucherSerial", icon = "fa fa-book", section = ParamNavigationSection.class)
		class VoucherSerial implements VoucherPages {
		}

	}
	
	@Folder
	interface Dialogs extends VoucherPages {
		
		@View
		class CommodityItemEditor implements Dialogs {
		}
		
	}
}
