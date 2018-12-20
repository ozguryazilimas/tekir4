package com.ozguryazilim.tekir.invoice.sales;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.invoice.InvoiceViewModel;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oyas
 */
public class SalesInvoiceViewModel extends InvoiceViewModel{

    public SalesInvoiceViewModel(Long id, Process process, Contact account, List<String> tags, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic, Date time, BigDecimal total, Currency currency) {
        super(id, process, account, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic, time, total, currency);
    }

    public SalesInvoiceViewModel(Long id, Long processId, String processNo, Long accountId, String accountName, Class<? extends Contact> accountType, List<String> tags, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, Long groupId, String groupNo, String topic, Date time, BigDecimal total, Currency currency) {
        super(id, processId, processNo, accountId, accountName, accountType, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, groupId, groupNo, topic, time, total, currency);
    }
    
}
