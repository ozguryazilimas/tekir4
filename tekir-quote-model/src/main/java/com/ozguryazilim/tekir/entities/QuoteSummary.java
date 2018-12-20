package com.ozguryazilim.tekir.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author oyas
 */
@Entity
@Table(name = "TSQ_QUOTE_SUM")
public class QuoteSummary extends VoucherSummaryBase<Quote>{
    
}
