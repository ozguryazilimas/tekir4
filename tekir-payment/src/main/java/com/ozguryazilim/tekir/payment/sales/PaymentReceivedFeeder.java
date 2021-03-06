package com.ozguryazilim.tekir.payment.sales;

import com.ozguryazilim.tekir.entities.PaymentReceived;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.payment.PaymentFeederBase;
import com.ozguryazilim.tekir.voucher.VoucherOwnerChange;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.group.VoucherGroupTxnService;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Feeder
public class PaymentReceivedFeeder extends PaymentFeederBase<PaymentReceived>{
	
	@Inject
	private VoucherGroupTxnService voucherGroupTxnService;

    public void listenStateChange(@Observes(during = TransactionPhase.AFTER_COMPLETION) @FeatureQualifier(feauture = PaymentReceivedFeature.class) @After VoucherStateChange event) {
        feedFeeder(event);
        feedMatcherService(event);

    }
    
    public void listenOwnerChange(@Observes(during = TransactionPhase.AFTER_COMPLETION) @FeatureQualifier(feauture = PaymentReceivedFeature.class) @After VoucherOwnerChange event) {
        feedFeeder(event);
    }

    public void listenEntityChange(@Observes @EntityQualifier(entity = PaymentReceived.class) @After EntityChangeEvent event) {
        feedAccountTxn(event);
    }
    

	public void feed(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = PaymentReceived.class) @After EntityChangeEvent event) {

		if (event.getAction() != EntityChangeAction.DELETE) {
			PaymentReceived entity = (PaymentReceived) event.getEntity();

			FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
			
			if( entity.getGroup()!=null){
				voucherGroupTxnService.saveFeature(voucherPointer, entity.getGroup(), entity.getOwner(), entity.getTopic(),
						entity.getDate(), entity.getState());
			}
		}

		// TODO: Delete edildiğinde de gidip txn'den silme yapılmalı.
	}
    

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.SALES;
    }    
}
