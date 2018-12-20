package com.ozguryazilim.tekir.voucher;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 * Voucher'lar arasında stateChange'e göre redirect'ion handler interface'i
 * 
 * @author Hakan Uygun
 */
public interface VoucherRedirectHandler {
   
    /**
     * Redirect etmek istenmiyorsa geriye null döndürülecek.
     * 
     * @param event
     * @return 
     */
    Class<? extends ViewConfig> redirect( VoucherStateChange event );
}
