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
public class ContactPhone extends ContactInformation{

    
    private String number;
    
    @Override
    public List<String> getRoles() {
        List<String> ls = super.getRoles(); 
        
        ls.add("LAND");
        ls.add("MOBILE");
        ls.add("FAX");
        
        return ls;
    }
    
     
    public String getCaption(){
        return number;
    }
}
