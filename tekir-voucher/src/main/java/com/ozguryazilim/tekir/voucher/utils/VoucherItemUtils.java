/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.utils;

import com.ozguryazilim.tekir.entities.VoucherCommodityItemBase;

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
}
