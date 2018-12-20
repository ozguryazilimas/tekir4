package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.unit.QuantitativeAmount;
import com.ozguryazilim.telve.unit.UnitName;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author oyas
 */
@Embeddable
public class Quantity implements Serializable{
    
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    
    @Column(name = "UNIT")
    private String unit;

    public Quantity() {
    }

    public Quantity(BigDecimal amount, String unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public QuantitativeAmount getQuantitativeAmount(){
        return QuantitativeAmount.of(amount, unit);
    }
    
    public  void setQuantitativeAmount(QuantitativeAmount amt){
        amount = amt.getAmount();
        unit = amt.getUnitName().toString();
    }
    
    public UnitName getUnitName(){
        return UnitName.of(unit);
    }
    
    public void setUnitName( UnitName un ){
        unit = un.toString();
    }
}
