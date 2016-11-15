/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.sales;

import com.ozguryazilim.tekir.entities.SalesOrder;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature=SalesOrderFeature.class )
public class SalesOrderBrowse extends VoucherBrowseBase<SalesOrder, SalesOrderViewModel> {

    @Inject
    private SalesOrderRepository repository;
    
    @Override
    public VoucherRepositoryBase<SalesOrder, SalesOrderViewModel> getVoucherRepository() {
        return repository;
    }

    @Override
    protected void buildQueryDefinition(QueryDefinition<SalesOrder, SalesOrderViewModel> queryDefinition) {
        queryDefinition
                .addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true);
    }
    
}
