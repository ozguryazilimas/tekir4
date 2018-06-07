/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice.sales;

import com.ozguryazilim.tekir.entities.SalesInvoice;
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
public abstract class SalesInvoiceRepository extends InvoiceRepository<SalesInvoice, SalesInvoiceViewModel>{

    @Override
    public Class<SalesInvoice> getEntityClass() {
        return SalesInvoice.class;
    }

    @Override
    protected Class<SalesInvoiceViewModel> getViewModelClass() {
        return SalesInvoiceViewModel.class;
    }

    public abstract List<SalesInvoice> findByDateBetween(Date beginDate, Date endDate);
}
