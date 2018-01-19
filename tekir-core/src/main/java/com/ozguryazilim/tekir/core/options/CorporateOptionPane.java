/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.options;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.config.AbstractOptionPane;
import com.ozguryazilim.telve.config.OptionPane;
import com.ozguryazilim.telve.config.OptionPaneType;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Tekir Kullanıcı Kurum Temel Bilgileri
 * 
 * @author oyas
 */
@OptionPane( permission = "CorporateOptions", optionPage = CorePages.CorporateOptionPane.class, type = OptionPaneType.System)
public class CorporateOptionPane extends AbstractOptionPane{

    
    private static final String NAME = "corp.name";
    private static final String TAX_OFFICE = "corp.taxOffice";
    private static final String TAX_NUMBER = "corp.taxNumber";
    private static final String INV_ADDRESS = "corp.invAddr";
    
    @Inject
    private Kahve kahve;
    
    private String name;
    private String taxOffice;
    private String taxNumber;
    private String invoiceAddress;
    
    
    
    @PostConstruct
    public void init(){
        
        name = kahve.get(NAME, "").getAsString();
        taxOffice = kahve.get(TAX_OFFICE, "").getAsString();
        taxNumber = kahve.get(TAX_NUMBER, "").getAsString();
        invoiceAddress = kahve.get(INV_ADDRESS, "").getAsString();
        
    }
    
    @Override
    public void save() {
        
        kahve.put(NAME, name);
        kahve.put(TAX_OFFICE, taxOffice);
        kahve.put(TAX_NUMBER, taxNumber);
        kahve.put(INV_ADDRESS, invoiceAddress);
        
        super.save();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }
    
    
    
    
    
}
