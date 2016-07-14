/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.feed;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;

/**
 * Feeder işaretleyici
 * 
 * ApplicationScope'da yaşarlar. Fazla maliyetli değiller ve ikide bir oluşturulmaları gerekiyor
 * Ayrıca arkaplan işlerde de kullanılmaları gerekebilir.
 * 
 * @author Hakan Uygun
 */
@Stereotype
@ApplicationScoped
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Named
@Documented
public @interface Feeder {
    
    /**
     * Feeder'in UI için kullanacağı icon font
     * 
     * Örneğin: "fa fa-user"
     * 
     * @return 
     */
    String icon();
}
