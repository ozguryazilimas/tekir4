/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment.purchase;

import com.ozguryazilim.tekir.entities.Payment;
import com.ozguryazilim.tekir.payment.config.PaymentPages;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.FeatureCategory;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import com.ozguryazilim.telve.feature.search.Search;

import javax.enterprise.inject.Default;

/**
 *
 * @author oyas
 */
@Feature(permission = "payment", forEntity = Payment.class, category = {FeatureCategory.ACCOUNTABLE} )
@Page( type = PageType.BROWSE, page = PaymentPages.Purchase.PaymentBrowse.class )
@Page( type = PageType.VIEW, page = PaymentPages.Purchase.PaymentView.class )
@Page( type = PageType.MASTER_VIEW, page = PaymentPages.Purchase.PaymentMasterView.class )
@Page( type = PageType.EDIT, page = PaymentPages.Purchase.Payment.class )
@Search(handler = PaymentSearchHandler.class )
@Voucher @Default
public class PaymentFeature extends AbstractFeatureHandler{
    
}
