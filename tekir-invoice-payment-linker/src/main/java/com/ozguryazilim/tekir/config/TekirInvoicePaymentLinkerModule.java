package com.ozguryazilim.tekir.config;

import com.ozguryazilim.tekir.invoice.config.InvoicePages;
import com.ozguryazilim.telve.api.module.TelveModule;
import com.ozguryazilim.telve.view.ContextMenuResolverRegistery;
import javax.annotation.PostConstruct;

/**
 *
 * @author oyas
 */
@TelveModule
public class TekirInvoicePaymentLinkerModule {
    
    @PostConstruct
    public void init(){
        
        ContextMenuResolverRegistery.registerMenu(InvoicePages.Purchase.PurchaseInvoiceView.class, "/menu/purchaseInvoicePayment.xhtml");
        ContextMenuResolverRegistery.registerMenu(InvoicePages.Sales.SalesInvoiceView.class, "/menu/salesInvoicePayment.xhtml");
        
    }
}
