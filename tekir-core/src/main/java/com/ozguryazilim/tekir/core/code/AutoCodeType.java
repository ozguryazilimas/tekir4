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
