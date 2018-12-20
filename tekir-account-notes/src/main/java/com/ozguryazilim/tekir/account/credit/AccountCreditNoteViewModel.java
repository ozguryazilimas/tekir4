package com.ozguryazilim.tekir.account.credit;

import com.ozguryazilim.tekir.account.AccountNoteViewModel;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oyas
 */
public class AccountCreditNoteViewModel extends AccountNoteViewModel{
    
    public AccountCreditNoteViewModel(Long id, List<String> tags, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic, Contact account, Currency currency, BigDecimal amount) {
        super(id, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic, account, currency, amount);
    }
    
    
}
