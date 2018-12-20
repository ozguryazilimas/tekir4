package com.ozguryazilim.tekir.voucher.group;

import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.tekir.voucher.config.VoucherPages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import javax.enterprise.inject.Default;

/**
 * Voucher Group Feature Definition
 * @author Hakan Uygun
 */

@Feature(permission = "voucherGroup", forEntity = VoucherGroup.class )
@Page(type = PageType.BROWSE, page = VoucherPages.Group.VoucherGroupBrowse.class )
@Page(type = PageType.VIEW, page = VoucherPages.Group.VoucherGroupView.class)
@Page(type = PageType.MASTER_VIEW, page = VoucherPages.Group.VoucherGroupMasterView.class)
@Page(type = PageType.EDIT, page = VoucherPages.Group.VoucherGroup.class )
@Default
public class VoucherGroupFeature extends AbstractFeatureHandler{
    
}
