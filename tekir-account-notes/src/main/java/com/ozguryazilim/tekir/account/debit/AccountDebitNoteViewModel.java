/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.debit;

import com.ozguryazilim.tekir.account.credit.*;
import com.ozguryazilim.tekir.account.AccountNoteViewModel;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.VoucherState;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 *
 * @author oyas
 */
public class AccountDebitNoteViewModel extends AccountNoteViewModel{
    
    public AccountDebitNoteViewModel(Long id, Contact account, Currency currency, BigDecimal amount, String code, String voucherNo, String info, String referenceNo, Date date, String processId, String owner, VoucherState state) {
        super(id, account, currency, amount, code, voucherNo, info, referenceNo, date, processId, owner, state);
    }

    public AccountDebitNoteViewModel(Long id, Long accountId, String accountName, String accountType, Currency currency, BigDecimal amount, String code, String voucherNo, String info, String referenceNo, Date date, String processId, String owner, VoucherState state) {
        super(id, accountId, accountName, accountType, currency, amount, code, voucherNo, info, referenceNo, date, processId, owner, state);
    }
    
}
