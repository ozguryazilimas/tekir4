package com.ozguryazilim.tekir.order.sales;

import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.SalesOrder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.order.OrderFeeder;
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
public class SalesOrderFeeder extends OrderFeeder<SalesOrder>{
    
    public void listenStateChange(@Observes(during = TransactionPhase.AFTER_COMPLETION) @FeatureQualifier(feauture = SalesOrderFeature.class) @After VoucherStateChange event) {
        feedFeeder(event);
        feedMatcherService(event);
    }
    
    public void listenEntityChange(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = SalesOrder.class) @After EntityChangeEvent event) {
        feedAccountTxn(event);
    }


    @Override
    protected ProcessType getProcessType() {
        return ProcessType.SALES;
    }
    
}
