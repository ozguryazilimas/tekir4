package com.ozguryazilim.tekir.config;

import com.ozguryazilim.tekir.order.config.OrderPages;
import com.ozguryazilim.telve.api.module.TelveModule;
import com.ozguryazilim.telve.view.ContextMenuResolverRegistery;
import javax.annotation.PostConstruct;

/**
 *
 * @author oyas
 */
@TelveModule
public class TekirOrderInvoiceLinkerModule {
    
    @PostConstruct
    public void init(){
        
        ContextMenuResolverRegistery.registerMenu(OrderPages.Purchase.PurchaseOrderView.class, "/menu/purchaseOrderInvoice.xhtml");
        ContextMenuResolverRegistery.registerMenu(OrderPages.Sales.SalesOrderView.class, "/menu/salesOrderInvoice.xhtml");
        
    }
}
