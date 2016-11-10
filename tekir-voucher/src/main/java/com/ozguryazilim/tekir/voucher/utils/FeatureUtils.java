/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.utils;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.entities.VoucherProcessBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureRegistery;

/**
 * Feature'lar için Voucher Utility Fonksiyonları
 *
 * @author Hakan Uygun
 */
public class FeatureUtils {

    private FeatureUtils() {
    }

    /**
     * Voucher'dan türeyen bir entity için Voucher Pointer üretir.
     * 
     * Feature'da entity sınıfı ( forEntity ) işaretlenmiş olmalıdır.
     * 
     * @param <C> VoucherBase'dan üreyen bir entity sınıfı
     * @param voucher
     * @return 
     */
    public static <C extends VoucherBase> FeaturePointer getFeaturePointer(C voucher) {
        FeaturePointer fp = new FeaturePointer();
        fp.setBusinessKey(voucher.getVoucherNo());
        fp.setPrimaryKey(voucher.getId());
        fp.setFeature(FeatureRegistery.getFeatureClass(voucher.getClass()).getSimpleName());

        return fp;
    }
    
    
    /**
     * Voucher'dan türeyen bir entity için Account Pointer üretir.
     * 
     * Feature'da entity sınıfı ( forEntity ) işaretlenmiş olmalıdır.
     * 
     * @param <C> VoucherBase'dan üreyen bir entity sınıfı
     * @param voucher
     * @return 
     */
    public static <C extends VoucherProcessBase> FeaturePointer getAccountFeaturePointer(C voucher) {
        FeaturePointer fp = new FeaturePointer();
        fp.setBusinessKey(voucher.getAccount().getName());
        fp.setPrimaryKey(voucher.getAccount().getId());
        fp.setFeature(FeatureRegistery.getFeatureClass(voucher.getAccount().getClass()).getSimpleName());

        return fp;
    }
    
    
    /**
     * Voucher'dan türeyen bir entity için Account Pointer üretir.
     * 
     * Feature'da entity sınıfı ( forEntity ) işaretlenmiş olmalıdır.
     * 
     * @param <C> VoucherBase'dan üreyen bir entity sınıfı
     * @param voucher
     * @return 
     */
    public static <C extends Contact> FeaturePointer getAccountFeaturePointer(C account) {
        FeaturePointer fp = new FeaturePointer();
        fp.setBusinessKey(account.getName());
        fp.setPrimaryKey(account.getId());
        fp.setFeature(FeatureRegistery.getFeatureClass(account.getClass()).getSimpleName());

        return fp;
    }

}
