/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.code;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;

/**
 * AutoCode için CDI extention.
 * 
 * @author Hakan Uygun
 */
public class AutoCodeExtention implements Extension{
    
    /**
     * AutoCode için yapılan tanımları toparlar.
     * 
     * @param <T>
     * @param pat 
     */
    <T> void processAnnotatedType(@Observes @WithAnnotations(AutoCode.class) ProcessAnnotatedType<T> pat) {
        AutoCode a = pat.getAnnotatedType().getAnnotation(AutoCode.class);
        AutoCodeConsumerRegistery.register(a, pat.getAnnotatedType().getJavaClass());
    }
}
