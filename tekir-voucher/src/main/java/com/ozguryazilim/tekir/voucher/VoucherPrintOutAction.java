/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 *
 * @author oyas
 */
public class VoucherPrintOutAction extends VoucherStateAction{

    private final VoucherFormBase voucherBean;

    public VoucherPrintOutAction( VoucherFormBase voucherBean ) {
        super("print", "fa fa-print");
        this.voucherBean = voucherBean;
    }

    @Override
    public Class<? extends ViewConfig> execute() {
        voucherBean.printOut();
        return null;
    }
    
}
