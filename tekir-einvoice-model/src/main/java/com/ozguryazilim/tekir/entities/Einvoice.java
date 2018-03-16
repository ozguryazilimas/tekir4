package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.AuditBase;

import javax.persistence.*;

@Entity
@Table(name = "TEI_EINVOICE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DIRECTION")
public class Einvoice extends AuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "INVOICE_ID", foreignKey = @ForeignKey(name = "FK_EIV_IV"))
    private Invoice invoice;

    @Column(name = "RETURNED_MESSAGE")
    private String returnedMessage;

    @Column(name = "EINVOICE_CODE")
    private String einvoiceCode;

    @Column(name = "EINVOICE_STATUS")
    @Enumerated(EnumType.STRING)
    private EinvoiceStatus einvoiceStatus;


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