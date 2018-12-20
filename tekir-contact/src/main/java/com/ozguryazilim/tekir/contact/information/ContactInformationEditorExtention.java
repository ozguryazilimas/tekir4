package com.ozguryazilim.tekir.contact.information;

import com.google.common.base.CaseFormat;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ContactInformationEditor extention
 * @author Hakan Uygun
 */
public class ContactInformationEditorExtention implements Extension{
    
    private static final Logger LOG = LoggerFactory.getLogger(ContactInformationEditorExtention.class);
    
    /**
     * SubView ile işaretli sınıfları bulup SubViewRegistery'e yerleştirir.
     * @param <T>
     * @param pat 
     */
    <T> void processAnnotatedType(@Observes @WithAnnotations(ContactInformationEditor.class) ProcessAnnotatedType<T> pat) {
        

        ContactInformationEditor a = pat.getAnnotatedType().getAnnotation(ContactInformationEditor.class);
        
        String beanName = pat.getAnnotatedType().getJavaClass().getSimpleName();
        beanName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, beanName);
        
        
        LOG.debug("Registered ContactInformationEditor {}", beanName );
        ContactInformationEditorRegistery.register(beanName, a);
    }
}
