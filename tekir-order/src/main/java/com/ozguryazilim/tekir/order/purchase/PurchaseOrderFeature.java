/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.purchase;

import com.ozguryazilim.tekir.entities.PurchaseOrder;
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
@Feature(permission = "purchaseOrder", forEntity = PurchaseOrder.class )
@Page( type = PageType.BROWSE, page = OrderPages.Purchase.PurchaseOrderBrowse.class )
@Page( type = PageType.VIEW, page = OrderPages.Purchase.PurchaseOrderView.class )
@Page( type = PageType.MASTER_VIEW, page = OrderPages.Purchase.PurchaseOrderMasterView.class )
@Page( type = PageType.EDIT, page = OrderPages.Purchase.PurchaseOrder.class )
@Voucher @Default
public class PurchaseOrderFeature extends AbstractFeatureHandler{
    
}
