/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.currency;

import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.config.AbstractOptionPane;
import java.util.Currency;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import com.ozguryazilim.telve.config.OptionPane;
import com.ozguryazilim.telve.config.OptionPaneType;

/**
 * Döviz Tanım ekranı için kontrol sınıfı.
 *
 * @author Hakan Uygun
 */
//@Named
//@GroupedConversationScoped
@OptionPane( permission = "currencyDefinition", optionPage = CorePages.CurrencyDefinition.class, type = OptionPaneType.System)
public class CurrencyDefinitionHome extends AbstractOptionPane {


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
       
    
    @Override
    public void save(){
        service.setDefaultCurrency(defaultCurrency);
        service.setCurrencies(selectedCurrencies);
    }


	
}
