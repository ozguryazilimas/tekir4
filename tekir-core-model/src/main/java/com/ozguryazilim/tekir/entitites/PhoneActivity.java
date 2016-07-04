/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

import java.util.List;

/**
 * Telefon görüşmesi activity'si
 * 
 * @author Hakan Uygun
 */
public class PhoneActivity extends Activity{
    
    /**
     * Aranılan/aranılacak olan numara.
     */
    private String number;
    
    
    /**
     * Telefon aramalarının sonuç durumları neler olabilir?
     * @return 
     */
    @Override
    public List<String> getResultStatus(){
        List<String> ls = super.getResultStatus();
        
        ls.add("NumberNotExists");
        ls.add("Recormachine");
        ls.add("Followup");
        
        return ls;
    }
    
}
