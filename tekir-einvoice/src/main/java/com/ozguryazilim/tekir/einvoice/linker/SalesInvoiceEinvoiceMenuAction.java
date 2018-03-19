package com.ozguryazilim.tekir.einvoice.linker;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.einvoice.EinvoiceBuilder;
import com.ozguryazilim.tekir.einvoice.EinvoiceSender;
import com.ozguryazilim.tekir.einvoice.sales.SalesEinvoiceRepository;
import com.ozguryazilim.tekir.entities.SalesEinvoice;
import com.ozguryazilim.tekir.entities.SalesInvoice;
import com.ozguryazilim.tekir.invoice.sales.SalesInvoiceHome;
import com.ozguryazilim.telve.messages.FacesMessages;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.Serializable;
import java.util.List;

import static com.ozguryazilim.tekir.einvoice.EinvoiceBuilder.SATICI_VKN;

/**
 * Fatura uzerinden SalesInvoice ve Kahve entitylerini alir.
 * Ardindan sirasiyla EinvoiceBuilder ve EinvoiceSender siniflarini calistirir.
 *
 * @author soner.cirit
 * @return Efatura islemlerini takip etmek icin gerekli olan belgeOid numarasini doner.
 */
@Named
@RequestScoped
public class SalesInvoiceEinvoiceMenuAction implements Serializable {
    private static final String USERNAME = "einvoice.username";
    private static final String PASSWORD = "einvoice.password";
    @Inject
    SalesInvoiceHome invoiceHome;
    @Inject
    Kahve kahve;
    @Inject
    SalesEinvoiceRepository salesEinvoiceRepository;
    private EinvoiceSender sender = new EinvoiceSender();

    public void newEinvoice() throws Exception {
        SalesInvoice entity = invoiceHome.getEntity();
        EinvoiceBuilder einvoiceBuilder = new EinvoiceBuilder();
        File file = einvoiceBuilder.buildEinvoice(entity, kahve);
        String rM = sender.sendEinvoice(file, kahve.get(SATICI_VKN, "").getAsString(), entity, kahve.get(USERNAME,
                "").getAsString(), kahve.get(PASSWORD, "").getAsString(), salesEinvoiceRepository);
        if (rM.equals("SUCCESS")) {
            FacesMessages.info("E-fatura başarıyla gönderilmiştir.");
        } else {
            FacesMessages.error("E-fatura gönderilememiştir.", rM);
        }
    }

    public List<SalesEinvoice> checkIfExists() {
        SalesInvoice entity = invoiceHome.getEntity();
        return salesEinvoiceRepository.findByInvoice(entity);
    }
}