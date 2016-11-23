/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.purchase;

import com.ozguryazilim.tekir.entities.PurchaseOrder;
import com.ozguryazilim.tekir.order.OrderBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature=PurchaseOrderFeature.class )
public class PurchaseOrderBrowse extends OrderBrowseBase<PurchaseOrder, PurchaseOrderViewModel> {

    @Inject
    private PurchaseOrderRepository repository;
    
    @Override
    public VoucherRepositoryBase<PurchaseOrder, PurchaseOrderViewModel> getVoucherRepository() {
        return repository;
    }
    
}
