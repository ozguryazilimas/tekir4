/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account;

import com.ozguryazilim.tekir.entities.Contact;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * Cari Hesap durum bilgi model sınıfı.
 * 
 * Bir cari hesap için borç/alacak/bakiye gibi özet bilgileri tutar.
 * 
 * @see AccountStatusReport içerisinde kullanılır.
 * 
 * @author Hakan Uygun
 */
public class AccountTxnStatusModel implements Serializable{
    
    private Long accountId;
    private String  accountName;
    private Class<? extends Contact> contactClass;
    private BigDecimal credit;
    private BigDecimal debit;
    private BigDecimal balance;
    private Currency currency;

    public AccountTxnStatusModel(Long accountId, String accountName, Class<? extends Contact> contactClass, BigDecimal credit, BigDecimal debit, Currency currency) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.contactClass = contactClass;
        this.credit = credit;
        this.debit = debit;
        this.currency = currency;
        //Borçtan alacak çıkar ve bakiye olur
        this.balance = credit.subtract(debit);
    }
    
    public AccountTxnStatusModel(Long accountId, String accountName, BigDecimal credit, BigDecimal debit ) {
        this.accountId = accountId;
        this.accountName = accountName;
        //this.contactClass = contactClass;
        this.credit = credit;
        this.debit = debit;
        //FIXME:Eğer null ise Local_CCY olacak. Ama constructor'da nasıl olacak?
        this.currency = null; 
        //Borçtan alacak çıkar ve bakiye olur
        this.balance = credit.subtract(debit);
    }

    
    
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Class<? extends Contact> getContactClass() {
        return contactClass;
    }

    public void setContactClass(Class<? extends Contact> contactClass) {
        this.contactClass = contactClass;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.accountId);
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
        final AccountTxnStatusModel other = (AccountTxnStatusModel) obj;
        if (!Objects.equals(this.accountId, other.accountId)) {
            return false;
        }
        return true;
    }
    
    
    
}
