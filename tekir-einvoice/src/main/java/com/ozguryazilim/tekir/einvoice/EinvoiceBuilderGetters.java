package com.ozguryazilim.tekir.einvoice;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.entities.InvoiceItem;
import com.ozguryazilim.tekir.entities.InvoiceSummary;
import com.ozguryazilim.tekir.entities.SalesInvoice;
import com.ozguryazilim.tekir.entities.TaxDefinition;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EinvoiceBuilderGetters {

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

    public String getSaticiAdres(Kahve kahve){
        return kahve.get("corp.invAddr", "").getAsString();
    }

    public String getSaticiIlce(Kahve kahve){
        return kahve.get("corp.invCounty", "").getAsString();
    }

    public String getSaticiSehir(Kahve kahve){
        return kahve.get("corp.invProvince", "").getAsString();
    }

    public String getSaticiUlke(Kahve kahve){
        return kahve.get("corp.invCountry", "").getAsString();
    }

    public String getSaticiPostaKodu(Kahve kahve){
        return kahve.get("corp.invZipCode", "").getAsString();
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

    public String getAliciPostaKodu(SalesInvoice entity){
        return entity.getAccount().getPrimaryAddress().getZipCode();
    }

    public String getAliciAdres(SalesInvoice entity){
        return entity.getAccount().getPrimaryAddress().getAddress();
    }

    public InvoiceItem getFaturaSatir(SalesInvoice entity, int i){
        return entity.getItems().get(i);
    }

    public String getMiktarBirimKodu(InvoiceItem commodityEntity){
        return commodityEntity.getCommodity().getUnitSet().getEinvoiceCode();
    }

    public BigDecimal getMiktarTipi(InvoiceItem commodityEntity){
        return commodityEntity.getQuantity().getAmount().setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getBirimFiyat(InvoiceItem commodityEntity){
        return commodityEntity.getPrice().setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getMalHizmetMiktari(InvoiceItem commodityEntity){
        return commodityEntity.getTotal().setScale(2, RoundingMode.CEILING);
    }

    public String getMalHizmetBilgileriAdi(InvoiceItem commodityEntity){
        return commodityEntity.getCommodity().getName();
    }

    public Integer getIskontoOran(InvoiceItem commodityEntity){
        return commodityEntity.getDiscountRate();
    }

    public BigDecimal getIskontoTutarTipi(InvoiceItem commodityEntity){
        return commodityEntity.getDiscount().setScale(2, RoundingMode.CEILING);
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
        return commodityEntity.getLineTotal().setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getToplamMalHizmetTutari(SalesInvoice entity){
        return entity.getSummaries().get("BeforeDiscountTotal").getAmount().setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getVergiHaricTutar(SalesInvoice entity){
        return entity.getSummaries().get("BeforeTaxTotal").getAmount().setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getToplamIskontoTutari(SalesInvoice entity){
        return entity.getSummaries().get("Discount").getAmount().setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getOdenecekTutar(SalesInvoice entity){
        return entity.getSummaries().get("GrandTotal").getAmount().setScale(2, RoundingMode.CEILING);
    }

    public BigDecimal getToplamVergiTutari(InvoiceSummary taxEntity){
        return taxEntity.getAmount().setScale(2, RoundingMode.CEILING);
    }

    public String getToplamVergiKodu(InvoiceSummary taxEntity){
        return taxEntity.getCode();
    }

    public String getToplamVergiAdi(InvoiceSummary taxEntity){
        return taxEntity.getBaseName();
    }

    public String getBelgeNo(SalesInvoice entity){
        return entity.getVoucherNo();
    }

}
