/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherProcessViewModel;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;

/**
 *
 * @author oyas
 */
public class InvoiceViewModel extends VoucherProcessViewModel{
    
    private BigDecimal total;
    private Currency currency;
    private Date time;
    
    public InvoiceViewModel(Long id, com.ozguryazilim.tekir.entities.Process process, Contact account, List<String> tags, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic, Date time, BigDecimal total, Currency currency) {
        super(id, process, account, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
        this.total = total;
        this.currency = currency;
        this.time = time;
    }

    public InvoiceViewModel(Long id, Long processId, String processNo, Long accountId, String accountName, Class<? extends Contact> accountType, List<String> tags, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, Long groupId, String groupNo, String topic, Date time, BigDecimal total, Currency currency) {
        super(id, processId, processNo, accountId, accountName, accountType, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, groupId, groupNo, topic);
        this.total = total;
        this.currency = currency;
        this.time = time;
    }

    
    
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
