/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.currency;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.ozguryazilim.mutfak.kahve.Kahve;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.ConfigResolver;

/**
 * Sistemde tanımlı olan currency tanımlarını sunar.
 * 
 * @author Hakan Uygun
 */
@ApplicationScoped
@Named
public class CurrencyService implements Serializable{
    
    private static final String AVAILABLE_CURRENCIES = "tekir.currency.avilable";
    private static final String USABLE_CURRENCIES = "tekir.currency.useable";
    
    private static final String DEFAULT_CURRENCY = "tekir.currency.default";
    
    @Inject
    private Kahve kahve;
            
    
    private List<Currency> availableCurrencies = new ArrayList<>();
    private List<Currency> usableCurrencies = new ArrayList<>();
    private Currency defaultCurrency;
    
    /**
     * Config ve Kahve'den gereken bilgileri toplayarak bileşeni hazırlar.
     */
    @PostConstruct
    public void init(){
        //Config'de yazılı para birimleri alınıp available olarak setleniyor
        String vals = ConfigResolver.getPropertyValue(AVAILABLE_CURRENCIES);
        Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(vals)
                .forEach(ccy -> availableCurrencies.add(Currency.getInstance(ccy)));
        
        vals = kahve.get(USABLE_CURRENCIES,ConfigResolver.getPropertyValue(USABLE_CURRENCIES)).getAsString();
        Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(vals)
                .forEach(ccy -> usableCurrencies.add(Currency.getInstance(ccy)));
        
        defaultCurrency = Currency.getInstance(kahve.get(DEFAULT_CURRENCY, ConfigResolver.getPropertyValue(DEFAULT_CURRENCY)).getAsString());
    }
    
    /**
     * Sistem de tanımlı para birimlerinin listesini döndürür.
     * Bu birimler tekir.currencies property'alanında tanımlanırlar
     * 
     * @return 
     */
    public List<Currency> getAvailableCurrencies(){
        return availableCurrencies;
    }

    /**
     * Uygulama içinde kullanılacak olan varsayılan para birimni
     * 
     * @return 
     */
    public Currency getDefaultCurrency(){
        return defaultCurrency;
    }
    
    
    /**
     * Verilen currency'i default olarak setler
     * @param ccy 
     */
    public void setDefaultCurrency(Currency ccy ){
        defaultCurrency = ccy;
        kahve.put(DEFAULT_CURRENCY, defaultCurrency.getCurrencyCode());
    }
    
    /**
     * Geriye sistemde kullanılabilecek olan para birimi listesini döndürür.
     * @return 
     */
    public List<Currency> getCurrencies(){
        return usableCurrencies;
    }
    
    
    /**
     * Verilen listeyi kullanılabilir pra birimleri olrak setler
     * 
     * @param currencies 
     */
    public void setCurrencies( List<Currency> currencies ){
        
        List<String> ls = new ArrayList();
        currencies.forEach(ccy -> ls.add(ccy.getCurrencyCode()));
        
        StringBuilder sb = new StringBuilder();
        Joiner.on(',').appendTo(sb, ls);
        
        kahve.put(USABLE_CURRENCIES, sb.toString());
        
        usableCurrencies.clear();
        usableCurrencies.addAll(currencies);
        
    }
    
}
