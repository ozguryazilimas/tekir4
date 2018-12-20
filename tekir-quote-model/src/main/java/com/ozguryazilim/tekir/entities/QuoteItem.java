package com.ozguryazilim.tekir.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Teklif satırı
 * 
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TSQ_QUOTE_ITEM")
public class QuoteItem extends VoucherCommodityItemBase<Quote>{

}
