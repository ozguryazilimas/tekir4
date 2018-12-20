package com.ozguryazilim.tekir.voucher;

import java.util.List;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 *
 * @author oyas
 */
public class VoucherPrintOutAction extends VoucherStateAction{

    private final VoucherFormBase voucherBean;

    private List<String> subactionNames;
            
    public VoucherPrintOutAction( VoucherFormBase voucherBean ) {
        super("print", "fa fa-print", Boolean.TRUE, Boolean.FALSE);
        this.voucherBean = voucherBean;
        this.subactionNames = voucherBean.getPrintOutNames();
    }

    @Override
    public Class<? extends ViewConfig> execute() {
        voucherBean.printOut( null );
        return null;
    }

    @Override
    public Class<? extends ViewConfig> execute(String param) {
        voucherBean.printOut( param );
        return null;
    }

    @Override
    public Boolean getHasSubactions() {
        return subactionNames.size() > 1;
    }
    
    @Override
    public List<String> getSubactionNames() {
        return subactionNames;
    }
    
}
