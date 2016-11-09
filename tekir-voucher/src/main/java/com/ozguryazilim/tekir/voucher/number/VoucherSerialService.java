/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.number;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.telve.feature.FeatureHandler;
import com.ozguryazilim.telve.sequence.SequenceManager;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author oyas
 */
@Named
@ApplicationScoped
public class VoucherSerialService implements Serializable{
    
    @Inject
    private SequenceManager sequenceManager;
    
    @Inject
    private Kahve kahve;
    
    
    public String getNewSerialNumber( FeatureHandler featureHandler){
        String fhn = featureHandler.getName();
        String val = kahve.get("voucher.serial." + fhn, fhn.substring(0, 3).toUpperCase()).getValue();
        
        return sequenceManager.getNewSerialNumber(val, 6);
    }
    
}

