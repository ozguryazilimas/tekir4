/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.config;

import com.ozguryazilim.tekir.core.config.PurchaseNavigationSection;
import com.ozguryazilim.tekir.core.config.SalesNavigationSection;
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
@Folder(name = "./order")
public interface OrderPages extends Pages {
    
    interface Sales extends OrderPages{
        @View
	@SecuredPage("salesOrder")
	@PageTitle("module.caption.SalesOrderBrowse")
	@Navigation(label = "module.caption.SalesOrderBrowse", icon = "flaticon-speech-bubble", section = SalesNavigationSection.class)
	class SalesOrderBrowse implements Sales {
	}

	@View
	@SecuredPage("salesOrder")
	@PageTitle("module.caption.SalesOrder")
	class SalesOrder implements Sales {
	}

	@View
	@SecuredPage("salesOrder")
	@PageTitle("module.caption.SalesOrderView")
	class SalesOrderView implements Sales {
	}

	@View
	@SecuredPage("salesOrder")
	@PageTitle("module.caption.SalesOrderMasterView")
	class SalesOrderMasterView
			implements
				Sales {
	}

    }
    
    interface Purchase extends OrderPages{
        @View
	@SecuredPage("purchaseOrder")
	@PageTitle("module.caption.PurchaseOrderBrowse")
	@Navigation(label = "module.caption.PurchaseOrderBrowse", icon = "flaticon-speech-bubble", section = PurchaseNavigationSection.class)
	class PurchaseOrderBrowse implements Purchase {
	}

	@View
	@SecuredPage("purchaseOrder")
	@PageTitle("module.caption.PurchaseOrder")
	class PurchaseOrder implements Purchase {
	}

	@View
	@SecuredPage("purchaseOrder")
	@PageTitle("module.caption.PurchaseOrderView")
	class PurchaseOrderView implements Purchase {
	}

	@View
	@SecuredPage("purchaseOrder")
	@PageTitle("module.caption.PurchaseOrderMasterView")
	class PurchaseOrderMasterView
			implements
				Purchase {
	}
    }
}
