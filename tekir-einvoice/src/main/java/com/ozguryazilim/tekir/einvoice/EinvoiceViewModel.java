package com.ozguryazilim.tekir.einvoice;

import com.ozguryazilim.tekir.entities.EinvoiceStatus;
import com.ozguryazilim.tekir.entities.Invoice;
import com.ozguryazilim.telve.entities.ViewModel;

import java.io.Serializable;

public class EinvoiceViewModel implements ViewModel, Serializable {

    private Long id;
    private Invoice invoice;
    private String returnedMessage;
    private String einvoiceCode;
    private EinvoiceStatus einvoiceStatus;

    public EinvoiceViewModel(Long id, Invoice invoice, String returnedMessage, String einvoiceCode, EinvoiceStatus einvoiceStatus){
        this.id = id;
        this.invoice = invoice;
        this.returnedMessage = returnedMessage;
        this.einvoiceCode = einvoiceCode;
        this.einvoiceStatus = einvoiceStatus;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getReturnedMessage() {
        return returnedMessage;
    }

    public void setReturnedMessage(String returnedMessage) {
        this.returnedMessage = returnedMessage;
    }

    public String getEinvoiceCode() {
        return einvoiceCode;
    }

    public void setEinvoiceCode(String einvoiceCode) {
        this.einvoiceCode = einvoiceCode;
    }

    public EinvoiceStatus getEinvoiceStatus() {
        return einvoiceStatus;
    }

    public void setEinvoiceStatus(EinvoiceStatus einvoiceStatus) {
        this.einvoiceStatus = einvoiceStatus;
    }
}
