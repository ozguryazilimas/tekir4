package com.ozguryazilim.finance.account;


import com.ozguryazilim.tekir.entities.FinanceAccount;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * Finans Hesabı durum bilgi model sınıfı.
 *
 * Bir finans hesabı için borç/alacak/bakiye gibi özet bilgileri tutar.
 *
 * @see FinancialStatusReport içerisinde kullanılır.
 *
 */
public class FinanceAccountTxnStatusModel implements Serializable {

    private Long accountId;
    private String  accountName;
    private Class<? extends FinanceAccount> accountClass;
    private String code;
    private BigDecimal credit;
    private BigDecimal debit;
    private BigDecimal balance;
    private Currency currency;

    public FinanceAccountTxnStatusModel(Long accountId, String accountName,
                                        Class<? extends FinanceAccount> accountClass,
                                        BigDecimal credit, BigDecimal debit, Currency currency) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountClass = accountClass;
        this.credit = credit;
        this.debit = debit;
        this.currency = currency;
        //Borçtan alacak çıkar ve bakiye olur
        this.balance = credit.subtract(debit);
    }

    public FinanceAccountTxnStatusModel(Long accountId, String accountName,
                                        String code, BigDecimal credit,
                                        BigDecimal debit ) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.code = code;
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

    public Class<? extends FinanceAccount> getContactClass() {
        return accountClass;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setContactClass(Class<? extends FinanceAccount> contactClass) {
        this.accountClass = contactClass;
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
        final FinanceAccountTxnStatusModel other = (FinanceAccountTxnStatusModel) obj;
        if (!Objects.equals(this.accountId, other.accountId)) {
            return false;
        }
        return true;
    }



}
