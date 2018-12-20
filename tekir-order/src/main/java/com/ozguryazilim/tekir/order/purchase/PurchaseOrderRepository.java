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
