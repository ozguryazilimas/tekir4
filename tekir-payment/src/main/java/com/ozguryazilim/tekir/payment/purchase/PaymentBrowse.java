package com.ozguryazilim.tekir.payment.purchase;

import com.ozguryazilim.tekir.entities.Payment;
import com.ozguryazilim.tekir.payment.PaymentBrowseBase;
import com.ozguryazilim.tekir.payment.PaymentViewModel;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
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
    
    @Inject
    private PaymentHome home;
    
    @Override
    public VoucherRepositoryBase<Payment, PaymentViewModel> getVoucherRepository() {
        return repository;
    }

	@Override
	public VoucherFormBase<Payment> getHome() {
		// TODO Auto-generated method stub
		return home;
	}

    
}
