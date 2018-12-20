package com.ozguryazilim.tekir.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author oyas
 */
@Entity
@Table(name = "TIV_INVOICE_SUM")
public class InvoiceSummary extends VoucherSummaryBase<Invoice> {
    
}
