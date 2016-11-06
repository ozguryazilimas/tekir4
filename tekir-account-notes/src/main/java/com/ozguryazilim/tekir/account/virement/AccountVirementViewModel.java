/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.virement;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherViewModel;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 *
 * @author oyas
 */
public class AccountVirementViewModel extends VoucherViewModel{
    
    private Contact fromAccount;
    private Contact toAccount;
    private Currency currency;
    private BigDecimal amount = BigDecimal.ZERO;

    public AccountVirementViewModel(Long id, Contact fromAccount, Contact toAccount, Currency currency, BigDecimal amount,  String code, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic) {
        super(id, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.currency = currency;
        this.amount = amount;
    }

    public Contact getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Contact fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Contact getToAccount() {
        return toAccount;
    }

    public void setToAccount(Contact toAccount) {
        this.toAccount = toAccount;
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
