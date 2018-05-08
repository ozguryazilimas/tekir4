/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.entities.Quantity;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.voucher.VoucherCommodityItemViewModel;
import java.math.BigDecimal;

/**
 *
 * @author oyas
 */
public class QuoteItemViewModel extends VoucherCommodityItemViewModel<Quote>{
    
    public QuoteItemViewModel(Long id, Long commodityId, String commodityName, String info, Quantity quantity, BigDecimal price, Integer discountRate, BigDecimal discount, BigDecimal total, BigDecimal lineTotal) {
        super(id, commodityId, commodityName, info, quantity, price, discountRate, discount, total, lineTotal);
    }
    
}
