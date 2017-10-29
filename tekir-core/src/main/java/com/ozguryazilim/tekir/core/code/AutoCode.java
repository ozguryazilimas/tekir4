/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.code;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.inject.Stereotype;

/**
 * Otomatik kod üretici için register tanımları.
 * 
 * Genelde ParametreHome sınıflarında ya da Feature tanımlarında kullanılır.
 * 
 * Burada tanımlanan değerler varsayılan değerler olacak. 
 * Kullanıcı ayarlardan değişiklik yaparak kullanım biçmini değiştirebilir.
 * Kullanıcı değişiklikleri kahve'de saklanacak.
 * 
 * @see AutoCodeService 
 * @see AutoCodeOptionPane
 * 
 * @author Hakan Uygun
 */
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface AutoCode {
    
    /**
     * Eğer verilmez ise üzerinde tanımlandığı sınıfın adını kullanır
     * @return 
     */
    String cosumer() default "";
    
    /**
     * Bu kod üretici için varsayılan davranış.
     * @return 
     */
    AutoCodeType type() default AutoCodeType.SEMIAUTOMATIC;
    
    /**
     * Bu ayarların kullanıcı tarafından değiştirilip değiştirilemiyeceği
     * @return 
     */
    boolean configurable() default true;
    
    /**
     * Caption için kullanılacak message-key.
     * 
     * Eğer verilmez ise autoCode.consumer.{consumer()} değeri kullanılır.
     * @return 
     */
    String caption() default "";
    
    /**
     * Sıra numarasının varsayılan uzunluk bilgisi.
     * 
     * Varsayılan 6
     * 
     * @return 
     */
    int size() default 6;
    
    /**
     * Üretilecek kod için varsayılan prefix.
     * 
     * Eğer verilmez ise comsumer değerinin ilk üç karakteri kullanılır.
     * @return 
     */
    String serial() default "";
}
