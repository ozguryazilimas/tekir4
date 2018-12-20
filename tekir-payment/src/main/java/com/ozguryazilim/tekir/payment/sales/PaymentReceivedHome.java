package com.ozguryazilim.tekir.payment.sales;

import com.ozguryazilim.tekir.entities.PaymentReceived;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.payment.PaymentHomeBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@FormEdit(feature = PaymentReceivedFeature.class)
public class PaymentReceivedHome extends PaymentHomeBase<PaymentReceived>{

    @Inject
    private PaymentReceivedRepository repository;
    
    @Override
    protected RepositoryBase<PaymentReceived, ?> getRepository() {
        return repository;
    }

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.SALES;
    }
    
}
