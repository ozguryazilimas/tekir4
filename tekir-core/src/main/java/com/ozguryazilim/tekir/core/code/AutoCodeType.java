/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.code;

/**
 * Otomatik kod üreticinin nasıl davranacağının varsayılan değeri.
 * 
 * @author Hakan Uygun
 */
public enum AutoCodeType {
   
    /**
     * Kod üretilmez kullanıcı giriş yapar.
     */
    MANUAL,
    /**
     * Otomatik kod üretilir kullanıcı girişine izin verilmez.
     */
    AUTOMATIC,
    /**
     * Otomatik kod üretilir ama kullanıcı isterse değiştirebilir.
     */
    SEMIAUTOMATIC
}
