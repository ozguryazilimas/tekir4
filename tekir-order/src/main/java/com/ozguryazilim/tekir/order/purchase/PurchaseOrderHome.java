/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.purchase;

import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.PurchaseOrder;
import com.ozguryazilim.tekir.order.OrderHomeBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@FormEdit(feature = PurchaseOrderFeature.class)
public class PurchaseOrderHome extends OrderHomeBase<PurchaseOrder> {

    @Inject
    private PurchaseOrderRepository repository;

    @Override
    protected RepositoryBase<PurchaseOrder, ?> getRepository() {
        return repository;
    }

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.PURCHASE;
    }

}
