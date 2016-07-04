/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

/**
 *
 * @author oyas
 */
public class ContactSocialAccount extends ContactInformation{
    
    
    private String address;
    
    /**
     * FB, TW, LinkedIn v.b. 
     * 
     * aslında entegrasyon açısından bu tiplerin kontrollü olması lazım sanırım.
     * 
     * web sayfasıo da buraya girse ? 
     */
    private String network;
    
    public String getCaption(){
        return address;
    }
    
    public String getIcon(){
        return "";
    }
}
