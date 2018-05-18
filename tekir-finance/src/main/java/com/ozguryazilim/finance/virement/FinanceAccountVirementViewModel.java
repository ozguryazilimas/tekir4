/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.virement;

import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.VirementType;
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
public class FinanceAccountVirementViewModel extends VoucherViewModel{
    
    private FinanceAccount fromAccount;
    private FinanceAccount toAccount;
    private Currency fromCurrency;
    private Currency toCurrency;
    private BigDecimal fromAmount = BigDecimal.ZERO;
    private BigDecimal toAmount = BigDecimal.ZERO;
    private VirementType virementType = VirementType.VIREMENT;

    public FinanceAccountVirementViewModel(Long id, VirementType virementType, FinanceAccount fromAccount,
										   FinanceAccount toAccount, Currency fromCurrency, Currency toCurrency,
										   BigDecimal fromAmount, BigDecimal toAmount, List<String> tags,
										   String voucherNo, String info, String referenceNo, Date date, String owner,
										   VoucherState state, String stateReason, String stateInfo, VoucherGroup group,
										   String topic) {
        super(id, tags, voucherNo, info, referenceNo, date, owner, state, stateReason, stateInfo, group, topic);
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
        this.virementType = virementType;
        
    }

    public FinanceAccount getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(FinanceAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    public FinanceAccount getToAccount() {
        return toAccount;
    }

    public void setToAccount(FinanceAccount toAccount) {
        this.toAccount = toAccount;
    }

	public Currency getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(Currency fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public Currency getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(Currency toCurrency) {
		this.toCurrency = toCurrency;
	}

	public BigDecimal getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}

	public BigDecimal getToAmount() {
		return toAmount;
	}

	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
	}

	public VirementType getVirementType() {
		return virementType;
	}

	public void setVirementType(VirementType virementType) {
		this.virementType = virementType;		
	}

   
    
    
}
