package com.ozguryazilim.tekir.core.config;

import com.ozguryazilim.tekir.core.unitset.UnitSetDefinitionRepository;
import com.ozguryazilim.tekir.entities.UnitSetDefinition;
import com.ozguryazilim.tekir.entities.UnitSetItem;
import com.ozguryazilim.telve.unit.QuantitativeAmount;
import com.ozguryazilim.telve.unit.UnitName;
import com.ozguryazilim.telve.unit.UnitSetBuilder;
import com.ozguryazilim.telve.unit.UnitSetRegistery;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Veri tabanına tanımlanmış olan UnitSet'leri telve-unit'e register eder.
 * 
 * 
 * Uygulama ayağa kalkerken ve istenildiğinde bir event ile devreye girer.
 * @author Hakan Uygun
 */
@Singleton
@Startup
public class UnitSetRegisteryManager {
    
    private static final Logger LOG = LoggerFactory.getLogger(UnitSetRegisteryManager.class);
    
    @Inject
    private UnitSetDefinitionRepository repository;
    
    @PostConstruct
    public void init(){
        registerUnitSets();
    }

    private void registerUnitSets() {
        LOG.debug("UnitSet Registration begin");
        List<UnitSetDefinition> usl = repository.findAllActives();
        
        for( UnitSetDefinition us : usl ){
            LOG.debug("UnitSet Registration begin for : {}", us.getCode());
            UnitSetBuilder usb = UnitSetBuilder.create(us.getCode(), us.getBaseUnit());
            for( UnitSetItem usi : us.getItems()){
                usb.addUnit(usi.getName(), new QuantitativeAmount(usi.getQuantity().getAmount(), new UnitName(us.getCode(), usi.getQuantity().getUnit())));
            }
            
            usb.register();
        }
    }
    
    
    /**
     * Event dinler ve ardından UnitSet'leri yeniden register eder.
     * @param event 
     */
    public void refreshUnitSets( @Observes RefreshUnitSetsEvent event){
        UnitSetRegistery.clear();
        registerUnitSets();
    }
}
