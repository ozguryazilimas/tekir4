/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

import com.ozguryazilim.telve.entities.EntityBase;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * İletişim adresi.
 * 
 * E-posta, telefon, v.b.
 *
 * Tek bir sınıfa koymak yerine miras alınan alt sınıflar mı yapsak?
 * Hem type safe olur, hem de kontroller ( validation ) daha doğru olabilir.
 * 
 * @author Hakan Uygun
 */
@Entity
@Table( name = "TC_COMMADDR" )
public class ContactInformation extends EntityBase{

    /**
     * Bu adresin kime ait olduğu
     */
    private Contact contact;
    
    
    /**
     * Business, Home, Personal, Mobile, Fax, Invoice, Shipment, Default( primary )
     */
    private String roles;
    
    
    
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List<String> getRoles(){
        List<String>  ls = new ArrayList<>();
        ls.add("BUSINESS");
        ls.add("HOME");
        ls.add("PERSONEL");
        return ls;
    }
    
    /**
     * UI'de ne nasıl gösterilecek?
     * @return 
     */
    public String getCaption(){
        return "";
    }
    
    
    /**
     * Hangi icon'nun gösterileceği. 
     * 
     * altsınıflar kendi ikonlarını çoklayabilirler. Örneğin normal telefon mobil telefon gibi.
     * @return 
     */
    public String getIcon(){
        return "";
    }
}
