/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.FinanceAccount;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Finance Account Txn üserinde summary almak için model.
 * 
 * @author Ceyhun Onur
 */
public class FinanceAccountTxnSumModel implements Serializable{
    
	private String accountName;
    private FinanceAccount  account;
    private BigDecimal localBalance;
    private Currency currency;
    private BigDecimal balance;
    private Integer rate;

    public FinanceAccountTxnSumModel(FinanceAccount account, BigDecimal localBalance, BigDecimal balance, Currency currency) {
        this.account = account;
        this.localBalance = localBalance;
        this.currency = currency;
        this.balance = balance;
        this.accountName = account.getName();
    }
    
    public FinanceAccountTxnSumModel(FinanceAccount account, BigDecimal balance, Currency currency){
    	this(account,new BigDecimal(0),balance,currency);
    	
    }
     
    public FinanceAccountTxnSumModel(FinanceAccount account, BigDecimal localBalance) {
    	this(account,localBalance,new BigDecimal(0),null);

    }

    public FinanceAccount getAccount() {
        return account;
    }

    public void setAccountName(FinanceAccount account) {
        this.account = account;
    }    
    
    public BigDecimal getLocalBalance() {
        return localBalance;
    }

    public void setLocalBalance(BigDecimal localBalance) {
        this.localBalance = localBalance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
    
    
    
}
