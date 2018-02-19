package com.ozguryazilim.tekir.einvoice;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.einvoice.model.*;
import com.ozguryazilim.tekir.entities.InvoiceItem;
import com.ozguryazilim.tekir.entities.InvoiceSummary;
import com.ozguryazilim.tekir.entities.SalesInvoice;
import com.ozguryazilim.tekir.entities.TaxDefinition;
import com.ozguryazilim.tekir.voucher.utils.VoucherItemUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class EinvoiceBuilder {
    EinvoiceBuilderGetters getters = new EinvoiceBuilderGetters();
    EinvoiceSender sender = new EinvoiceSender();

    public void buildEinvoice(SalesInvoice entity, Kahve kahve) throws Exception{

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


        fatura.setFaturaTuru(getters.getFaturaTuru());
        fatura.setFaturaTipi(getters.getFaturaTipi());
        fatura.setFaturaTarihi(getters.getFaturaTarihi(entity));
        fatura.setParaBirimi(getters.getParaBirimi(entity));

        saticiVKN.setSchemeId(getters.getVKNKodu());
        saticiVKN.setValue(getters.getSaticiVKN(kahve));
        saticiTarafBilgileriTipi.getAliciSaticiTanimi().add(saticiVKN);

        saticiTarafBilgileriTipi.setUnvan(getters.getSaticiUnvan(kahve));

        saticiTarafBilgileriTipi.setVergiDairesi(getters.getSaticiVergiDairesi(kahve));

        saticiAdresTipi.setIlce(getters.getSaticiIlce(kahve));
        saticiAdresTipi.setSehir(getters.getSaticiSehir(kahve));
        saticiAdresTipi.setUlke(getters.getSaticiUlke(kahve));
        saticiAdresTipi.setPostaKodu(getters.getSaticiPostaKodu(kahve));
        saticiAdresTipi.setCaddeSokak(getters.getSaticiAdres(kahve));
        saticiTarafBilgileriTipi.setPostaAdresi(saticiAdresTipi);

        fatura.setSatici(saticiTarafBilgileriTipi);

        aliciVKN.setSchemeId(getters.getVKNKodu());
        aliciVKN.setValue(getters.getAliciVKN(entity));
        aliciTarafBilgileriTipi.getAliciSaticiTanimi().add(aliciVKN);

        aliciTarafBilgileriTipi.setUnvan(getters.getAliciUnvan(entity));

        aliciTarafBilgileriTipi.setVergiDairesi(getters.getAliciVergiDairesi(entity));

        aliciAdresTipi.setIlce(getters.getAliciIlce(entity));
        aliciAdresTipi.setSehir(getters.getAliciSehir(entity));
        aliciAdresTipi.setUlke(getters.getAliciUlke(entity));
        aliciAdresTipi.setPostaKodu(getters.getAliciPostaKodu(entity));
        aliciAdresTipi.setCaddeSokak(getters.getAliciAdres(entity));
        aliciTarafBilgileriTipi.setPostaAdresi(aliciAdresTipi);
        fatura.setAlici(aliciTarafBilgileriTipi);

        toplamMalHizmetTutari.setParaBirimi(getters.getParaBirimi(entity));
        toplamMalHizmetTutari.setValue(getters.getToplamMalHizmetTutari(entity));
        parasalToplamlarTipi.setToplamMalHizmetTutari(toplamMalHizmetTutari);

        vergiHaricTutar.setParaBirimi(getters.getParaBirimi(entity));
        vergiHaricTutar.setValue(getters.getVergiHaricTutar(entity));
        parasalToplamlarTipi.setVergiHaricTutar(vergiHaricTutar);

        toplamIskontoTutari.setParaBirimi(getters.getParaBirimi(entity));
        toplamIskontoTutari.setValue(getters.getToplamIskontoTutari(entity));
        parasalToplamlarTipi.setToplamIskontoTutari(toplamIskontoTutari);

        odenecekTutar.setParaBirimi(getters.getParaBirimi(entity));
        odenecekTutar.setValue(getters.getOdenecekTutar(entity));
        parasalToplamlarTipi.setVergiDahilTutar(odenecekTutar);
        parasalToplamlarTipi.setOdenecekTutar(odenecekTutar);

        fatura.setParasalToplamlar(parasalToplamlarTipi);

        List<InvoiceSummary> taxes = VoucherItemUtils.getTaxes(entity.getSummaries());
        BigDecimal toplamVergiTutariVergiler = new BigDecimal("0");
        VergilerTipi toplamVergilerTipi = new VergilerTipi();
        TutarTipi tutarTipi = new TutarTipi();
        int taxCount = taxes.size();
        for (int i=0; i<taxCount; i++){
            InvoiceSummary taxEntity = taxes.get(i);
            toplamVergiTutariVergiler = setTotalTax(taxEntity, entity, toplamVergiTutariVergiler, toplamVergilerTipi);
        }
        tutarTipi.setParaBirimi(getters.getParaBirimi(entity));
        tutarTipi.setValue(toplamVergiTutariVergiler.setScale(2, RoundingMode.CEILING));
        toplamVergilerTipi.setToplamVergiTutari(tutarTipi);

        fatura.getVergiler().add(toplamVergilerTipi);


        int listCount = entity.getItems().size();
        for (int i=0; i<listCount; i++){
            InvoiceItem commodityEntity = getters.getFaturaSatir(entity,i);

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

            idTipi.setValue(String.valueOf(i+1));
            faturaSatirTipi.setSiraNo(idTipi);

            miktarTipi.setBirimKodu(getters.getMiktarBirimKodu(commodityEntity));
            miktarTipi.setValue(getters.getMiktarTipi(commodityEntity));
            faturaSatirTipi.setMiktar(miktarTipi);

            birimFiyat.setParaBirimi(getters.getParaBirimi(entity));
            birimFiyat.setValue(getters.getBirimFiyat(commodityEntity));
            faturaSatirTipi.setBirimFiyat(birimFiyat);

            malHizmetMiktari.setParaBirimi(getters.getParaBirimi(entity));
            malHizmetMiktari.setValue(getters.getMalHizmetMiktari(commodityEntity));
            faturaSatirTipi.setMalHizmetMiktari(malHizmetMiktari);

            malHizmetBilgileri.setAdi(getters.getMalHizmetBilgileriAdi(commodityEntity));
            faturaSatirTipi.setMalHizmetBilgileri(malHizmetBilgileri);

            if (getters.getIskontoOran(commodityEntity) != 0){
                iskontoArtirimTipi.setArtirim(false);
                iskontoArtirimTipi.setOran(BigDecimal.valueOf(getters.getIskontoOran(commodityEntity) * 0.01));
                iskontoTutarTipi.setParaBirimi(getters.getParaBirimi(entity));
                iskontoTutarTipi.setValue(getters.getIskontoTutarTipi(commodityEntity));
                iskontoArtirimTipi.setTutar(iskontoTutarTipi);
                iskontoArtirimTipi.setUygulandigiTutar(malHizmetMiktari);
                faturaSatirTipi.getIskontoArtirim().add(iskontoArtirimTipi);
            }

            BigDecimal toplamVergiTutariBirim = new BigDecimal(0);

            TaxDefinition tax1 = getters.getTax1(commodityEntity);
            toplamVergiTutariBirim = setTax(vergilerTipi, tax1, commodityEntity, entity, toplamVergiTutariBirim);

            TaxDefinition tax2 = getters.getTax2(commodityEntity);
            toplamVergiTutariBirim = setTax(vergilerTipi, tax2, commodityEntity, entity, toplamVergiTutariBirim);

            TaxDefinition tax3 = getters.getTax3(commodityEntity);
            toplamVergiTutariBirim = setTax(vergilerTipi, tax3, commodityEntity, entity, toplamVergiTutariBirim);

            toplamVergiTutari.setValue(toplamVergiTutariBirim);
            toplamVergiTutari.setParaBirimi(getters.getParaBirimi(entity));
            vergilerTipi.setToplamVergiTutari(toplamVergiTutari);

            faturaSatirTipi.setVergiler(vergilerTipi);

            fatura.getFaturaSatir().add(faturaSatirTipi);
        }

        File file = new File("einvoiceFile.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(Fatura.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(fatura, file);

        sender.sendEinvoice(file, getters.getSaticiVKN(kahve), getters.getBelgeNo(entity));
    }

    public BigDecimal setTax(VergilerTipi vergilerTipi, TaxDefinition tax, InvoiceItem commodityEntity, SalesInvoice entity, BigDecimal toplamVergiTutari) {
        if (tax != null){
            VergiTipi vergiTipi = new VergiTipi();
            VergiTuruTipi vergiTuruTipi = new VergiTuruTipi();
            TutarTipi vergiTutari = new TutarTipi();
            TutarTipi matrah = new TutarTipi();

            vergiTuruTipi.setVergiAdi(String.valueOf(tax.getType()));
            vergiTuruTipi.setVergikodu(tax.getEinvoiceCode());
            vergiTipi.setVergiTuru(vergiTuruTipi);

            vergiTipi.setOran(tax.getRate());

            matrah.setParaBirimi(getters.getParaBirimi(entity));
            matrah.setValue(getters.getMatrah(commodityEntity));
            vergiTipi.setMatrah(matrah);

            vergiTutari.setParaBirimi(getters.getParaBirimi(entity));
            BigDecimal vergiTutariBirim = getters.getMatrah(commodityEntity).multiply(tax.getRate().multiply(BigDecimal.valueOf(0.01))).setScale(2, RoundingMode.CEILING);
            vergiTutari.setValue(vergiTutariBirim);
            vergiTipi.setVergiTutari(vergiTutari);

            toplamVergiTutari = toplamVergiTutari.add(vergiTutariBirim);

            vergilerTipi.getVergi().add(vergiTipi);
        }
        return toplamVergiTutari;
    }

    public BigDecimal setTotalTax(InvoiceSummary taxEntity, SalesInvoice entity, BigDecimal toplamVergiTutariVergiler, VergilerTipi toplamVergilerTipi){
        VergiTuruTipi vergiTuruTipi = new VergiTuruTipi();
        VergiTipi vergiTipi = new VergiTipi();
        TutarTipi vergiTutari = new TutarTipi();
        TutarTipi vergiMatrah = new TutarTipi();

        vergiMatrah.setParaBirimi(getters.getParaBirimi(entity));
        vergiMatrah.setValue(getters.getVergiHaricTutar(entity));
        vergiTipi.setMatrah(vergiMatrah);

        vergiTuruTipi.setVergikodu(getters.getToplamVergiKodu(taxEntity));
        vergiTuruTipi.setVergiAdi(getters.getToplamVergiAdi(taxEntity));
        vergiTipi.setVergiTuru(vergiTuruTipi);

        vergiTutari.setParaBirimi(getters.getParaBirimi(entity));
        vergiTutari.setValue(getters.getToplamVergiTutari(taxEntity));
        vergiTipi.setVergiTutari(vergiTutari);

        toplamVergiTutariVergiler = toplamVergiTutariVergiler.add(taxEntity.getAmount());

        toplamVergilerTipi.getVergi().add(vergiTipi);

        return toplamVergiTutariVergiler;
    }
}
