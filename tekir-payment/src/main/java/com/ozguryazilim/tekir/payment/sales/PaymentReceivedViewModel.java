package com.ozguryazilim.tekir.payment.sales;

import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.payment.PaymentViewModel;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oyas
 */
public class PaymentReceivedViewModel extends PaymentViewModel{
    
    public PaymentReceivedViewModel(Long id, Process process, Contact account, List<String> tags, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic, FinanceAccount financeAccount, Currency currency, BigDecimal amount) {
        super(id, process, account, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic, financeAccount, currency, amount);
    }

    public PaymentReceivedViewModel(Long id, Long processId, String processNo, Long accountId, String accountName, Class<? extends Contact> accountType, List<String> tags, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, Long groupId, String groupNo, String topic, Long financeId, String financeName, Currency currency, BigDecimal amount) {
        super(id, processId, processNo, accountId, accountName, accountType, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, groupId, groupNo, topic, financeId, financeName, currency, amount);
    }
    
}
