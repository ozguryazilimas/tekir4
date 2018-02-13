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
    private static final String INV_COUNTY = "corp.invCounty";
    private static final String INV_PROVINCE = "corp.invProvince";
    private static final String INV_COUNTRY = "corp.invCountry";
    private static final String INV_ZIPCODE = "corp.invZipCode";
    
    @Inject
    private Kahve kahve;
    
    private String name;
    private String taxOffice;
    private String taxNumber;
    private String invoiceAddress;
    private String invoiceCounty;
    private String invoiceProvince;
    private String invoiceCountry;
    private String invoiceZipCode;
    
    
    
    @PostConstruct
    public void init(){
        
        name = kahve.get(NAME, "").getAsString();
        taxOffice = kahve.get(TAX_OFFICE, "").getAsString();
        taxNumber = kahve.get(TAX_NUMBER, "").getAsString();
        invoiceAddress = kahve.get(INV_ADDRESS, "").getAsString();
        invoiceCounty = kahve.get(INV_COUNTY, "").getAsString();
        invoiceProvince = kahve.get(INV_PROVINCE, "").getAsString();
        invoiceCountry = kahve.get(INV_COUNTRY, "").getAsString();
        invoiceZipCode = kahve.get(INV_ZIPCODE, "").getAsString();
        
    }
    
    @Override
    public void save() {
        
        kahve.put(NAME, name);
        kahve.put(TAX_OFFICE, taxOffice);
        kahve.put(TAX_NUMBER, taxNumber);
        kahve.put(INV_ADDRESS, invoiceAddress);
        kahve.put(INV_COUNTY, invoiceCounty);
        kahve.put(INV_PROVINCE, invoiceProvince);
        kahve.put(INV_COUNTRY, invoiceCountry);
        kahve.put(INV_ZIPCODE, invoiceZipCode);
        
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


    public String getInvoiceCounty() {
        return invoiceCounty;
    }

    public void setInvoiceCounty(String invoiceCounty) {
        this.invoiceCounty = invoiceCounty;
    }

    public String getInvoiceProvince() {
        return invoiceProvince;
    }

    public void setInvoiceProvince(String invoiceProvince) {
        this.invoiceProvince = invoiceProvince;
    }

    public String getInvoiceCountry() {
        return invoiceCountry;
    }

    public void setInvoiceCountry(String invoiceCountry) {
        this.invoiceCountry = invoiceCountry;
    }

    public String getInvoiceZipCode() {
        return invoiceZipCode;
    }

    public void setInvoiceZipCode(String invoiceZipCode) {
        this.invoiceZipCode = invoiceZipCode;
    }
}
