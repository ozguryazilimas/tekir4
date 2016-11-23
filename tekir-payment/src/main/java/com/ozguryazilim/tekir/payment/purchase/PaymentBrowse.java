/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment.purchase;

import com.ozguryazilim.tekir.entities.Payment;
import com.ozguryazilim.tekir.payment.PaymentBrowseBase;
import com.ozguryazilim.tekir.payment.PaymentViewModel;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature=PaymentFeature.class)
public class PaymentBrowse extends PaymentBrowseBase<Payment, PaymentViewModel>{
    
    @Inject
    private PaymentRepository repository;
    
    @Override
    public VoucherRepositoryBase<Payment, PaymentViewModel> getVoucherRepository() {
        return repository;
    }

    
}
