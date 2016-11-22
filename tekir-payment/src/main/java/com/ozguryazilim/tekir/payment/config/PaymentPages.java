/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment.config;

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
@Folder(name = "./payment")
public interface PaymentPages extends Pages {
    
    interface Sales extends PaymentPages{
        @View
	@SecuredPage("paymentReceived")
	@PageTitle("module.caption.PaymentReceivedBrowse")
	@Navigation(label = "module.caption.PaymentReceivedBrowse", icon = "flaticon-speech-bubble", section = SalesNavigationSection.class)
	class PaymentReceivedBrowse implements Sales {
	}

	@View
	@SecuredPage("paymentReceived")
	@PageTitle("module.caption.PaymentReceived")
	class PaymentReceived implements Sales {
	}

	@View
	@SecuredPage("paymentReceived")
	@PageTitle("module.caption.PaymentReceivedView")
	class PaymentReceivedView implements Sales {
	}

	@View
	@SecuredPage("paymentReceived")
	@PageTitle("module.caption.PaymentReceivedMasterView")
	class PaymentReceivedMasterView
			implements
				Sales {
	}

    }
    
    interface Purchase extends PaymentPages{
        @View
	@SecuredPage("payment")
	@PageTitle("module.caption.PaymentBrowse")
	@Navigation(label = "module.caption.PaymentBrowse", icon = "flaticon-speech-bubble", section = PurchaseNavigationSection.class)
	class PaymentBrowse implements Purchase {
	}

	@View
	@SecuredPage("payment")
	@PageTitle("module.caption.Payment")
	class Payment implements Purchase {
	}

	@View
	@SecuredPage("payment")
	@PageTitle("module.caption.PaymentView")
	class PaymentView implements Purchase {
	}

	@View
	@SecuredPage("payment")
	@PageTitle("module.caption.PaymentMasterView")
	class PaymentMasterView
			implements
				Purchase {
	}

    }
}
