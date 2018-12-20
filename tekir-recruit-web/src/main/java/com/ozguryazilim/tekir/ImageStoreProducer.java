package com.ozguryazilim.tekir;

import com.ozguryazilim.telve.image.jpa.SimpleImageJpaStore;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ozguryazilim.telve.image.ImageStore;

/**
 *
 * @author oyas
 */
@ApplicationScoped
public class ImageStoreProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(ImageStoreProducer.class);
    
    @Produces
    @ApplicationScoped
    public ImageStore imageStoreProducer(){
        
        ImageStore store = new SimpleImageJpaStore();
        LOG.info("Image JPA Store initialized!");
        return store;
        
    }
 
}
