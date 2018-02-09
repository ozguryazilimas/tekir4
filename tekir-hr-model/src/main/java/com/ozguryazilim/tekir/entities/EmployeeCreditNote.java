package com.ozguryazilim.tekir.entities;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Çalışan alacak fişi modeli
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-30
 * @see VoucherBase
 * @see Employee
 * @see EmployeeCreditNoteType
 */
@Entity
@Table(name = "THR_CREDIT_NOTE")
public class EmployeeCreditNote extends VoucherBase {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    /**
     * Alacak fişinin hangi çalışana ait olduğu
     */
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", foreignKey = @ForeignKey(name = "FK_CREDIT_NOTE_EMP"))
    private Employee employee;
    
    @Column(name = "CCY")
    private Currency currency;
    
    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;
    
    @Column(name = "LOCAL_AMOUNT")
    private BigDecimal localAmount = BigDecimal.ZERO;
    
    @Column(name = "PAYMENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    
    /**
     * Ödemenin hangi hesaptan yapılacağı
     */
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_CREDIT_NOTE_ACC"))
    private FinanceAccount financeAccount;
    
    /**
     * Alacak fişinin tipi
     */
    @Enumerated(EnumType.STRING)
    @Column( name = "TYPE")
    private EmployeeCreditNoteType type;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public FinanceAccount getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(FinanceAccount financeAccount) {
        this.financeAccount = financeAccount;
    }
    
    public EmployeeCreditNoteType getType() {
        return type;
    }

    public void setType(EmployeeCreditNoteType type) {
        this.type = type;
    }
    
}
