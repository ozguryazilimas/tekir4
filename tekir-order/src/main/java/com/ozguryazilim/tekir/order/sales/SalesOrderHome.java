package com.ozguryazilim.tekir.order.sales;

import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.SalesOrder;
import com.ozguryazilim.tekir.order.OrderHomeBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@FormEdit(feature = SalesOrderFeature.class)
public class SalesOrderHome extends OrderHomeBase<SalesOrder> {

    private final static String tagKey = "SalesOrder";

    @Inject
    private SalesOrderRepository repository;

    @Override
    protected RepositoryBase<SalesOrder, ?> getRepository() {
        return repository;
    }

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.SALES;
    }
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase contact){
    		return FeatureUtils.getFeaturePointer(contact);
    }

    public String getTagKey() {
        return tagKey;
    }

}
