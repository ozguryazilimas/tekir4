package com.ozguryazilim.tekir.einvoice.options;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.einvoice.config.EinvoicePages;
import com.ozguryazilim.telve.config.AbstractOptionPane;
import com.ozguryazilim.telve.config.OptionPane;
import com.ozguryazilim.telve.config.OptionPaneType;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@OptionPane( permission = "EinvoiceOptions", optionPage = EinvoicePages.EinvoiceOptionPane.class, type = OptionPaneType.System)
public class EinvoiceOptionPane extends AbstractOptionPane {

    private static final String USERNAME = "einvoice.username";
    private static final String PASSWORD = "einvoice.password";

    @Inject
    private Kahve kahve;

    private String username;
    private String password;

    @PostConstruct
    public void init(){

        username = kahve.get(USERNAME,"").getAsString();
        password = kahve.get(PASSWORD,"").getAsString();
    }

    @Override
    public void save(){
        kahve.put(USERNAME,username);
        kahve.put(PASSWORD,password);

        super.save();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}