package com.ozguryazilim.tekir.einvoice;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.core.options.CorporateOptionPane;
import com.ozguryazilim.tekir.entities.InvoiceItem;
import com.ozguryazilim.tekir.entities.SalesInvoice;
import com.ozguryazilim.tekir.entities.TaxDefinition;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EinvoiceBuilderGetters {
    @Inject
    Kahve kahve;


    public String getFaturaTuru(){
        return "TICARIFATURA";
    }

    public String getFaturaTipi(){
        return "SATIS";
    }

    public String getFaturaTarihi(SalesInvoice entity){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String invoiceDate = dateFormat.format(entity.getDate()).toString();
        return invoiceDate;
    }

    public String getParaBirimi(SalesInvoice entity) {
        String invoiceCurrency = entity.getCurrency().toString();
        if (invoiceCurrency.equalsIgnoreCase("TRY")) {
            invoiceCurrency = "TRL";
        }
        return invoiceCurrency;
    }

    public String getVKNKodu(){
        return "VKN";
    }

    public String getSaticiVKN(Kahve kahve){
        return kahve.get("corp.taxNumber", "").getAsString();
    }

    public String getSaticiUnvan(Kahve kahve){
        return kahve.get("corp.name", "").getAsString();
    }

    public String getSaticiVergiDairesi(Kahve kahve){
        return kahve.get("corp.taxOffice", "").getAsString();
    }

    public String getAliciVKN(SalesInvoice entity){
        return entity.getAccount().getTaxNumber();
    }

    public String getAliciUnvan(SalesInvoice entity){
        return entity.getAccount().getName();
    }

    public String getAliciVergiDairesi(SalesInvoice entity){
        return entity.getAccount().getTaxOffice();
    }

    public String getAliciIlce(SalesInvoice entity){
        return entity.getAccount().getPrimaryAddress().getCounty();
    }

    public String getAliciSehir(SalesInvoice entity){
        return entity.getAccount().getPrimaryAddress().getProvince();
    }

    public String getAliciUlke(SalesInvoice entity){
        return entity.getAccount().getPrimaryAddress().getCountry();
    }

    public InvoiceItem getFaturaSatir(SalesInvoice entity, int i){
        return entity.getItems().get(i);
    }

    public String getMiktarBirimKodu(InvoiceItem commodityEntity){
        return commodityEntity.getCommodity().getUnitSet().getEinvoiceCode();
    }

    public BigDecimal getMiktarTipi(InvoiceItem commodityEntity){
        return commodityEntity.getQuantity().getAmount();
    }

    public BigDecimal getBirimFiyat(InvoiceItem commodityEntity){
        return commodityEntity.getPrice();
    }

    public BigDecimal getMalHizmetMiktari(InvoiceItem commodityEntity){
        return commodityEntity.getTotal();
    }

    public String getMalHizmetBilgileriAdi(InvoiceItem commodityEntity){
        return commodityEntity.getCommodity().getName();
    }

    public Integer getIskontoOran(InvoiceItem commodityEntity){
        return commodityEntity.getDiscountRate();
    }

    public BigDecimal getIskontoTutarTipi(InvoiceItem commodityEntity){
        return commodityEntity.getDiscount();
    }

    public TaxDefinition getTax1(InvoiceItem commodityEntity){
        return commodityEntity.getCommodity().getTax1();
    }

    public TaxDefinition getTax2(InvoiceItem commodityEntity){
        return commodityEntity.getCommodity().getTax2();
    }
    public TaxDefinition getTax3(InvoiceItem commodityEntity){
        return commodityEntity.getCommodity().getTax3();
    }

    public BigDecimal getMatrah(InvoiceItem commodityEntity){
        return commodityEntity.getLineTotal();
    }

    public BigDecimal getToplamMalHizmetTutari(SalesInvoice entity){
        return entity.getSummaries().get("BeforeDiscountTotal").getAmount();
    }

    public BigDecimal getVergiHaricTutar(SalesInvoice entity){
        return entity.getSummaries().get("BeforeTaxTotal").getAmount();
    }

    public BigDecimal getToplamIskontoTutari(SalesInvoice entity){
        return entity.getSummaries().get("Discount").getAmount();
    }

    public BigDecimal getOdenecekTutar(SalesInvoice entity){
        return entity.getSummaries().get("GrandTotal").getAmount();
    }

}
