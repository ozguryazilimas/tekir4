/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.process;

import javax.enterprise.inject.Default;

import com.ozguryazilim.tekir.voucher.config.VoucherPages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import com.ozguryazilim.tekir.entities.Process;

/**
 * Process Feature Definition
 * @author Hakan Uygun
 */
@Feature(permission = "process", forEntity = Process.class )
@Page( type = PageType.BROWSE, page = VoucherPages.Voucher.Process.ProcessBrowse.class )
@Page(type = PageType.EDIT, page = VoucherPages.Voucher.Process.ProcessView.class)
@Page(type = PageType.VIEW, page = VoucherPages.Voucher.Process.ProcessView.class)
@Page(type = PageType.MASTER_VIEW, page = VoucherPages.Voucher.Process.ProcessMasterView.class)
@Default
public class ProcessFeature extends AbstractFeatureHandler{
    
}
