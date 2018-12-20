package com.ozguryazilim.tekir.entities;

import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Sosyal ağ ve web adresleri
 * 
 * NETWORK üzerinde gerekli tip tutulur. 
 * 
 * @author Hakan Uygun
 */
@Entity
@DiscriminatorValue(value = "NETWORK")
public class ContactSocialAccount extends ContactInformation{
    
    
    /**
     * FB, TW, LinkedIn v.b. 
     * 
     * aslında entegrasyon açısından bu tiplerin kontrollü olması lazım sanırım.
     * 
     * web sayfasıo da buraya girse ? 
     */
    @Column(name="NETWORK")
    private String network;
    
    public String getCaption(){
        return getAddress();
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    @Override
    public String getIcon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getAcceptedSubTypes() {
        return Collections.emptyList();
    }
    
    
}
