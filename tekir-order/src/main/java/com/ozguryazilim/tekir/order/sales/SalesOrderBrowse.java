package com.ozguryazilim.tekir.order.sales;

import com.ozguryazilim.tekir.entities.SalesOrder;
import com.ozguryazilim.tekir.order.OrderBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.forms.Browse;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Browse( feature=SalesOrderFeature.class )
public class SalesOrderBrowse extends OrderBrowseBase<SalesOrder, SalesOrderViewModel> {

    @Inject
    private SalesOrderRepository repository;
    
    @Inject
    private SalesOrderHome home;
    
    @Override
    public VoucherRepositoryBase<SalesOrder, SalesOrderViewModel> getVoucherRepository() {
        return repository;
    }

	@Override
	public VoucherFormBase<SalesOrder> getHome() {
		// TODO Auto-generated method stub
		return home;
	}

    @Override
    public String getTagKey() {
        return home.getTagKey();
    }

}
