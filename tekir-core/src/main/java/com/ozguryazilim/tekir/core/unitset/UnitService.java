/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.unitset;

import com.ozguryazilim.telve.unit.UnitName;
import com.ozguryazilim.telve.unit.UnitSet;
import com.ozguryazilim.telve.unit.UnitSetRegistery;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Unit'ler ile ilgili UI için bilgi döndüren service sınıfı
 * 
 * @author Hakan Uygun
 */
@Named
@ApplicationScoped
public class UnitService implements Serializable{
    
    /**
     * Geriye ismi verilen unitSet için birim isim listesini döndürür.
     * @param unitSet
     * @return 
     */
    public List<UnitName> getUnits( String unitSet ){
        
        UnitSet us = UnitSetRegistery.getUnitSet(unitSet);
        
        if( us == null ) return Collections.emptyList();
        
        return us.getUnitNames();
    }

    /**
     * Verilen UnitName'in diğer unitlerinin listesini döndürür.
     * @param unitName
     * @return 
     */
    public List<UnitName> getUnits( UnitName unitName ){
        return getUnits( unitName.getUnitSet());
    }
    
}
