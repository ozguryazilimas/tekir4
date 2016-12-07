/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.currency;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.beanutils.ConversionException;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ozguryazilim.tekir.core.currency.exchange.ExchangeRateHome;
import com.ozguryazilim.telve.messages.FacesMessages;

/**
 * Döviz Tanım ekranı için kontrol sınıfı.
 *
 * @author Hakan Uygun
 */
@Named
@GroupedConversationScoped
public class CurrencyDefinitionHome implements Serializable {


    @Inject
    private CurrencyService service;
    

    private List<Currency>  selectedCurrencies;
    private Currency defaultCurrency;


    @PostConstruct
    public void init(){
        //Burada populate yapmak lazım gibi
    	selectedCurrencies = service.getCurrencies();
    	defaultCurrency = service.getDefaultCurrency();    	
    }

    public List<Currency> getSelectedCurrencies() {
        if( selectedCurrencies == null ){
            selectedCurrencies = service.getCurrencies();
        }
        return selectedCurrencies;
    }

    public void setSelectedCurrencies(List<Currency> selectedCurrencies) {
        this.selectedCurrencies = selectedCurrencies;
    }

    public Currency getDefaultCurrency() {
        if( defaultCurrency == null ){
            defaultCurrency = service.getDefaultCurrency();
        }
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
       
    
    public void save(){
        service.setDefaultCurrency(defaultCurrency);
        service.setCurrencies(selectedCurrencies);
    }


	
}
