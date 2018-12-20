package com.ozguryazilim.tekir.invoice.purchase;

import com.ozguryazilim.tekir.entities.PurchaseInvoice;
import com.ozguryazilim.tekir.invoice.InvoiceBrowse;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
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
    
    @Inject
    private PurchaseInvoiceHome home;
    
    @Override
    public VoucherRepositoryBase<PurchaseInvoice, PurchaseInvoiceViewModel> getVoucherRepository() {
        return repository;
    }

	@Override
	public VoucherFormBase<PurchaseInvoice> getHome() {
		// TODO Auto-generated method stub
		return home;
	}

    @Override
    public String getTagKey() {
        return home.getTagKey();
    }

}
