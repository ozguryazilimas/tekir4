package com.ozguryazilim.tekir.contact.information;

import com.ozguryazilim.tekir.entities.ContactInformation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 * ContactInformation editorlar için işaretleyici
 * 
 * @author Hakan Uygun
 */
@Stereotype
@SessionScoped
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Named
@Documented
public @interface ContactInformationEditor {
    
    Class<? extends ContactInformation> handle();
    Class<? extends ViewConfig> page();
    
}
