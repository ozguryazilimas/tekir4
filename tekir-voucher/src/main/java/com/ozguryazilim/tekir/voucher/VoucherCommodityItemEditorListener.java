/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherCommodityItemBase;
import com.ozguryazilim.tekir.entities.VoucherSummaryBase;
import java.util.List;

/**
 * VoucherCommodityItemEditor çağıran sınıfların geri dönüşü dinlemek için kullanacakları callback arayüzü.
 * 
 * @author Hakan Uygun
 */
public interface VoucherCommodityItemEditorListener {

    /**
     * Editor için yeni bir satır ekleyip editorü açar.
     */
    public void addItem();
    
    /**
     * Editor sonucu editlenen değer.
     * 
     * @param item 
     */
    void saveItem( VoucherCommodityItemBase item );
    
    /**
     * Geriye sadece vergi itemlarının listesini döndürür.
     * @return 
     */
    public List<? extends VoucherSummaryBase> getTaxes();
    
    /**
     * Detaylar üzerinde vergi ve toplamları hesaplar.
     */
    public void calculateSummaries();
}
