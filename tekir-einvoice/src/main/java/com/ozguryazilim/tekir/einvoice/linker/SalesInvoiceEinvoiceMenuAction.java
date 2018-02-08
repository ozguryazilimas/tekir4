/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.einvoice.linker;
import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.core.options.CorporateOptionPane;
import com.ozguryazilim.tekir.einvoice.EinvoiceBuilder;
import com.ozguryazilim.tekir.entities.SalesInvoice;
import com.ozguryazilim.tekir.invoice.sales.SalesInvoiceHome;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

;

/**
 *
 * @author oyas
 */
@Named
@RequestScoped
public class SalesInvoiceEinvoiceMenuAction implements Serializable{
    @Inject
    SalesInvoiceHome invoiceHome;
    @Inject
    Kahve kahve;

    public void newEinvoice() throws Exception {
        SalesInvoice entity = invoiceHome.getEntity();
        EinvoiceBuilder einvoiceBuilder = new EinvoiceBuilder();
        einvoiceBuilder.buildEinvoice(entity, kahve);
    }
}
