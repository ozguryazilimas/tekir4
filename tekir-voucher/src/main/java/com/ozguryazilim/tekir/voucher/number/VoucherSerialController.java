/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.number;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.tekir.voucher.config.VoucherPages;
import com.ozguryazilim.telve.config.AbstractOptionPane;
import com.ozguryazilim.telve.config.OptionPane;
import com.ozguryazilim.telve.config.OptionPaneType;
import com.ozguryazilim.telve.feature.FeatureHandler;
import com.ozguryazilim.telve.messages.FacesMessages;
import com.ozguryazilim.telve.view.Pages;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 * Voucher'lar için SERİ numarasının ne olacağının belirlendiği ViewController.
 * 
 * Verilerini kahve üzerinde tutar.
 * 
 * Voucher listesini Vocuher qualifier ile annotate edilmiş FeatureHandler sınıfları üzerinden toparlar.
 * 
 * @author Hakan Uygun
 */
//@Named
//@SessionScoped
@OptionPane( permission = "voucherSerial", optionPage = VoucherPages.VoucherSerial.class, type = OptionPaneType.System)
public class VoucherSerialController extends AbstractOptionPane{
    
    @Inject @Voucher
    private Instance<FeatureHandler> featureHandlers;
    
    @Inject
    private Kahve kahve;
    
    private List<String> features = new ArrayList<>();
    
    //Değerleri tutar featureName, value
    private Map<String,String> serials = new HashMap<>();
    
    //Caption'ları tutar featureName, featureCaption
    private Map<String,String> captions = new HashMap<>();
    
    @PostConstruct
    public void init(){
        
        for( FeatureHandler fh : featureHandlers ){
            
            String fhn = fh.getName();
            String fhc = fh.getCaption();
            
            captions.put(fhn, fhc);
            
            String val = kahve.get("voucher.serial." + fhn, fhn.substring(0, 3).toUpperCase()).getValue();
            serials.put(fhn, val);
            
            features.add(fhn);
        }
        
    }

    @Override
    public void save(){
        
        for( Map.Entry<String,String> ve : serials.entrySet()){
            kahve.put("voucher.serial." + ve.getKey(), ve.getValue());
        }

        FacesMessages.info("voucher.messages.RecordSaved");
    }
    
    public Class<? extends ViewConfig> close(){
        return Pages.Home.class;
    }
    
    public List<String> getFeatures() {
        return features;
    }

    public Map<String, String> getSerials() {
        return serials;
    }

    public Map<String, String> getCaptions() {
        return captions;
    }
    
    
}
