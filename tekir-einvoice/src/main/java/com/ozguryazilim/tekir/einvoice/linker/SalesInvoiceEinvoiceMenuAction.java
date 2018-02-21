package com.ozguryazilim.tekir.einvoice.linker;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.einvoice.EinvoiceBuilder;
import com.ozguryazilim.tekir.einvoice.EinvoiceSender;
import com.ozguryazilim.tekir.entities.SalesInvoice;
import com.ozguryazilim.tekir.invoice.sales.SalesInvoiceHome;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.Serializable;

;import static com.ozguryazilim.tekir.einvoice.EinvoiceBuilder.SATICI_VKN;

/**
 * Fatura uzerinden SalesInvoice ve Kahve entitylerini alir.
 * Ardindan sirasiyla EinvoiceBuilder ve EinvoiceSender siniflarini calistirir.
 * @return Efatura islemlerini takip etmek icin gerekli olan belgeOid numarasini doner.
 * @author soner.cirit
 */
@Named
@RequestScoped
public class SalesInvoiceEinvoiceMenuAction implements Serializable{
    private static final String USERNAME = "einvoice.username";
    private static final String PASSWORD = "einvoice.password";
    @Inject
    SalesInvoiceHome invoiceHome;
    @Inject
    Kahve kahve;
    private EinvoiceSender sender = new EinvoiceSender();

    public String newEinvoice() throws Exception {
        SalesInvoice entity = invoiceHome.getEntity();
        EinvoiceBuilder einvoiceBuilder = new EinvoiceBuilder();
        File file = einvoiceBuilder.buildEinvoice(entity, kahve);
        return sender.sendEinvoice(file, kahve.get(SATICI_VKN, "").getAsString(), entity.getVoucherNo(), kahve.get(USERNAME,"").getAsString(), kahve.get(PASSWORD,"").getAsString());
    }
}
