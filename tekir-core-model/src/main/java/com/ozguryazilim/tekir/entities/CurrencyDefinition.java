/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import com.ozguryazilim.telve.entities.ParamEntityBase;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Sistem de kullanılacak olan Para Birimi ( Döviz ) tanımları
 * 
 * Burada aslında var olan tanımlardan hangilerinin kullanılacağı seçilecek. 
 * Çünkü CCY tanımları Java içerisinde mevcut durumda olacaklar.
 * Default data olarak code alanları ISO kodu olarak dolu gelecek
 * 
 * Acaba bu bilgiyi kahveye doğru mu kaydırsak? Döviz-Kur çevrimleri felan da olacak sonuçta. Özel kendine has bir yapısı olacak bu işlerin.
 * Ama conf ile olmaz. Kullanıcı seçimli bir yapı olması lazım. 
 * 
 * @author Hakan Uygun
 */
//@Entity
//@Table( name = "TCO_CURRENCY" )
public class CurrencyDefinition extends ParamEntityBase{
    
    @Id @GeneratedValue( strategy = GenerationType.AUTO, generator="genericSeq")
    @Column(name="ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
