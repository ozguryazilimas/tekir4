package com.ozguryazilim.tekir;

import com.ozguryazilim.tekir.einvoice.model.*;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class jaxb {

    public static void main(String[] args) throws Exception {
        Fatura fatura = new Fatura();
        TarafBilgileriTipi tarafBilgileriTipi = new TarafBilgileriTipi();
        IdTipi idTipi = new IdTipi();
        AdresTipi adresTipi = new AdresTipi();
        VergilerTipi vergilerTipi = new VergilerTipi();
        TutarTipi tutarTipi = new TutarTipi();
        ParasalToplamlarTipi parasalToplamlarTipi = new ParasalToplamlarTipi();
        FaturaSatirTipi faturaSatirTipi = new FaturaSatirTipi();
        MiktarTipi miktarTipi = new MiktarTipi();
        MalHizmetBilgileriTipi malHizmetBilgileriTipi = new MalHizmetBilgileriTipi();


        fatura.setFaturaTarihi("2018-12-29");
        fatura.setFaturaTuru("TICARIFATURA");
        fatura.setFaturaTipi("SATIS");
        fatura.setFaturaZamani("15:50:24");
        fatura.setParaBirimi("TL");
        idTipi.setValue("idTipiValue");
        idTipi.setSchemeId("idTipiSchemeId");
        tarafBilgileriTipi.getAliciSaticiTanimi().add(idTipi);
        adresTipi.setIlce("ilce");
        adresTipi.setSehir("sehir");
        adresTipi.setUlke("ulke");
        tarafBilgileriTipi.setPostaAdresi(adresTipi);
        fatura.setAlici(tarafBilgileriTipi);
        fatura.setSatici(tarafBilgileriTipi);
        tutarTipi.setParaBirimi("paraBirimi");
        vergilerTipi.setToplamVergiTutari(tutarTipi);
        fatura.getVergiler().add(vergilerTipi);
        parasalToplamlarTipi.setToplamMalHizmetTutari(tutarTipi);
        parasalToplamlarTipi.setVergiHaricTutar(tutarTipi);
        parasalToplamlarTipi.setVergiDahilTutar(tutarTipi);
        parasalToplamlarTipi.setOdenecekTutar(tutarTipi);
        fatura.setParasalToplamlar(parasalToplamlarTipi);
        faturaSatirTipi.setSiraNo(idTipi);
        miktarTipi.setBirimKodu("birimKodu");
        faturaSatirTipi.setMiktar(miktarTipi);
        faturaSatirTipi.setMalHizmetMiktari(tutarTipi);
        malHizmetBilgileriTipi.setAdi("malHBAdi");
        faturaSatirTipi.setMalHizmetBilgileri(malHizmetBilgileriTipi);
        faturaSatirTipi.setBirimFiyat(tutarTipi);
        fatura.getFaturaSatir().add(faturaSatirTipi);

        File file = new File("file2.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Fatura.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(fatura, file);
        jaxbMarshaller.marshal(fatura, System.out);

    }
}
