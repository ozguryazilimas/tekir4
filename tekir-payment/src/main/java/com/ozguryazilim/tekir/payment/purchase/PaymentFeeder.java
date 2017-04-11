/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment.purchase;

import com.ozguryazilim.tekir.entities.Payment;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.payment.PaymentFeederBase;
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
public class PaymentFeeder extends PaymentFeederBase<Payment>{

    
    public void listenStateChange(@Observes(during = TransactionPhase.AFTER_COMPLETION) @FeatureQualifier(feauture = PaymentFeature.class) @After VoucherStateChange event) {
        feedFeeder(event);
        feedMatcherService(event);

    }

    public void listenEntityChange(@Observes(during = TransactionPhase.IN_PROGRESS)@EntityQualifier(entity = Payment.class) @After EntityChangeEvent event) {
        feedAccountTxn(event);
    }
    
    @Override
    protected ProcessType getProcessType() {
        return ProcessType.PURCHASE;
    }
    
}
