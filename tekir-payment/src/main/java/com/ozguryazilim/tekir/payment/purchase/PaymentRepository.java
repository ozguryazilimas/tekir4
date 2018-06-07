/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment.purchase;

import com.ozguryazilim.tekir.entities.Payment;
import com.ozguryazilim.tekir.payment.PaymentRepositoryBase;
import com.ozguryazilim.tekir.payment.PaymentViewModel;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class PaymentRepository extends PaymentRepositoryBase<Payment, PaymentViewModel>{

    @Override
    public Class<Payment> getEntityClass() {
        return Payment.class;
    }

    @Override
    public Class<PaymentViewModel> getViewModelClass() {
        return PaymentViewModel.class;
    }
}
