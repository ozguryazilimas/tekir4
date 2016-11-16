/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * VoucherCommodityItemBase detaylarına sahip olan master sınıflar için arayüz.
 * 
 * Bu sayede toplam alma işlemleri için gereken methodlara ulaşmak mümkün olacak.
 * 
 * Bakınız @see SummaryCalculator sınıfı.
 * 
 * @author Hakan Uygun
 */
public interface VoucherCommodityMaster<E extends VoucherBase>{
    
    
    BigDecimal getTotal();

    void setTotal(BigDecimal total);

    List<? extends VoucherCommodityItemBase<? super E>> getItems();
    
    Map<String, ? extends VoucherSummaryBase<? super E>> getSummaries();

}
