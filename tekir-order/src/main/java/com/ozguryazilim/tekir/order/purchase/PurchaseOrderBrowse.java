package com.ozguryazilim.tekir.order.purchase;

import com.ozguryazilim.tekir.entities.PurchaseOrder;
import com.ozguryazilim.tekir.order.OrderBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature=PurchaseOrderFeature.class )
public class PurchaseOrderBrowse extends OrderBrowseBase<PurchaseOrder, PurchaseOrderViewModel> {

    @Inject
    private PurchaseOrderRepository repository;
    
    @Inject
    private PurchaseOrderHome home;
    
    @Override
    public VoucherRepositoryBase<PurchaseOrder, PurchaseOrderViewModel> getVoucherRepository() {
        return repository;
    }

	@Override
	public VoucherFormBase<PurchaseOrder> getHome() {
		// TODO Auto-generated method stub
		return home;
	}

    @Override
    public String getTagKey() {
        return home.getTagKey();
    }

}
