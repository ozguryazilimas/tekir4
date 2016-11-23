/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice.purchase;

import com.ozguryazilim.tekir.entities.PurchaseInvoice;
import com.ozguryazilim.tekir.invoice.InvoiceBrowse;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature=PurchaseInvoiceFeature.class )
public class PurchaseInvoiceBrowse extends InvoiceBrowse<PurchaseInvoice, PurchaseInvoiceViewModel> {

    @Inject
    private PurchaseInvoiceRepository repository;
    
    @Override
    public VoucherRepositoryBase<PurchaseInvoice, PurchaseInvoiceViewModel> getVoucherRepository() {
        return repository;
    }

}
