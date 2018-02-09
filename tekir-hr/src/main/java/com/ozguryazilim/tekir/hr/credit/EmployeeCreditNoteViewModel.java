package com.ozguryazilim.tekir.hr.credit;

import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.EmployeeCreditNoteType;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherViewModel;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 *
 * @author Erdem Uslu
 */
public class EmployeeCreditNoteViewModel extends VoucherViewModel {
    
    private Employee employee;
    private EmployeeCreditNoteType type;
    private FinanceAccount financeAccount;
    private Date paymentDate;
    private Currency currency;
    private BigDecimal amount = BigDecimal.ZERO;

    public EmployeeCreditNoteViewModel(Long id, String code, String voucherNo, String info, 
            String referenceNo, Date date, String owner, 
            VoucherState state, String stateReason, String stateInfo, VoucherGroup group,String topic,
            Employee employee, EmployeeCreditNoteType type, FinanceAccount financeAccount, Date paymentDate, Currency currency, BigDecimal amount) {
        super(id, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
        
        this.employee = employee;
        this.type = type;
        this.financeAccount = financeAccount;
        this.paymentDate = paymentDate;
        this.currency = currency;
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeCreditNoteType getType() {
        return type;
    }

    public void setType(EmployeeCreditNoteType type) {
        this.type = type;
    }

    public FinanceAccount getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(FinanceAccount financeAccount) {
        this.financeAccount = financeAccount;
    }
    
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
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
