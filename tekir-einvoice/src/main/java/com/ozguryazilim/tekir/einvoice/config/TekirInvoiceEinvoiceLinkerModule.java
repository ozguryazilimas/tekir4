package com.ozguryazilim.tekir.einvoice.config;

import com.ozguryazilim.tekir.invoice.config.InvoicePages;
import com.ozguryazilim.telve.api.module.TelveModule;
import com.ozguryazilim.telve.view.ContextMenuResolverRegistery;

import javax.annotation.PostConstruct;

/**
 * @author soner.cirit
 */
@TelveModule
public class TekirInvoiceEinvoiceLinkerModule {

    @PostConstruct
    public void init() {

        ContextMenuResolverRegistery.registerMenu(InvoicePages.Sales.SalesInvoiceView.class,
                "/menu/salesInvoiceEinvoice.xhtml");
    }
}
