/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice.purchase;

import com.ozguryazilim.tekir.entities.PurchaseInvoice;
import com.ozguryazilim.tekir.invoice.InvoiceRepository;
import java.util.Date;
import java.util.List;
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

    public abstract List<PurchaseInvoice> findByDateBetween(Date beginDate, Date endDate);
}
