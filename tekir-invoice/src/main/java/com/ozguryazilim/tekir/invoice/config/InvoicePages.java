/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice.config;

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
@Folder(name = "./invoice")
public interface InvoicePages extends Pages {
    interface Sales extends InvoicePages{
        @View
	@SecuredPage("salesInvoice")
	@PageTitle("module.caption.SalesInvoiceBrowse")
	@Navigation(label = "module.caption.SalesInvoiceBrowse", icon = "flaticon-invoice-1", section = SalesNavigationSection.class)
	class SalesInvoiceBrowse implements Sales {
	}

	@View
	@SecuredPage("salesInvoice")
	@PageTitle("module.caption.SalesInvoice")
	class SalesInvoice implements Sales {
	}

	@View
	@SecuredPage("salesInvoice")
	@PageTitle("module.caption.SalesInvoiceView")
	class SalesInvoiceView implements Sales {
	}

	@View
	@SecuredPage("salesInvoice")
	@PageTitle("module.caption.SalesInvoiceMasterView")
	class SalesInvoiceMasterView
			implements
				Sales {
	}

    }
    
    interface Purchase extends InvoicePages{
        @View
	@SecuredPage("purchaseInvoice")
	@PageTitle("module.caption.PurchaseInvoiceBrowse")
	@Navigation(label = "module.caption.PurchaseInvoiceBrowse", icon = "flaticon-invoice-1", section = PurchaseNavigationSection.class)
	class PurchaseInvoiceBrowse implements Purchase {
	}

	@View
	@SecuredPage("purchaseInvoice")
	@PageTitle("module.caption.PurchaseInvoice")
	class PurchaseInvoice implements Purchase {
	}

	@View
	@SecuredPage("purchaseInvoice")
	@PageTitle("module.caption.PurchaseInvoiceView")
	class PurchaseInvoiceView implements Purchase {
	}

	@View
	@SecuredPage("purchaseInvoice")
	@PageTitle("module.caption.PurchaseInvoiceMasterView")
	class PurchaseInvoiceMasterView
			implements
				Purchase {
	}
    }
}
