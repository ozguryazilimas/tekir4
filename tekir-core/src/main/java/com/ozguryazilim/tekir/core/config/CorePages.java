package com.ozguryazilim.tekir.core.config;

import com.ozguryazilim.telve.view.Pages;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.nav.Navigation;

/**
 * @author oyas
 */
@ApplicationScoped
@Folder(name = "./core")
public interface CorePages extends Pages {

    @View
    @SecuredPage()
    class TekirFeatureLookup implements CorePages {
    }

    @View
    @SecuredPage("location")
    @PageTitle("module.caption.Location")
    @Navigation(label = "module.caption.Location",
            icon = "fa fa-map-marker",
            section = ParamNavigationSection.class)
    class Location implements CorePages {
    }

    @View
    @SecuredPage()
    class LocationLookup implements CorePages {
    }

    @View
    @SecuredPage("industry")
    @PageTitle("module.caption.Industry")
    @Navigation(label = "module.caption.Industry",
            icon = "fa fa-industry",
            section = ParamNavigationSection.class)
    class Industry implements CorePages {
    }

    @View
    @SecuredPage()
    class IndustryLookup implements CorePages {
    }

    @View
    @SecuredPage("territory")
    @PageTitle("module.caption.Territory")
    @Navigation(label = "module.caption.Territory",
            icon = "fa fa-map-o",
            section = ParamNavigationSection.class)
    class Territory implements CorePages {
    }

    @View
    @SecuredPage()
    class TerritoryLookup implements CorePages {
    }

    @View
    @SecuredPage("currencyDefinition")
    class CurrencyDefinition implements CorePages {
    }

    @View
    @SecuredPage("exchangeRate")
    @PageTitle("module.caption.ExchangeRate")
    @Navigation(label = "module.caption.ExchangeRate",
            icon = "fa fa-money",
            section = FinanceParamNavigationSection.class)
    class ExchangeRate implements CorePages {
    }

    @View
    @SecuredPage("unitSetDefinition")
    @PageTitle("module.caption.UnitSetDefinition")
    @Navigation(label = "module.caption.UnitSetDefinition",
            icon = "fa fa-sitemap",
            section = ParamNavigationSection.class)
    class UnitSetDefinition implements CorePages {
    }

    @View
    @SecuredPage()
    class UnitSetDefinitionLookup
            implements
            com.ozguryazilim.tekir.core.config.CorePages {
    }

    @View
    @SecuredPage("taxDefinition")
    @PageTitle("module.caption.TaxDefinition")
    @Navigation(label = "module.caption.TaxDefinition",
            icon = "flaticon-tax",
            section = FinanceParamNavigationSection.class)
    class TaxDefinition implements CorePages {
    }

    @View
    @SecuredPage()
    class TaxDefinitionLookup implements CorePages {
    }


    @View
    @SecuredPage("paymentPlan")
    @PageTitle("module.caption.PaymentPlan")
    @Navigation(label = "module.caption.PaymentPlan",
            icon = "flaticon-tax",
            section = FinanceParamNavigationSection.class)
    class PaymentPlan implements CorePages {
    }

    @View
    @SecuredPage()
    class PaymentPlanLookup implements CorePages {
    }

    @View
    @SecuredPage()
    class TcmbExchangeRatesCommand implements CorePages {

    }

    @View
    @SecuredPage()
    class CorporateOptionPane implements CorePages {
    }

    @View
    @SecuredPage()
    class AutoCodeOptionPane implements CorePages {
    }

}
