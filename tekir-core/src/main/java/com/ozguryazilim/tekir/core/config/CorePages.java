/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.config;

import com.ozguryazilim.telve.view.Pages;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.tekir.core.config.CorePages;

/**
 *
 * @author oyas
 */
@ApplicationScoped
@Folder(name = "./")
public interface CorePages extends Pages {

	interface Core extends CorePages {

		@SecuredPage()
		@View
		class TekirFeatureLookup implements CorePages {
		}

		@View
		@SecuredPage("location")
		@PageTitle("module.caption.Location")
		@Navigation(label = "module.caption.Location", icon = "fa fa-map-marker", section = ParamNavigationSection.class)
		class Location implements CorePages {
		}

		@SecuredPage()
		@View
		class LocationLookup implements CorePages {
		}

		@View
		@SecuredPage("industry")
		@PageTitle("module.caption.Industry")
		@Navigation(label = "module.caption.Industry", icon = "fa fa-industry", section = ParamNavigationSection.class)
		class Industry implements CorePages {
		}

		@SecuredPage()
		@View
		class IndustryLookup implements CorePages {
		}

		@View
		@SecuredPage("territory")
		@PageTitle("module.caption.Territory")
		@Navigation(label = "module.caption.Territory", icon = "fa fa-map-o", section = ParamNavigationSection.class)
		class Territory implements CorePages {
		}

		@SecuredPage()
		@View
		class TerritoryLookup implements CorePages {
		}

		@View
		@SecuredPage("currencyDefinition")
		@PageTitle("module.caption.CurrencyDefinition")
		@Navigation(label = "module.caption.CurrencyDefinition", icon = "flaticon-exchange-1", section = FinanceParamNavigationSection.class)
		class CurrencyDefinition implements CorePages {
		}

		@View
		@SecuredPage("exchangeRate")
		@PageTitle("module.caption.ExchangeRate")
		@Navigation(label = "module.caption.ExchangeRate", icon = "fa fa-money", section = FinanceParamNavigationSection.class)
		class ExchangeRate implements CorePages {
		}

		@View
		@SecuredPage("unitSetDefinition")
		@PageTitle("module.caption.UnitSetDefinition")
		@Navigation(label = "module.caption.UnitSetDefinition", icon = "fa fa-sitemap", section = ParamNavigationSection.class)
		class UnitSetDefinition implements CorePages {
		}

		@View
		@SecuredPage()
		class UnitSetDefinitionLookup implements com.ozguryazilim.tekir.core.config.CorePages {
		}

		@View
		@SecuredPage("taxDefinition")
		@PageTitle("module.caption.TaxDefinition")
		@Navigation(label = "module.caption.TaxDefinition", icon = "flaticon-tax", section = FinanceParamNavigationSection.class)
		class TaxDefinition implements com.ozguryazilim.tekir.core.config.CorePages {
		}

		@View
		@SecuredPage()
		class TaxDefinitionLookup implements com.ozguryazilim.tekir.core.config.CorePages {
		}

		@View
		@SecuredPage("paymentPlan")
		@PageTitle("module.caption.PaymentPlan")
		@Navigation(label = "module.caption.PaymentPlan", icon = "flaticon-tax", section = FinanceParamNavigationSection.class)
		class PaymentPlan implements com.ozguryazilim.tekir.core.config.CorePages {
		}

		@View
		@SecuredPage()
		class PaymentPlanLookup implements com.ozguryazilim.tekir.core.config.CorePages {
		}

		@SecuredPage()
		@View
		class TcmbExchangeRatesCommand implements com.ozguryazilim.tekir.core.config.CorePages {

		}

	}

	@Folder
	interface Dialogs extends CorePages {

		@View
		class ChangeOwnerDialog implements Dialogs {
		}

	}

}
