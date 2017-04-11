/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice.purchase;

import javax.enterprise.inject.Default;

import com.ozguryazilim.tekir.entities.PurchaseInvoice;
import com.ozguryazilim.tekir.invoice.config.InvoicePages;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import com.ozguryazilim.telve.feature.search.Search;

/**
 *
 * @author oyas
 */
@Feature(permission = "purchaseInvoice", forEntity = PurchaseInvoice.class )
@Page( type = PageType.BROWSE, page = InvoicePages.Purchase.PurchaseInvoiceBrowse.class )
@Page( type = PageType.VIEW, page = InvoicePages.Purchase.PurchaseInvoiceView.class )
@Page( type = PageType.MASTER_VIEW, page = InvoicePages.Purchase.PurchaseInvoiceMasterView.class )
@Page( type = PageType.EDIT, page = InvoicePages.Purchase.PurchaseInvoice.class )
@Search(handler = PurchaseInvoiceSearchHandler.class )
@Voucher @Default
public class PurchaseInvoiceFeature extends AbstractFeatureHandler{
    
}
