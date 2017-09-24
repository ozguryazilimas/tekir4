/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.code;

import com.google.common.base.Strings;
import com.ozguryazilim.mutfak.kahve.Kahve;
import com.ozguryazilim.mutfak.kahve.KahveEntry;
import com.ozguryazilim.telve.feature.FeatureHandler;
import com.ozguryazilim.telve.sequence.SequenceManager;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Uygulama içerisinde Unique Code ihtiyacı olan yerler için code service.
 * 
 * Parametre, Kart gibi Unique bir bizKey'e ihtiyaç duyan ama kullanıcının girişinin zor olduğu ya da zorlamanın çok anlamlı olmadığı yerler için kod üretir.
 * 
 * 
 * - Her consumer kendini register eder @see AutoCode, AutoCodeConsumerRegistery#register
 * - Consumer başına bir Seri tanımı alınır ( UI üzerinden bu bilgi değiştirilebilir )
 * - SerialService ile istenilen consumer için kod üretii yapılır.
 * - Bazı consumer'lar için otomatik üretim kullanıcı tarafından kapatılabilir!
 * - Cosumer'lar ayrıca i18n için dil dosyalarını da doldurmalılar.
 * 
 * 
 * Consumerlar kendilerini register ederken mümkünse Feature bilgisi ile register ederler ise TypeSafe olabilir. ( Parametreler için Feature tanımı yok şu an )
 * 
 * TODO: Farklı kurallar ile otomatik kod üretimi için eklemeler yapılacak. Örneğin sadece bir serial değil yıl-ay gibi bilgiler ile seri kısmını üretmek gibi.
 * 
 * FIXME: cluster ortamda consumer map'in sync sorunu olacak!!!!
 * 
 * @author Hakan Uygun
 */
@ApplicationScoped
public class AutoCodeService implements Serializable{
    
    private static final String TYPE_KEY = "autoCode.type.";
    private static final String SIZE_KEY = "autoCode.size.";
    private static final String SERIAL_KEY = "autoCode.serial.";
    
    @Inject
    private SequenceManager sequenceManager;
    
    @Inject
    private Kahve kahve;
    
    private Map<String, AutoCodeMetaModel> consumers = new HashMap<>();
    
    @PostConstruct
    public void init(){
        buildAutoCodeMetaModels();
    }
    
    public String getNewSerialNumber( FeatureHandler featureHandler){
        String fhn = featureHandler.getName();
        
        return getNewSerialNumber(fhn);
    }
    
    
    public String getNewSerialNumber( String consumer ){
        AutoCodeMetaModel meta = consumers.get(consumer);
        
        if( meta == null ){
            return "";
        }
        
        //Eğer manual ise geriye kod döndürülmeyecek demek
        if( meta.getType() == AutoCodeType.MANUAL ){
            return "";
        }
        
        return sequenceManager.getNewSerialNumber(meta.getSerial(), meta.getSize());
    }
    
    
    /**
     * Ayarlardan değil farklı bir kanaldan key kullanıyor.
     * 
     * Özellikle ağaç tipi parametrelerde serial olarak parent'ın code kullanılacak.
     * 
     * @param consumer
     * @param serial
     * @return 
     */
    public String getNewSerialNumber( String consumer, String serial ){
        AutoCodeMetaModel meta = consumers.get(consumer);
        
        if( meta == null ){
            return "";
        }
        
        //Eğer manual ise geriye kod döndürülmeyecek demek
        if( meta.getType() == AutoCodeType.MANUAL ){
            return "";
        }

        //Meta üzerinde ki mi yoksa parametre mi kullanılacak?
        String val;
        if( !Strings.isNullOrEmpty(serial)){
            val = serial;
        } else {
            val = meta.getSerial();
        }
        
        return sequenceManager.getNewSerialNumber(val, meta.getSize());
    }
    
    
    /**
     * Registery'den aldığı bilgiler ile kahve'den gelenleri birleştirir.
     * 
     * Böylece güncel bilgileri döndürür.
     * 
     * Type, Size ve Serial kullanıcı tarafından değiştirilebilir.
     */
    protected void buildAutoCodeMetaModels(){
        for( AutoCodeMetaModel meta : AutoCodeConsumerRegistery.getConsumers() ){
    
            //Kahve'den alınanlar ile meta güncellenecek.
            //Tip kontrolü
            KahveEntry ent = kahve.get(TYPE_KEY + meta.getCosumer());
            if( ent != null ){
                meta.setType( AutoCodeType.valueOf(ent.getAsString()));
            }
        
            //Size kontrolü
            ent = kahve.get(SIZE_KEY + meta.getCosumer());
            if( ent != null ){
                meta.setSize(ent.getAsInteger());
            }
            
            //Serial kontrolü
            ent = kahve.get(SERIAL_KEY + meta.getCosumer());
            if( ent != null ){
                meta.setSerial(ent.getAsString());
            } else {
                meta.setSerial(meta.getCosumer().substring(0, 3).toUpperCase());
            }
            consumers.put(meta.getCosumer(), meta);
            
        }
    }
    
    /**
     * Eldeki meta modelleri kahve'ye kaydeder.
     */
    public void save(){
        for( AutoCodeMetaModel meta : consumers.values()){
            kahve.put(TYPE_KEY + meta.getCosumer(), meta.getType().name());
            kahve.put(SIZE_KEY + meta.getCosumer(), meta.getSize());
            kahve.put(SERIAL_KEY + meta.getCosumer(), meta.getSerial());

        }
    }

    public Map<String, AutoCodeMetaModel> getConsumers() {
        return consumers;
    }
    
    
}
