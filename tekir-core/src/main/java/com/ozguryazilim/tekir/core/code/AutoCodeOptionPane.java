/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.code;

import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.config.AbstractOptionPane;
import com.ozguryazilim.telve.config.OptionPane;
import com.ozguryazilim.telve.config.OptionPaneType;
import com.ozguryazilim.telve.messages.FacesMessages;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Otomatik Numara üretim ayar sayfası control sınıfı.
 * 
 * @author Hakan Uygun
 */
@OptionPane( permission = "autoCodeSerial", optionPage = CorePages.AutoCodeOptionPane.class, type = OptionPaneType.System)
public class AutoCodeOptionPane extends AbstractOptionPane{
    
    @Inject
    private Kahve kahve;
    
    @Inject
    private AutoCodeService autoCodeService;
    
    private final List<AutoCodeMetaModel> consumers = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        //FIXME: configurable olanlar sadece gösterilecek.
        consumers.addAll(autoCodeService.getConsumers().values());
    }

    public List<AutoCodeMetaModel> getConsumers() {
        return consumers;
    }

    @Override
    public void save() {
        autoCodeService.save();
        FacesMessages.info("facesMessages.info.ChangesSuccessfullySaved");
    }
    
    
}
