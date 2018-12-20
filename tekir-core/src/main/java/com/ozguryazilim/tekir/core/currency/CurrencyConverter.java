package com.ozguryazilim.tekir.core.currency;

import java.util.Currency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * java.util.Currency jsf converter
 * 
 * @author Hakan Uygun
 */
@FacesConverter("currencyConverter")
public class CurrencyConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return Currency.getInstance(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if( o instanceof Currency){
            return ((Currency)o).getCurrencyCode();
        }
        
        return "";
    }
    
}
