/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.math.BigDecimal;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Cari Virman Fişi
 * 
 * @author Hakan Uygun
 */
@Entity
@Table(name = "TAN_VIREMENT")
public class AccountVirement extends VoucherBase{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    
    /**
     * Hangi müşteri için
     */
    @ManyToOne
    @JoinColumn(name = "FROM_ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_AV_FACC"))
    private Contact fromAccount;
    
    @ManyToOne
    @JoinColumn(name = "TO_ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_AV_TACC"))
    private Contact toAccount;
    
    @Column(name = "CCY")
    private Currency currency;
    
    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;
    
    @Column(name = "LOCAL_AMOUNT")
    private BigDecimal localAmount = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Contact fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Contact getToAccount() {
        return toAccount;
    }

    public void setToAccount(Contact toAccount) {
        this.toAccount = toAccount;
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

    public BigDecimal getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(BigDecimal localAmount) {
        this.localAmount = localAmount;
    }

    
    
}
