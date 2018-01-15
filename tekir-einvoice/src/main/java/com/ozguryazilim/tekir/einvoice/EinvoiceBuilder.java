package com.ozguryazilim.tekir.einvoice;

import com.ozguryazilim.tekir.einvoice.model.*;
import com.ozguryazilim.tekir.entities.InvoiceItem;
import com.ozguryazilim.tekir.entities.SalesInvoice;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EinvoiceBuilder {

    public String getInvoiceDate(SalesInvoice entity){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String invoiceDate = dateFormat.format(entity.getDate()).toString();
        return invoiceDate;
    }

    public String getInvoiceCurrency(SalesInvoice entity) {
        String invoiceCurrency = entity.getCurrency().toString();
        if (invoiceCurrency.equalsIgnoreCase("TRY")) {
            invoiceCurrency = "TRL";
        }
        return invoiceCurrency;
    }

    public String getBuyerVKN(SalesInvoice entity){
        return entity.getAccount().getTaxNumber();
    }

    public String getBuyerOrganizationName(SalesInvoice entity){
        return entity.getAccount().getOrganization();
    }

    public String getBuyerCounty(SalesInvoice entity){
        return entity.getAccount().getPrimaryAddress().getCounty();
    }

    public String getBuyerProvince(SalesInvoice entity){
        return entity.getAccount().getPrimaryAddress().getProvince();
    }

    public String getBuyerCountry(SalesInvoice entity){
        return entity.getAccount().getPrimaryAddress().getCountry();
    }

    public int getTaxTypeCodes(String taxType){
        if (taxType.equalsIgnoreCase("KDV")){
            return 0015;
        }
        if (taxType.equalsIgnoreCase("OIV")){
            return 4080;
        }
        if (taxType.equalsIgnoreCase("OTV")){
            return 0;
        }
        return 0;
    }

    public void buildEinvoice(SalesInvoice entity) throws Exception{
        Fatura fatura = new Fatura();
        TarafBilgileriTipi aliciTarafBilgileriTipi = new TarafBilgileriTipi();
        TarafBilgileriTipi saticiTarafBilgileriTipi = new TarafBilgileriTipi();
        IdTipi aliciVKN = new IdTipi();
        IdTipi saticiVKN = new IdTipi();
        AdresTipi aliciAdresTipi = new AdresTipi();
        AdresTipi saticiAdresTipi = new AdresTipi();

        fatura.setFaturaTuru("TICARIFATURA");
        fatura.setFaturaTipi("SATIS");
        fatura.setFaturaTarihi(getInvoiceDate(entity));
        fatura.setParaBirimi(getInvoiceCurrency(entity));

        saticiVKN.setSchemeId("VKN");
        saticiVKN.setValue("vergino");

        saticiTarafBilgileriTipi.getAliciSaticiTanimi().add(saticiVKN);

        saticiTarafBilgileriTipi.setUnvan("unvan");

        saticiAdresTipi.setIlce("ilce");
        saticiAdresTipi.setSehir("sehir");
        saticiAdresTipi.setUlke("ulke");
        saticiTarafBilgileriTipi.setPostaAdresi(saticiAdresTipi);

        fatura.setSatici(saticiTarafBilgileriTipi);

        aliciVKN.setSchemeId("VKN");
        aliciVKN.setValue(getBuyerVKN(entity));
        aliciTarafBilgileriTipi.getAliciSaticiTanimi().add(aliciVKN);

        aliciTarafBilgileriTipi.setUnvan(getBuyerOrganizationName(entity));

        aliciAdresTipi.setIlce(getBuyerCounty(entity));
        aliciAdresTipi.setSehir(getBuyerProvince(entity));
        aliciAdresTipi.setUlke(getBuyerCountry(entity));
        aliciTarafBilgileriTipi.setPostaAdresi(aliciAdresTipi);
        fatura.setAlici(aliciTarafBilgileriTipi);

        int listCount = entity.getItems().size();
        for (int i=0; i<listCount; i++){
            InvoiceItem commodityEntity = entity.getItems().get(i);

            FaturaSatirTipi faturaSatirTipi = new FaturaSatirTipi();
            IdTipi idTipi = new IdTipi();
            MiktarTipi miktarTipi = new MiktarTipi();
            TutarTipi tutarTipi = new TutarTipi();
            IskontoArtirimTipi iskontoArtirimTipi = new IskontoArtirimTipi();
            TutarTipi iskontoTutarTipi = new TutarTipi();
            VergilerTipi vergilerTipi = new VergilerTipi();

            idTipi.setValue(String.valueOf(i+1));
            faturaSatirTipi.setSiraNo(idTipi);

            miktarTipi.setBirimKodu("NIU");
            miktarTipi.setValue(commodityEntity.getQuantity().getAmount());
            faturaSatirTipi.setMiktar(miktarTipi);

            tutarTipi.setParaBirimi(getInvoiceCurrency(entity));
            tutarTipi.setValue(commodityEntity.getTotal());
            faturaSatirTipi.setMalHizmetMiktari(tutarTipi);

            if (commodityEntity.getDiscountRate() != 0){
                iskontoArtirimTipi.setArtirim(false);
                iskontoArtirimTipi.setOran(BigDecimal.valueOf(commodityEntity.getDiscountRate() * 0.01));
                iskontoTutarTipi.setParaBirimi(getInvoiceCurrency(entity));
                iskontoTutarTipi.setValue(commodityEntity.getDiscount());
                iskontoArtirimTipi.setTutar(iskontoTutarTipi);
                iskontoArtirimTipi.setUygulandigiTutar(tutarTipi);
                faturaSatirTipi.getIskontoArtirim().add(iskontoArtirimTipi);
                
            }

            fatura.getFaturaSatir().add(faturaSatirTipi);
        }

        File file = new File("file2.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Fatura.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(fatura, file);
        jaxbMarshaller.marshal(fatura, System.out);
    }
}
