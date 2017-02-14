/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.process;

import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.tekir.voucher.config.VoucherPages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import javax.enterprise.inject.Default;

/**
 * Quote Feature Definition
 * @author Hakan Uygun
 */
@Feature(permission = "feature", forEntity = Feature.class )
@Page( type = PageType.BROWSE, page = VoucherPages.ProcessBrowse.class )

//@Page( type = PageType.VIEW, page = VoucherPages.ProcessView.class )
//@Page( type = PageType.MASTER_VIEW, page = VoucherPages.ProcessMasterView.class )
@Voucher @Default
public class ProcessFeature extends AbstractFeatureHandler{
    
}
