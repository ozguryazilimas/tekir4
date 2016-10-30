/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.EntityBase;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Fişler üzerinde toplam bilgilerini tutmak için ver modeli.
 * 
 * TODO: VoucherItemBase ile olduğu gibi bununda aslında yeri burası olmaya bilir.
 * 
 * Her bir satır, fişle ilgili bir tutar bilgisi taşır.
 * 
 * Vergi, VergiMatrahı, ToplamVergi, Indirim v.b.
 * 
 * 
 * @author Hakan Uygun
 */
@MappedSuperclass
public abstract class VoucherSummaryBase<E extends VoucherBase> extends EntityBase {
    
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "genericSeq")
    @Column(name = "ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "MASTER_ID", foreignKey = @ForeignKey(name = "FK_QUOTEIT_QUOTE"))
    private E master;
    
    /**
     * Bu row'a erişim için anahtar.
     * Aynı zamanda arayüzde de nasıl gösterileceği konusunda da dil dosyası için kullanılır.
     */
    @Column( name = "ROW_KEY")
    private String rowKey;
    
    /**
     * Ek açıklama alanı.
     * Mesela KDV oran'ı ya da ismi gibi
     */
    @Column( name = "INFO")
    private String info;
    
    /**
     * Satırın değeri
     */
    @Column( name = "AMOUNT")
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public E getMaster() {
        return master;
    }

    public void setMaster(E master) {
        this.master = master;
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    
    
}
