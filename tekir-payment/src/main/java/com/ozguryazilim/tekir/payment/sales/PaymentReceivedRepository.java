package com.ozguryazilim.tekir.payment.sales;

import com.ozguryazilim.tekir.entities.PaymentReceived;
import com.ozguryazilim.tekir.payment.PaymentRepositoryBase;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class PaymentReceivedRepository extends PaymentRepositoryBase<PaymentReceived, PaymentReceivedViewModel>{

    @Override
    public Class<PaymentReceived> getEntityClass() {
        return PaymentReceived.class;
    }

    @Override
    public Class<PaymentReceivedViewModel> getViewModelClass() {
        return PaymentReceivedViewModel.class;
    }
    
}
