package com.ozguryazilim.tekir.core.dialogs;

import com.ozguryazilim.tekir.core.currency.exchange.ExchangeRateHome;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 * Eger bugune ait exchange kayitlari yoksa kullanicinin kayitlari kolayca yenilemesi icin dialog
 */
@SessionScoped
@Named
public class ExchangeRateNotFoundDialog implements Serializable {

    @Inject
    ExchangeRateHome exchangeRateHome;

    public void openDialog() {
        Map<String, Object> options = new HashMap<>();

        decorateDialog(options);

        PrimeFaces.current().dialog().openDynamic(getDialogName(), options, null);
    }

    public void closeDialog() {
        exchangeRateHome.getTCMBRates();
        PrimeFaces.current().dialog().closeDynamic(null);
    }

    public void cancelDialog() {
        PrimeFaces.current().dialog().closeDynamic(null);
    }


    /**
     * Açılacak olan diolog özellikleri setlenir.
     *
     * Alt sınıflar isterse bu methodu override ederk dialog özellikleirni değiştirebilirler.
     */
    protected void decorateDialog(Map<String, Object> options) {
        options.put("modal", true);
        //options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 450);
    }

    public String getDialogName() {
        return "/dialogs/exchangeRateNotFoundDialog";
    }
}
