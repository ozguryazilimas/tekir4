/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice.sales;

import com.ozguryazilim.tekir.entities.SalesInvoice;
import com.ozguryazilim.tekir.invoice.config.InvoicePages;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import javax.enterprise.inject.Default;

/**
 *
 * @author oyas
 */
@Feature(permission = "salesInvoice", forEntity = SalesInvoice.class )
@Page( type = PageType.BROWSE, page = InvoicePages.Sales.SalesInvoiceBrowse.class )
@Page( type = PageType.VIEW, page = InvoicePages.Sales.SalesInvoiceView.class )
@Page( type = PageType.MASTER_VIEW, page = InvoicePages.Sales.SalesInvoiceMasterView.class )
@Page( type = PageType.EDIT, page = InvoicePages.Sales.SalesInvoice.class )
@Voucher @Default
public class SalesInvoiceFeature extends AbstractFeatureHandler{
    
}
