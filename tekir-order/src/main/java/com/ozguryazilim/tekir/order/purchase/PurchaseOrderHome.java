package com.ozguryazilim.tekir.order.purchase;

import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.PurchaseOrder;
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
@FormEdit(feature = PurchaseOrderFeature.class)
public class PurchaseOrderHome extends OrderHomeBase<PurchaseOrder> {

    private final static String tagKey = "PurchaseOrder";

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
    // FeatureLink yönlendirmesi
    public FeaturePointer getAllFeaturePointer(EntityBase contact){
    		return FeatureUtils.getFeaturePointer(contact);
    }

    public String getTagKey() {
        return tagKey;
    }
}
