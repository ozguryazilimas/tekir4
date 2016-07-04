/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

import java.util.List;

/**
 *
 * @author oyas
 */
public class ContactAddress extends ContactInformation{
    
    
    /**
     * Telefon numarası, e-posta adresi v.b.
     */
    private String address;
    
    //Gerçek adresler için lazım alanlar
    private String city;
    private String province;
    private String teritory;
    //Lan-Lon biçminde?
    private String point; 
    
    
    @Override
    public List<String> getRoles() {
        List<String> ls = super.getRoles(); 
        
        ls.add("INVOICE");
        ls.add("SHIPMENT");
        
        return ls;
    }

    public String getCaption(){
        return address + city ;
    }
}
