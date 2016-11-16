/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.utils;

import com.ozguryazilim.tekir.entities.TaxDefinition;
import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.entities.VoucherCommodityItemBase;
import com.ozguryazilim.tekir.entities.VoucherSummaryBase;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @author oyas
 */
public class SummaryCalculator<E extends VoucherBase, I extends VoucherCommodityItemBase, S extends VoucherSummaryBase>{
    
    public void calcSummaries(  Supplier<E> entitySupplier,
                                Supplier<List<I>> itemsSupplier, 
                                Supplier<Map<String, S>>  summariesSupplier, 
                                Supplier<S> newSummarySupplier, 
                                Consumer<BigDecimal> totalConsumer ) {
        
        Map<String, S> summaries = summariesSupplier.get();
        List<I> items = itemsSupplier.get();
        E entity = entitySupplier.get();
        
        summaries.clear();

        //Burayı nasıl yapacağız?
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalTax = BigDecimal.ZERO;

        for (VoucherCommodityItemBase it : items ) {

            TaxDefinition tax = it.getCommodity().getTax1();
            if (tax != null) {
                BigDecimal taxAmt = it.getTotal().multiply(tax.getRate());
                totalTax = totalTax.add(taxAmt);

                S sm = summaries.get("Tax." + tax.getCode());
                if (sm == null) {
                    sm = newSummarySupplier.get();
                    sm.setRowKey("Tax." + tax.getCode());
                    sm.setInfo(tax.getName());
                    sm.setMaster(entity);
                    sm.setAmount(taxAmt);
                    summaries.put(sm.getRowKey(), sm);
                } else {
                    sm.setAmount(sm.getAmount().add(taxAmt));
                }
            }

            tax = it.getCommodity().getTax2();
            if (tax != null) {
                BigDecimal taxAmt = it.getTotal().multiply(tax.getRate());
                totalTax = totalTax.add(taxAmt);

                S sm = summaries.get("Tax." + tax.getCode());
                if (sm == null) {
                    sm = newSummarySupplier.get();
                    sm.setRowKey("Tax." + tax.getCode());
                    sm.setInfo(tax.getName());
                    sm.setMaster(entity);
                    sm.setAmount(taxAmt);
                    summaries.put(sm.getRowKey(), sm);
                } else {
                    sm.setAmount(sm.getAmount().add(taxAmt));
                }
            }

            tax = it.getCommodity().getTax3();
            if (tax != null) {
                BigDecimal taxAmt = it.getTotal().multiply(tax.getRate());
                totalTax = totalTax.add(taxAmt);

                S sm = summaries.get("Tax." + tax.getCode());
                if (sm == null) {
                    sm = newSummarySupplier.get();
                    sm.setRowKey("Tax." + tax.getCode());
                    sm.setInfo(tax.getName());
                    sm.setMaster(entity);
                    sm.setAmount(taxAmt);
                    summaries.put(sm.getRowKey(), sm);
                } else {
                    sm.setAmount(sm.getAmount().add(taxAmt));
                }
            }

        }

        
        //Saklamadan hemen önce toplam tutarı hesaplayıp fiş üzerine yazalım.
        Function<I, BigDecimal> totalMapper = item -> item.getTotal();
        total = items.stream().map(totalMapper).reduce(BigDecimal.ZERO, BigDecimal::add);
        

        S sm = newSummarySupplier.get();
        sm.setRowKey("TaxTotal");
        sm.setMaster(entity);
        sm.setAmount(totalTax);
        summaries.put(sm.getRowKey(), sm);

        sm = newSummarySupplier.get();
        sm.setRowKey("BeforeTaxTotal");
        sm.setMaster(entity);
        sm.setAmount(total);
        summaries.put(sm.getRowKey(), sm);

        //Genel Toplam
        sm = newSummarySupplier.get();
        sm.setRowKey("GrandTotal");
        sm.setMaster(entity);
        sm.setAmount(total.add(totalTax));
        summaries.put(sm.getRowKey(), sm);
        

        //Sonuç toplamı da geri döndürelim.
        totalConsumer.accept(total);
    }
}
