/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Telefon görüşmesi activity'si
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue("PHONE")
public class PhoneActivity extends Activity{
    
    /**
     * Aranılan/aranılacak olan numara.
     * 
     * TODO: Bunu comchannels ile ilişkilendirsek mi?
     */
    @Column( name = "NUMBER")
    private String number;
    
    
    /**
     * Telefon aramalarının sonuç durumları neler olabilir?
     * @return 
     */
    @Override
    public List<String> getResultStatus(){
        List<String> ls = super.getResultStatus();
        
        ls.add("NumberNotExists");
        ls.add("RecordMachine");
        ls.add("Followup");
        
        return ls;
    }
    
}
