/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
