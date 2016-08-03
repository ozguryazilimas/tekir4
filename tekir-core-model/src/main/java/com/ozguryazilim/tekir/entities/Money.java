/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Para tipi veriler için veri modeli.
 * 
 * @author Hakan Uygun
 */
@Embeddable
public class Money implements Serializable{
    
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    
    @Column(name = "CCY")
    private String currency;

    public Money(){
        amount = BigDecimal.ZERO;
        currency = "";
    }
    
    public Money(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    public Money(String currency) {
        this.amount = BigDecimal.ZERO;
        this.currency = currency;
    }
    
    public Money(Currency currency) {
        this.amount = BigDecimal.ZERO;
        this.currency = currency.getCurrencyCode();
    }
    
    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency.getCurrencyCode();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public void setCurrency(Currency currency) {
        this.currency = currency.getCurrencyCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Money other = (Money) obj;
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + amount + " " + currency;
    }
    
    
    
}
