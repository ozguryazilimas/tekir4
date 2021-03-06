package com.ozguryazilim.tekir.order.sales;

import com.ozguryazilim.tekir.entities.SalesOrder;
import com.ozguryazilim.tekir.order.config.OrderPages;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import javax.enterprise.inject.Default;

/**
 *
 * @author oyas
 */
@Feature(permission = "salesOrder", forEntity = SalesOrder.class )
@Page( type = PageType.BROWSE, page = OrderPages.Sales.SalesOrderBrowse.class )
@Page( type = PageType.VIEW, page = OrderPages.Sales.SalesOrderView.class )
@Page( type = PageType.MASTER_VIEW, page = OrderPages.Sales.SalesOrderMasterView.class )
@Page( type = PageType.EDIT, page = OrderPages.Sales.SalesOrder.class )
@Voucher @Default
public class SalesOrderFeature extends AbstractFeatureHandler{
    
}
