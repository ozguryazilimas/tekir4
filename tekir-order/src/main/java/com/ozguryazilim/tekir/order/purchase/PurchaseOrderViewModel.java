/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.purchase;

import com.ozguryazilim.tekir.order.sales.*;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherProcessViewModel;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 *
 * @author oyas
 */
public class PurchaseOrderViewModel extends VoucherProcessViewModel{
   
    private BigDecimal total;
    private Currency currency;
    
    public PurchaseOrderViewModel(Long id, Process process, Contact account, String code, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic, BigDecimal total, Currency currency) {
        super(id, process, account, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
        this.total = total;
        this.currency = currency;
    }

    public PurchaseOrderViewModel(Long id, Long processId, String processNo, Long accountId, String accountName, Class<? extends Contact> accountType, String code, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, Long groupId, String groupNo, String topic, BigDecimal total, Currency currency) {
        super(id, processId, processNo, accountId, accountName, accountType, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, groupId, groupNo, topic);
        this.total = total;
        this.currency = currency;
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
    
    
    
    
}
