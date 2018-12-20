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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    
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
