/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Süreç destekli fişler için taban sınıf.
 * 
 * @author Hakan Uygun
 */
@MappedSuperclass
public abstract class VoucherProcessBase extends VoucherBase{
    
    /**
     * Süreç numarası.
     * Belgeler eğer bir süreç içerisinde yer alıyor ise bu numara farklı belgeler içerisinde aynı olacaktır.
     * 
     * Örneğin satış sürecinde Fırsat için alınan numara Teklif, Sipariş, Fatura ve Ödeme belgelerinde de aynı olacaktır.
     * 
     */
    @ManyToOne
    @JoinColumn(name = "PROCESS_ID", foreignKey = @ForeignKey(name = "FK_PROSS_VOG"))
    private Process process;
    
    /**
     * Fişin ilgili olduğu cari hesap
     */
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_PROSS_ACC"))
    private Contact account;

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Contact getAccount() {
        return account;
    }

    public void setAccount(Contact account) {
        this.account = account;
    }
    
    
}
