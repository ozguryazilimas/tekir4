/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment.sales;

import com.ozguryazilim.tekir.entities.PaymentReceived;
import com.ozguryazilim.tekir.payment.config.PaymentPages;
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
@Feature(permission = "paymentReceived", forEntity = PaymentReceived.class )
@Page( type = PageType.BROWSE, page = PaymentPages.Sales.PaymentReceivedBrowse.class )
@Page( type = PageType.VIEW, page = PaymentPages.Sales.PaymentReceivedView.class )
@Page( type = PageType.MASTER_VIEW, page = PaymentPages.Sales.PaymentReceivedMasterView.class )
@Page( type = PageType.EDIT, page = PaymentPages.Sales.PaymentReceived.class )
@Voucher @Default
public class PaymentReceivedFeature extends AbstractFeatureHandler{
    
}
