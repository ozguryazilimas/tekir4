/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;

/**
 * ActivityController extention
 * 
 * @author Hakan Uygun
 */
public class ActivityExtention implements Extension{
    
    /**
     * ActivityController ile işaretli sınıfları bulup ActivityRegistery'e yerleştirir.
     * @param <T>
     * @param pat 
     */
    <T extends AbstractActivityController> void processAnnotatedType(@Observes @WithAnnotations(ActivityController.class) ProcessAnnotatedType<T> pat) {

        ActivityController a = pat.getAnnotatedType().getAnnotation(ActivityController.class);
        
        ActivityRegistery.register(a, pat.getAnnotatedType().getJavaClass());
    }
}
