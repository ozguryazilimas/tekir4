/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherCommodityItemBase;

/**
 * VoucherCommodityItemEditor çağıran sınıfların geri dönüşü dinlemek için kullanacakları callback arayüzü.
 * 
 * @author Hakan Uygun
 */
public interface VoucherCommodityItemEditorListener {
    
    /**
     * Editor sonucu editlenen değer.
     * 
     * @param item 
     */
    void saveItem( VoucherCommodityItemBase item );
    
}
