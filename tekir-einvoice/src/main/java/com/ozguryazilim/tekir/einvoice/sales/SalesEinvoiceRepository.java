package com.ozguryazilim.tekir.einvoice.sales;

import com.ozguryazilim.tekir.einvoice.EinvoiceRepository;
import com.ozguryazilim.tekir.entities.Invoice;
import com.ozguryazilim.tekir.entities.SalesEinvoice;
import org.apache.deltaspike.data.api.Repository;

import javax.enterprise.context.Dependent;
import java.util.List;

@Repository
@Dependent
public abstract class SalesEinvoiceRepository extends EinvoiceRepository<SalesEinvoice, SalesEinvoiceViewModel> {
    @Override
    public Class<SalesEinvoice> getEntityClass() {
        return SalesEinvoice.class;
    }

    @Override
    protected Class<SalesEinvoiceViewModel> getViewModelClass() {
        return SalesEinvoiceViewModel.class;
    }

    public abstract List<SalesEinvoice> findByInvoice(Invoice invoice);
}