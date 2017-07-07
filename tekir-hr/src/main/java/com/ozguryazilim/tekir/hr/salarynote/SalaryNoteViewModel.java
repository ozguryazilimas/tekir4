/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.salarynote;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherViewModel;

/**
 *SalaryNote View Model.
 * 
 * @author oktay
 *
 */
public class SalaryNoteViewModel extends VoucherViewModel{
	
	private FinanceAccount financeAccount;
	private Date paymentDate;
	private Currency currency;
	private BigDecimal total;
	

	public SalaryNoteViewModel(Long id, String code, String voucherNo, String info, String referenceNo, Date date,
			String owner, VoucherState state, String stateReason, String stateInfo, VoucherGroup group, String topic,
			FinanceAccount financeAccount, Date paymentDate, Currency currency, BigDecimal total) {
		super(id, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
		this.financeAccount = financeAccount;
		this.paymentDate = paymentDate;
		this.currency = currency;
		this.total = total;
	}

	public SalaryNoteViewModel(Long id, String code, String voucherNo, String info, String referenceNo,Date date,
			String owner, VoucherState state, String stateReason, String stateInfo, Long groupId,
			String groupNo, String topic,Long financeAccountId,String financeAccountName, 
			Date paymentDate, Currency currency, BigDecimal total) {
		super(id, code, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, groupId, groupNo, topic);
		
		
		this.financeAccount = new FinanceAccount();
		this.financeAccount.setId(financeAccountId);
		this.financeAccount.setName(financeAccountName);
		
		this.paymentDate = paymentDate;
		this.currency = currency;
		this.total = total;
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
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
