package com.ozguryazilim.tekir.order.sales;

import com.ozguryazilim.tekir.entities.SalesOrder;
import com.ozguryazilim.tekir.order.OrderRepository;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class SalesOrderRepository extends OrderRepository<SalesOrder, SalesOrderViewModel>{
    
    @Override
    public Class<SalesOrder> getEntityClass() {
        return SalesOrder.class;
    }

    @Override
    public Class<SalesOrderViewModel> getViewModelClass() {
        return SalesOrderViewModel.class;
    }
}
