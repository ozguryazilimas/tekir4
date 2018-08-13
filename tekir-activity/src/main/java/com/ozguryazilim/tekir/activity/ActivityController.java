package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.entities.Activity;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;

/**
 * ActivityController Controller işaretlemek için kullanılır.
 *
 * @author Hakan Uygun
 */
@Stereotype
@GroupedConversationScoped
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Named
@Documented
public @interface ActivityController {

    Class<? extends Activity> activity();
    Class<? extends ViewConfig> editor() default ViewConfig.class;
    Class<? extends ViewConfig> viewer();
    Class<? extends ViewConfig> optionalPanel() default ViewConfig.class;

}
