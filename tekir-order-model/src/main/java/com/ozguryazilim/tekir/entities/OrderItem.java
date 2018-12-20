package com.ozguryazilim.tekir.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author oyas
 */
@Entity
@Table(name = "TOR_ORDER_ITEM")
public class OrderItem extends VoucherCommodityItemBase<Order>{
    
}
