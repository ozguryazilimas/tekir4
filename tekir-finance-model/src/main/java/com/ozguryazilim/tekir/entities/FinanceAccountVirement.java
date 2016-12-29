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
@Table(name = "TFN_FINANCE_VIREMENT")
public class FinanceAccountVirement extends VoucherBase{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    
    /**
     * Hangi müşteri için
     */
    @ManyToOne
    @JoinColumn(name = "FROM_ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_FAV_FACC"))
    private FinanceAccount fromAccount;
    
    @ManyToOne
    @JoinColumn(name = "TO_ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_FAV_TACC"))
    private FinanceAccount toAccount;
    
    @Column(name = "FROM_CCY")
    private Currency fromCurrency;
    
    @Column(name = "TO_CCY")
    private Currency toCurrency;
    
    @Column(name = "FROM_AMOUNT")
    private BigDecimal fromAmount = BigDecimal.ZERO;
    
    @Column(name = "TO_AMOUNT")
    private BigDecimal toAmount = BigDecimal.ZERO;
    
    @Column(name = "LOCAL_AMOUNT")
    private BigDecimal localAmount = BigDecimal.ZERO;    

    @Column(name = "VIREMENT_TYPE")
    private VirementType virementType = VirementType.VIREMENT;

    public Long getId() {    	
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
   
    public BigDecimal getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(BigDecimal localAmount) {
        this.localAmount = localAmount;
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
