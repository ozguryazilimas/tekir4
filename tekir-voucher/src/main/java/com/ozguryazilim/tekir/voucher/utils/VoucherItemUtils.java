/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.utils;

import com.ozguryazilim.tekir.entities.VoucherCommodityItemBase;
import com.ozguryazilim.tekir.entities.VoucherSummaryBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author oyas
 */
public class VoucherItemUtils {
    
    protected VoucherItemUtils(){}
    
    /**
     * Verilen bir commodityItembase içeriğini bir diğerine kopyalar.
     * @param source
     * @param target 
     */
    public static void copyCommodityItem( VoucherCommodityItemBase source, VoucherCommodityItemBase target ){
        target.setCommodity(source.getCommodity());
        target.setInfo(source.getInfo());
        target.setPrice(source.getPrice());
        target.setQuantity(source.getQuantity());
        target.setTotal(source.getTotal());
        target.setDiscount(source.getDiscount());
        target.setDiscountRate(source.getDiscountRate());
        target.setLineTotal(source.getLineTotal());
    }
    
    /**
     * Geriye Summuray içinden tax listesini sıralı olarak döndürür.
     * @param <E>
     * @param sums
     * @return 
     */
    public static <E extends VoucherSummaryBase> List<E> getTaxes( Map<String, E> sums ){
        List<E> result = new ArrayList<>();

        sums.entrySet().stream()
                .filter(e -> e.getKey().startsWith("Tax."))
                .forEach(e -> {
                    result.add(e.getValue());
                });
        
        result.sort((E t, E t1) -> t.getRowKey().compareTo(t1.getRowKey()));

        return result;
    }
}
