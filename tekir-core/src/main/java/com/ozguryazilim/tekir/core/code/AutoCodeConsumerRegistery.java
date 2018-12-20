package com.ozguryazilim.tekir.core.code;

import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author oyas
 */
public class AutoCodeConsumerRegistery {
    
    
    
    private static final Map<String, AutoCodeMetaModel> consumers = new HashMap<>();
    
    public static void register( AutoCode autoCode, Class clazz ){
     
        AutoCodeMetaModel meta = new AutoCodeMetaModel();
        
        String consumer = autoCode.cosumer();
        if( Strings.isNullOrEmpty(consumer)){
            consumer = clazz.getSimpleName();
        }
        
        meta.setCosumer(consumer);
        
        meta.setConfigurable(autoCode.configurable());
        meta.setSize(autoCode.size());
        meta.setType(autoCode.type());
        meta.setSerial(autoCode.serial());
        
        String cap = autoCode.caption();
        if( Strings.isNullOrEmpty(cap)){
            cap = "autoCode.consumer." + meta.getCosumer();
        }
        
        meta.setCaption(cap);
        
        
        consumers.put(meta.getCosumer(), meta);
        
    }

    public static List<AutoCodeMetaModel> getConsumers() {
        return new ArrayList<>(consumers.values());
    }
    
    
}
