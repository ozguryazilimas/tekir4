/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.purchase;

import com.ozguryazilim.tekir.entities.PurchaseOrder;
import com.ozguryazilim.tekir.order.OrderRepository;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class PurchaseOrderRepository extends OrderRepository<PurchaseOrder, PurchaseOrderViewModel>{

    @Override
    public Class<PurchaseOrder> getEntityClass() {
        return PurchaseOrder.class;
    }

    @Override
    public Class<PurchaseOrderViewModel> getViewModelClass() {
        return PurchaseOrderViewModel.class;
    }
    
}
