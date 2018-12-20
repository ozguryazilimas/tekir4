package com.ozguryazilim.tekir;

import com.ozguryazilim.telve.attachment.AttachmentException;
import com.ozguryazilim.telve.attachment.AttachmentStore;
import com.ozguryazilim.telve.attachment.AttachmentStoreConfig;
import com.ozguryazilim.telve.attachment.modeshape.AttachmentModeShapeStore;
import com.ozguryazilim.telve.attachment.qualifiers.FileStore;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oyas
 */
@ApplicationScoped
public class AttachmentStoreProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(AttachmentStoreProducer.class);
    
    @Produces
    public AttachmentStoreConfig produceStoreCOnfig(){
        AttachmentStoreConfig config = new AttachmentStoreConfig() {
            @Override
            public boolean canSupportFeature(String featureName, String storeName) {
                return true;
            }
        };
                
        return config;
    }
    
    @Produces
    @FileStore
    @ApplicationScoped
    public AttachmentStore fileStoreProducer(){
        
        try {
            AttachmentStore store = new AttachmentModeShapeStore();
            store.start();
            
            return store;
        } catch (AttachmentException ex) {
            LOG.error( "Store cannot initialized!", ex);
            throw new RuntimeException("Store cannot initialized!", ex);
        }
    }
    
    
}
