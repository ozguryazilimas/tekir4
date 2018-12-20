package com.ozguryazilim.tekir.invoice.sales;

import com.ozguryazilim.tekir.entities.SalesInvoice;
import com.ozguryazilim.tekir.invoice.InvoiceRepository;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class SalesInvoiceRepository extends InvoiceRepository<SalesInvoice, SalesInvoiceViewModel>{

    @Override
    public Class<SalesInvoice> getEntityClass() {
        return SalesInvoice.class;
    }

    @Override
    protected Class<SalesInvoiceViewModel> getViewModelClass() {
        return SalesInvoiceViewModel.class;
    }
    
}
