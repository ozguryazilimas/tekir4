package com.ozguryazilim.tekir.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author oyas
 */
@Entity
@Table(name = "TOR_ORDER_SUM")
public class OrderSummary extends VoucherSummaryBase<Order> {
    
}
