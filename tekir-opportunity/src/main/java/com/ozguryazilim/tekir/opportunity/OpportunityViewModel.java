package com.ozguryazilim.tekir.opportunity;


import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Process;
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
public class OpportunityViewModel extends VoucherProcessViewModel{

    private BigDecimal budget;
    private Currency currency;
    
    public OpportunityViewModel(Long id, Process process, Contact account, List<String> tags, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic, BigDecimal budget, Currency currency) {
        super(id, process, account, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
        this.budget = budget;
        this.currency = currency;
    }
    
    public OpportunityViewModel(Long id, Long processId, String processNo, Long accountId, String accountName, Class<? extends Contact> accountType, List<String> tags, String voucherNo, String info, String referenceNo, Date date, String owner, VoucherState state, String stateReason, String stateInfo, Long groupId, String groupNo, String topic, BigDecimal budget, Currency currency) {
        super(id, processId, processNo, accountId, accountName, accountType, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, groupId, groupNo, topic);
        this.budget = budget;
        this.currency = currency;
    }
   
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    
    
}
