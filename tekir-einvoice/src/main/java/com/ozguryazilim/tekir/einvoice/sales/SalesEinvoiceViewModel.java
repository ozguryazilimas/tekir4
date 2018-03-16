package com.ozguryazilim.tekir.einvoice.sales;

import com.ozguryazilim.tekir.einvoice.EinvoiceViewModel;
import com.ozguryazilim.tekir.entities.EinvoiceStatus;
import com.ozguryazilim.tekir.entities.Invoice;

public class SalesEinvoiceViewModel extends EinvoiceViewModel {
    public SalesEinvoiceViewModel(Long id, Invoice invoice, String returnedMessage, String einvoiceCode, EinvoiceStatus einvoiceStatus) {
        super(id, invoice, returnedMessage, einvoiceCode, einvoiceStatus);
    }
}
