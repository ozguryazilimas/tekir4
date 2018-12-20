package com.ozguryazilim.tekir.order.purchase;

import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.PurchaseOrder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.order.OrderFeeder;
import com.ozguryazilim.tekir.voucher.VoucherOwnerChange;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

/**
 *
 * @author oyas
 */
@Feeder
public class PurchaseOrderFeeder extends OrderFeeder<PurchaseOrder>{
    
    public void listenStateChange(@Observes(during = TransactionPhase.AFTER_COMPLETION) @FeatureQualifier(feauture = PurchaseOrderFeature.class) @After VoucherStateChange event) {
        feedFeeder(event);
        feedMatcherService(event);
    }
    
    public void listenOwnerChange(@Observes(during = TransactionPhase.AFTER_COMPLETION) @FeatureQualifier(feauture = PurchaseOrderFeature.class) @After VoucherOwnerChange event) {
        feedFeeder(event);
    }
    
    public void listenEntityChange(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = PurchaseOrder.class) @After EntityChangeEvent event) {
        feedAccountTxn(event);
    }

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.PURCHASE;
    }
    
}
