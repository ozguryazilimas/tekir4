/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment.sales;

import com.ozguryazilim.tekir.entities.BankCashAccount;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.payment.PaymentViewModel;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 *
 * @author oyas
 */
public class PaymentReceivedViewModel extends PaymentViewModel{
    
    public PaymentReceivedViewModel(Long id, Process process, Contact account, String code, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic, BankCashAccount bankCashAccount, Currency currency, BigDecimal amount) {
        super(id, process, account, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic, bankCashAccount, currency, amount);
    }

    public PaymentReceivedViewModel(Long id, Long processId, String processNo, Long accountId, String accountName, Class<? extends Contact> accountType, String code, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, Long groupId, String groupNo, String topic, Long bankCashId, String bankCashName, Currency currency, BigDecimal amount) {
        super(id, processId, processNo, accountId, accountName, accountType, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, groupId, groupNo, topic, bankCashId, bankCashName, currency, amount);
    }
    
}
