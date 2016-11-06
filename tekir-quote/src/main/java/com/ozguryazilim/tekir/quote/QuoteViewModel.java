package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherProcessViewModel;
import java.util.Date;

/**
 * View Model Class
 *
 * @author
 */
public class QuoteViewModel extends VoucherProcessViewModel{

    public QuoteViewModel(Long id, Process process, Contact account, String code, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic) {
        super(id, process, account, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
    }

    
}
