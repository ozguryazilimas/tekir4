package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherCommodityItemBase;
import com.ozguryazilim.tekir.entities.VoucherSummaryBase;
import java.util.List;

/**
 * VoucherCommodityItemEditor çağıran sınıfların geri dönüşü dinlemek için kullanacakları callback arayüzü.
 * 
 * @author Hakan Uygun
 */
public interface VoucherCommodityItemEditorListener<E extends VoucherCommodityItemBase>{

    /**
     * Editor için yeni bir satır ekleyip editorü açar.
     */
    public void addItem();
    
    public void editItem(E item);
    
    public void removeItem(E item);
    
    /**
     * Editor sonucu editlenen değer.
     * 
     * @param item 
     */
    void saveItem( E item );
    
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
