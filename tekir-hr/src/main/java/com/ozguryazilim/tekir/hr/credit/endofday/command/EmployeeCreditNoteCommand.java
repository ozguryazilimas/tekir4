/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.credit.endofday.command;

import com.ozguryazilim.tekir.entities.EmployeeCreditNoteType;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.telve.messagebus.command.AbstractStorableCommand;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.reports.ReportDate;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Personel Alacak Fişi Gün Sonu Komutu.
 * 
 * Zamanlanmış görev olarak çalışacak.
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-07-10
 * @see AbstractStorableCommand
 * @see EmployeeCreditNoteType
 */
public class EmployeeCreditNoteCommand extends AbstractStorableCommand {
    
    private EmployeeCreditNoteType type;
    
    private FinanceAccount financeAccount;
    
    private VoucherGroup group;
    
    private String topic;
    
    private String info;

    private Currency currency;
    
    private BigDecimal amount = BigDecimal.ZERO;

    private ReportDate date = new ReportDate(DateValueType.Today);

    private ReportDate paymentDate = new ReportDate(DateValueType.Today);

    public EmployeeCreditNoteType getType() {
        return type;
    }

    public void setType(EmployeeCreditNoteType type) {
        this.type = type;
    }

    public FinanceAccount getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(FinanceAccount financeAccount) {
        this.financeAccount = financeAccount;
    }

    public VoucherGroup getGroup() {
        return group;
    }

    public void setGroup(VoucherGroup group) {
        this.group = group;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public ReportDate getDate() {
        return date;
    }

    public void setDate(ReportDate date) {
        this.date = date;
    }

    public ReportDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(ReportDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
