package com.ozguryazilim.tekir.entities;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author oyas
 */
@Entity
@DiscriminatorValue(value = "BANK")
public class ContactBank extends ContactInformation{

    @Column(name="BANK")
    private String bank;
    
    @Column(name="IBAN")
    private String iban;
    
    @Column(name="CCY")
    private Currency currency;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    
    @Override
    public List<String> getAcceptedSubTypes() {
        List<String> ls = new ArrayList<>();
        return ls;
    }

    @Override
    public String getCaption() {
        return getBank() + "-" + getIban();
    }

    @Override
    public String getIcon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
