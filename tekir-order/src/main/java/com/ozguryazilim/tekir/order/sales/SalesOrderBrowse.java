/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.sales;

import com.ozguryazilim.tekir.entities.SalesOrder;
import com.ozguryazilim.tekir.order.OrderBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature=SalesOrderFeature.class )
public class SalesOrderBrowse extends OrderBrowseBase<SalesOrder, SalesOrderViewModel> {

    @Inject
    private SalesOrderRepository repository;
    
    @Override
    public VoucherRepositoryBase<SalesOrder, SalesOrderViewModel> getVoucherRepository() {
        return repository;
    }
    
}
