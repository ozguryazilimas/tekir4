package com.ozguryazilim.tekir.einvoice;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.einvoice.model.*;
import com.ozguryazilim.tekir.entities.*;
import com.ozguryazilim.tekir.voucher.utils.VoucherItemUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class EinvoiceBuilder {

    public static final String SATICI_VKN = "corp.taxNumber";
    private static final String FATURA_TURU = "TICARIFATURA";
    private static final String FATURA_TIPI = "SATIS";
    private static final String VKN_KODU = "VKN";
    private static final String SATICI_UNVAN = "corp.name";
    private static final String SATICI_VERGIDAIRESI = "corp.taxOffice";
    private static final String SATICI_ADRES = "corp.invAddr";
    private static final String SATICI_ILCE = "corp.invCounty";
    private static final String SATICI_SEHIR = "corp.invProvince";
    private static final String SATICI_ULKE = "corp.invCountry";
    private static final String SATICI_POSTAKODU = "corp.invZipCode";
    private static final String TOPLAM_MAL_HIZMET_TUTARI = "BeforeDiscountTotal";
    private static final String VERGI_HARIC_TUTAR = "BeforeTaxTotal";
    private static final String TOPLAM_ISKONTO_TUTARI = "Discount";
    private static final String ODENECEK_TUTAR = "GrandTotal";

    /**
     * SalesInvoice ve Kahve entitylerini alir.
     * SalesInvoice'dan urun, vergi ve alici kurum bilgileri alinir.
     * Kahve'den satici(tekir'i kullanan) kurumun bilgileri alinir.
     *
     * @return Alinan degerleri JAXB ile einvoiceFile.xml dosyasina derler ve bu dosyayi geri doner.
     * @author soner.cirit
     */
    public File buildEinvoice(SalesInvoice entity, Kahve kahve) {

        Fatura fatura = new Fatura();
        TarafBilgileriTipi aliciTarafBilgileriTipi = new TarafBilgileriTipi();
        TarafBilgileriTipi saticiTarafBilgileriTipi = new TarafBilgileriTipi();
        IdTipi aliciVKN = new IdTipi();
        IdTipi saticiVKN = new IdTipi();
        AdresTipi aliciAdresTipi = new AdresTipi();
        AdresTipi saticiAdresTipi = new AdresTipi();
        ParasalToplamlarTipi parasalToplamlarTipi = new ParasalToplamlarTipi();
        TutarTipi toplamMalHizmetTutari = new TutarTipi();
        TutarTipi vergiHaricTutar = new TutarTipi();
        TutarTipi toplamIskontoTutari = new TutarTipi();
        TutarTipi odenecekTutar = new TutarTipi();


        fatura.setFaturaTuru(FATURA_TURU);
        fatura.setFaturaTipi(FATURA_TIPI);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String invoiceDate = dateFormat.format(entity.getDate());
        fatura.setFaturaTarihi(invoiceDate);
        fatura.setParaBirimi(getParaBirimi(entity));

        saticiVKN.setSchemeId(VKN_KODU);
        saticiVKN.setValue(getSaticiVKN(kahve));
        saticiTarafBilgileriTipi.getAliciSaticiTanimi().add(saticiVKN);

        saticiTarafBilgileriTipi.setUnvan(kahve.get(SATICI_UNVAN, "").getAsString());

        saticiTarafBilgileriTipi.setVergiDairesi(kahve.get(SATICI_VERGIDAIRESI, "").getAsString());

        saticiAdresTipi.setIlce(kahve.get(SATICI_ILCE, "").getAsString());
        saticiAdresTipi.setSehir(kahve.get(SATICI_SEHIR, "").getAsString());
        saticiAdresTipi.setUlke(kahve.get(SATICI_ULKE, "").getAsString());
        saticiAdresTipi.setPostaKodu(kahve.get(SATICI_POSTAKODU, "").getAsString());
        saticiAdresTipi.setCaddeSokak(kahve.get(SATICI_ADRES, "").getAsString());
        saticiTarafBilgileriTipi.setPostaAdresi(saticiAdresTipi);

        fatura.setSatici(saticiTarafBilgileriTipi);

        Contact aliciEntity = entity.getAccount();

        aliciVKN.setSchemeId(VKN_KODU);
        aliciVKN.setValue(aliciEntity.getTaxNumber());
        aliciTarafBilgileriTipi.getAliciSaticiTanimi().add(aliciVKN);

        aliciTarafBilgileriTipi.setUnvan(aliciEntity.getName());

        aliciTarafBilgileriTipi.setVergiDairesi(aliciEntity.getTaxOffice());

        ContactAddress aliciAdresEntity = aliciEntity.getPrimaryAddress();
        if (aliciAdresEntity != null) {
            aliciAdresTipi.setIlce(aliciAdresEntity.getCounty());
            aliciAdresTipi.setSehir(aliciAdresEntity.getProvince());
            aliciAdresTipi.setUlke(aliciAdresEntity.getCountry());
            aliciAdresTipi.setPostaKodu(aliciAdresEntity.getZipCode());
            aliciAdresTipi.setCaddeSokak(aliciAdresEntity.getAddress());
            aliciTarafBilgileriTipi.setPostaAdresi(aliciAdresTipi);
            fatura.setAlici(aliciTarafBilgileriTipi);
        }

        toplamMalHizmetTutari.setParaBirimi(getParaBirimi(entity));
        Map<String, InvoiceSummary> deneme = entity.getSummaries();
        toplamMalHizmetTutari.setValue(entity.getSummaries().size() == 0 ? BigDecimal.ZERO : entity.getSummaries()
                .get(TOPLAM_MAL_HIZMET_TUTARI).getAmount().setScale(2, RoundingMode.CEILING));
        parasalToplamlarTipi.setToplamMalHizmetTutari(toplamMalHizmetTutari);

        vergiHaricTutar.setParaBirimi(getParaBirimi(entity));
        vergiHaricTutar.setValue(entity.getSummaries().size() == 0 ? BigDecimal.ZERO : entity.getSummaries().get
                (VERGI_HARIC_TUTAR).getAmount().setScale(2, RoundingMode.CEILING));
        parasalToplamlarTipi.setVergiHaricTutar(vergiHaricTutar);

        toplamIskontoTutari.setParaBirimi(getParaBirimi(entity));
        toplamIskontoTutari.setValue(entity.getSummaries().size() == 0 ? BigDecimal.ZERO : entity.getSummaries().get
                (TOPLAM_ISKONTO_TUTARI).getAmount().setScale(2, RoundingMode.CEILING));
        parasalToplamlarTipi.setToplamIskontoTutari(toplamIskontoTutari);

        odenecekTutar.setParaBirimi(getParaBirimi(entity));
        odenecekTutar.setValue(entity.getSummaries().size() == 0 ? BigDecimal.ZERO : entity.getSummaries().get
                (ODENECEK_TUTAR).getAmount().setScale(2, RoundingMode.CEILING));
        parasalToplamlarTipi.setVergiDahilTutar(odenecekTutar);
        parasalToplamlarTipi.setOdenecekTutar(odenecekTutar);

        fatura.setParasalToplamlar(parasalToplamlarTipi);

        List<InvoiceSummary> taxes = VoucherItemUtils.getTaxes(entity.getSummaries());
        BigDecimal toplamVergiTutariVergiler = new BigDecimal("0");
        VergilerTipi toplamVergilerTipi = new VergilerTipi();
        TutarTipi tutarTipi = new TutarTipi();
        for (InvoiceSummary taxEntity : taxes) {
            toplamVergiTutariVergiler = setTotalTax(taxEntity, entity, toplamVergiTutariVergiler, toplamVergilerTipi);
        }
        tutarTipi.setParaBirimi(getParaBirimi(entity));
        tutarTipi.setValue(toplamVergiTutariVergiler.setScale(2, RoundingMode.CEILING));
        toplamVergilerTipi.setToplamVergiTutari(tutarTipi);

        fatura.getVergiler().add(toplamVergilerTipi);


        int listCount = entity.getItems().size();
        for (int i = 0; i < listCount; i++) {
            InvoiceItem commodityEntity = entity.getItems().get(i);

            FaturaSatirTipi faturaSatirTipi = new FaturaSatirTipi();
            IdTipi idTipi = new IdTipi();
            MiktarTipi miktarTipi = new MiktarTipi();
            TutarTipi malHizmetMiktari = new TutarTipi();
            IskontoArtirimTipi iskontoArtirimTipi = new IskontoArtirimTipi();
            TutarTipi iskontoTutarTipi = new TutarTipi();
            VergilerTipi vergilerTipi = new VergilerTipi();
            TutarTipi toplamVergiTutari = new TutarTipi();
            MalHizmetBilgileriTipi malHizmetBilgileri = new MalHizmetBilgileriTipi();
            TutarTipi birimFiyat = new TutarTipi();

            idTipi.setValue(String.valueOf(i + 1));
            faturaSatirTipi.setSiraNo(idTipi);

            miktarTipi.setBirimKodu(commodityEntity.getCommodity().getUnitSet().getEinvoiceCode());
            miktarTipi.setValue(commodityEntity.getQuantity().getAmount().setScale(2, RoundingMode.CEILING));
            faturaSatirTipi.setMiktar(miktarTipi);

            birimFiyat.setParaBirimi(getParaBirimi(entity));
            birimFiyat.setValue(commodityEntity.getPrice().setScale(2, RoundingMode.CEILING));
            faturaSatirTipi.setBirimFiyat(birimFiyat);

            malHizmetMiktari.setParaBirimi(getParaBirimi(entity));
            malHizmetMiktari.setValue(commodityEntity.getTotal().setScale(2, RoundingMode.CEILING));
            faturaSatirTipi.setMalHizmetMiktari(malHizmetMiktari);

            malHizmetBilgileri.setAdi(commodityEntity.getCommodity().getName());
            faturaSatirTipi.setMalHizmetBilgileri(malHizmetBilgileri);

            if (commodityEntity.getDiscountRate() != 0) {
                iskontoArtirimTipi.setArtirim(false);
                iskontoArtirimTipi.setOran(BigDecimal.valueOf(commodityEntity.getDiscountRate() * 0.01));
                iskontoTutarTipi.setParaBirimi(getParaBirimi(entity));
                iskontoTutarTipi.setValue(commodityEntity.getDiscount().setScale(2, RoundingMode.CEILING));
                iskontoArtirimTipi.setTutar(iskontoTutarTipi);
                iskontoArtirimTipi.setUygulandigiTutar(malHizmetMiktari);
                faturaSatirTipi.getIskontoArtirim().add(iskontoArtirimTipi);
            }

            BigDecimal toplamVergiTutariBirim = new BigDecimal(0);

            TaxDefinition tax1 = commodityEntity.getCommodity().getTax1();
            toplamVergiTutariBirim = setTax(vergilerTipi, tax1, commodityEntity, entity, toplamVergiTutariBirim);

            TaxDefinition tax2 = commodityEntity.getCommodity().getTax2();
            toplamVergiTutariBirim = setTax(vergilerTipi, tax2, commodityEntity, entity, toplamVergiTutariBirim);

            TaxDefinition tax3 = commodityEntity.getCommodity().getTax3();
            toplamVergiTutariBirim = setTax(vergilerTipi, tax3, commodityEntity, entity, toplamVergiTutariBirim);

            toplamVergiTutari.setValue(toplamVergiTutariBirim);
            toplamVergiTutari.setParaBirimi(getParaBirimi(entity));
            vergilerTipi.setToplamVergiTutari(toplamVergiTutari);

            faturaSatirTipi.setVergiler(vergilerTipi);

            fatura.getFaturaSatir().add(faturaSatirTipi);
        }

        File file = new File("einvoiceFile.xml");

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Fatura.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(fatura, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return file;
    }

    private BigDecimal setTax(VergilerTipi vergilerTipi, TaxDefinition tax, InvoiceItem commodityEntity, SalesInvoice
            entity, BigDecimal toplamVergiTutari) {
        if (tax != null) {
            VergiTipi vergiTipi = new VergiTipi();
            VergiTuruTipi vergiTuruTipi = new VergiTuruTipi();
            TutarTipi vergiTutari = new TutarTipi();
            TutarTipi matrah = new TutarTipi();

            vergiTuruTipi.setVergiAdi(String.valueOf(tax.getType()));
            vergiTuruTipi.setVergikodu(tax.getEinvoiceCode());
            vergiTipi.setVergiTuru(vergiTuruTipi);

            vergiTipi.setOran(tax.getRate());

            matrah.setParaBirimi(getParaBirimi(entity));
            matrah.setValue(commodityEntity.getLineTotal().setScale(2, RoundingMode.CEILING));
            vergiTipi.setMatrah(matrah);

            vergiTutari.setParaBirimi(getParaBirimi(entity));
            BigDecimal vergiTutariBirim = commodityEntity.getLineTotal().setScale(2, RoundingMode.CEILING).multiply
                    (tax.getRate().multiply(BigDecimal.valueOf(0.01))).setScale(2, RoundingMode.CEILING);
            vergiTutari.setValue(vergiTutariBirim);
            vergiTipi.setVergiTutari(vergiTutari);

            toplamVergiTutari = toplamVergiTutari.add(vergiTutariBirim);

            vergilerTipi.getVergi().add(vergiTipi);
        }
        return toplamVergiTutari;
    }

    private BigDecimal setTotalTax(InvoiceSummary taxEntity, SalesInvoice entity, BigDecimal
            toplamVergiTutariVergiler, VergilerTipi toplamVergilerTipi) {
        VergiTuruTipi vergiTuruTipi = new VergiTuruTipi();
        VergiTipi vergiTipi = new VergiTipi();
        TutarTipi vergiTutari = new TutarTipi();
        TutarTipi vergiMatrah = new TutarTipi();

        vergiMatrah.setParaBirimi(getParaBirimi(entity));
        vergiMatrah.setValue(entity.getSummaries().get(VERGI_HARIC_TUTAR).getAmount().setScale(2, RoundingMode
                .CEILING));
        vergiTipi.setMatrah(vergiMatrah);

        vergiTuruTipi.setVergikodu(taxEntity.getCode());
        vergiTuruTipi.setVergiAdi(taxEntity.getBaseName());
        vergiTipi.setVergiTuru(vergiTuruTipi);

        vergiTutari.setParaBirimi(getParaBirimi(entity));
        vergiTutari.setValue(taxEntity.getAmount().setScale(2, RoundingMode.CEILING));
        vergiTipi.setVergiTutari(vergiTutari);

        toplamVergiTutariVergiler = toplamVergiTutariVergiler.add(taxEntity.getAmount());

        toplamVergilerTipi.getVergi().add(vergiTipi);

        return toplamVergiTutariVergiler;
    }

    private String getParaBirimi(SalesInvoice entity) {
        String invoiceCurrency = entity.getCurrency().toString();
        if (invoiceCurrency.equalsIgnoreCase("TRY")) {
            invoiceCurrency = "TRL";
        }
        return invoiceCurrency;
    }

    private String getSaticiVKN(Kahve kahve) {
        return kahve.get(SATICI_VKN, "").getAsString();
    }
}