/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.annotations.BizKey;
import com.ozguryazilim.telve.entities.AuditBase;
import com.ozguryazilim.telve.unit.UnitName;
import java.math.BigDecimal;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Emtia / Ticari Mal tanımı. 
 * 
 * En geniş hali ile Tekir içerisinde alınıp satılan şeyleri tanımlar.
 * 
 * Ürün,Servis v.b. gibi şeyler bu sınıfı miras alarak detaylanırlar.
 * 
 * 
 * Bazı ürünlerin alış ve satışları üzerinde farklı vergi oranları olabiliyor. Bu durum şu anda kapsam dışında.
 * KDV Tevkifatı sanki bir vergi gibi tannıtılıyor. Eğer Account üzerinde tevkifat işaretli ise hesaplanıcak
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TCO_COMMOTITY" )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Commodity extends AuditBase{

    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;

    @Column(name = "CODE", unique = true)
    @NotNull
    private String code;
    
    @Column(name = "NAME")
    @NotNull @BizKey
    private String name;
    
    @Column(name = "INFO")
    private String info;
        
    @Column(name = "ISACTIVE")
    private Boolean active = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "UNITSET_ID", foreignKey = @ForeignKey(name = "FK_COMM_UNITSET"))
    private UnitSetDefinition unitSet;
    
    @Column( name = "UNIT_DEFAULT" )
    private String defaultUnit;
    
    @Column( name = "PRICE" )
    private BigDecimal price;
    
    @Column( name = "CURRENCY")
    private Currency defaultCurrency;
    
    /**
     * Birinci vergi türü ( örneğin ÖTV )
     */
    @ManyToOne
    @JoinColumn(name = "TAX1_ID", foreignKey = @ForeignKey(name = "FK_COMM_TAX1"))
    private TaxDefinition tax1;
    
    /**
     * İkinci vergi türü ( örneğin KTV )
     */
    @ManyToOne
    @JoinColumn(name = "TAX2_ID", foreignKey = @ForeignKey(name = "FK_COMM_TAX2"))
    private TaxDefinition tax2;
    
    /**
     * Üçüncü vergi türü ( örneğin Tevkifat ( TKF ) )
     */
    @ManyToOne
    @JoinColumn(name = "TAX3_ID", foreignKey = @ForeignKey(name = "FK_COMM_TAX3"))
    private TaxDefinition tax3;
    
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_COMM_CAT"))
    private CommodityCategory category;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UnitSetDefinition getUnitSet() {
        return unitSet;
    }

    public void setUnitSet(UnitSetDefinition unitSet) {
        this.unitSet = unitSet;
    }

    public String getDefaultUnit() {
        return defaultUnit;
    }

    public void setDefaultUnit(String defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public TaxDefinition getTax1() {
        return tax1;
    }

    public void setTax1(TaxDefinition tax1) {
        this.tax1 = tax1;
    }

    public TaxDefinition getTax2() {
        return tax2;
    }

    public void setTax2(TaxDefinition tax2) {
        this.tax2 = tax2;
    }

    public TaxDefinition getTax3() {
        return tax3;
    }

    public void setTax3(TaxDefinition tax3) {
        this.tax3 = tax3;
    }

    public CommodityCategory getCategory() {
        return category;
    }

    public void setCategory(CommodityCategory category) {
        this.category = category;
    }
    
    
    public UnitName getUnitName(){
        if ( defaultUnit == null || defaultUnit.length() == 0 ){
            return new UnitName("", "");
        }
        return UnitName.of(defaultUnit);
    }
    
    public void setUnitName( UnitName un ){
        if( un == null ) {
            defaultUnit = null;
        } else {
            defaultUnit = un.toString();
        }
    }
    
}
