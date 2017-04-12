/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment.sales;

import com.ozguryazilim.tekir.entities.PaymentReceived;
import com.ozguryazilim.tekir.payment.PaymentBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature=PaymentReceivedFeature.class)
public class PaymentReceivedBrowse extends PaymentBrowseBase<PaymentReceived, PaymentReceivedViewModel>{

    @Inject
    private PaymentReceivedRepository repository;
    
    @Inject
    private PaymentReceivedHome home;
    
    @Override
    public VoucherRepositoryBase<PaymentReceived, PaymentReceivedViewModel> getVoucherRepository() {
        return repository;
    }

	@Override
	public VoucherFormBase<PaymentReceived> getHome() {
		// TODO Auto-generated method stub
		return home;
	}

    
}
