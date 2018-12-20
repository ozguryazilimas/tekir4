package com.ozguryazilim.tekir.invoice.purchase;

import com.ozguryazilim.tekir.entities.PurchaseInvoice;
import com.ozguryazilim.tekir.invoice.InvoiceRepository;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class PurchaseInvoiceRepository extends InvoiceRepository<PurchaseInvoice, PurchaseInvoiceViewModel> {

    @Override
    public Class<PurchaseInvoice> getEntityClass() {
        return PurchaseInvoice.class;
    }

    @Override
    protected Class<PurchaseInvoiceViewModel> getViewModelClass() {
        return PurchaseInvoiceViewModel.class;
    }

}
