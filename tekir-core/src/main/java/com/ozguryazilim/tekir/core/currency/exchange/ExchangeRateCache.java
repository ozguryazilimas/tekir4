/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.currency.exchange;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;

/**
 * Exchange rate'leri her seferinde veri tabanından okunmaması için basit bir cahce yapısı.
 * 
 * @author Hakan Uygun
 */
@ApplicationScoped
public class ExchangeRateCache implements Serializable{
   
    private final Map<Date, Map<String, Map<String, BigDecimal>>> rates = new ConcurrentHashMap<>();

    public Map<Date, Map<String, Map<String, BigDecimal>>> getRates() {
        return rates;
    }
    
    /**
     * Tutulan kur cache'i temizlenir.
     * 
     * TODO: Event-Observer yapsak mı?
     */
    public void clearChache(){
        rates.clear();
    }
    
    
}
