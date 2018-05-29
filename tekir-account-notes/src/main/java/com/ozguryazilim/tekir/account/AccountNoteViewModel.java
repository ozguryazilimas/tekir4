/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherViewModel;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oyas
 */
public class AccountNoteViewModel extends VoucherViewModel{
    
    
    private Contact account;
    private Currency currency;
    private BigDecimal amount = BigDecimal.ZERO;

    public AccountNoteViewModel(Long id, List<String> tags, String voucherNo, String info,
                                String referenceNo, Date date, String owner,
                                VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic,
                                Contact account, Currency currency, BigDecimal amount) {
        super(id, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
        
        this.account = account;
        this.currency = currency;
        this.amount = amount;
    }

    public Contact getAccount() {
        return account;
    }

    public void setAccount(Contact account) {
        this.account = account;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
