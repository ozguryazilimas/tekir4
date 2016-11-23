/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        
    }
}
