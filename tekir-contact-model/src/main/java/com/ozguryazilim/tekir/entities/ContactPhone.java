/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Telefon bilgisi
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue(value = "PHONE")
public class ContactPhone extends ContactInformation{

    /**
     * Telefon numarası
     * @return 
     */
    public String getNumber() {
        return getAddress();
    }

    /**
     * Telefon numarası formatted.
     * 
     * @param number 
     */
    public void setNumber(String number) {
        setAddress(number);
    }
    
    
    @Override
    public List<String> getAcceptedSubTypes() {
        List<String> ls = new ArrayList<>();
        
        ls.add("LAND");
        ls.add("MOBILE");
        ls.add("FAX");
        
        return ls;
    }
    
     
    public String getCaption(){
        return getNumber();
    }

    @Override
    public String getIcon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
