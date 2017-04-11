/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.credit;

import com.ozguryazilim.tekir.account.AccountNoteViewModel;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 *
 * @author oyas
 */
public class AccountDebitNote extends AccountNoteViewModel{
    
    public AccountCreditNoteViewModel(Long id, String code, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic, Contact account, Currency currency, BigDecimal amount) {
        super(id, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic, account, currency, amount);
    }
    
    
}
