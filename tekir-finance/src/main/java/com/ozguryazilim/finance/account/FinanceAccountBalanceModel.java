/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account;

import com.ozguryazilim.telve.entities.FeaturePointer;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * FinanceAccount için balance modeli.
 * 
 * @author Hakan Uygun
 */
public class FinanceAccountBalanceModel {
   
    private FeaturePointer featurePointer;
    private Date date;
    private String topic;
    private BigDecimal amount;
    private BigDecimal balance;
    private Currency ccy;
    private String lineType = "TXN"; //TAKE-OVER ( devir ) ve RESULT ( sonuç ) da olabilir 

    public FeaturePointer getFeaturePointer() {
        return featurePointer;
    }

    public void setFeaturePointer(FeaturePointer featurePointer) {
        this.featurePointer = featurePointer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

	public Currency getCcy() {
		return ccy;
	}

	public void setCcy(Currency ccy) {
		this.ccy = ccy;
	}
    
    
    
}
