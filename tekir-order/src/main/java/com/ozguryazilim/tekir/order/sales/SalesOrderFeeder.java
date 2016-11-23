/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author oyas
 */
@Feeder
public class SalesOrderFeeder extends OrderFeeder<SalesOrder>{
    
    public void listenStateChange(@Observes @FeatureQualifier(feauture = SalesOrderFeature.class) @After VoucherStateChange event) {
        feedFeeder(event);
        feedMatcherService(event);
    }
    
    public void listenEntityChange(@Observes @EntityQualifier(entity = SalesOrder.class) @After EntityChangeEvent event) {
        feedAccountTxn(event);
    }


    @Override
    protected ProcessType getProcessType() {
        return ProcessType.SALES;
    }
    
}
