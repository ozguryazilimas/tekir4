/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

/**
 * Activity'inin durum bilgisi
 *
 * @author Hakan Uygun
 */
public enum ActivityStatus {
    /**
     * Yeni tanım yapılıyor. Henüz Open durumda değil
     */
    DRAFT,
    /**
     * Zamanlanmış durumda. Zamanı ( DUE_DATE ) gelince yapılacak
     */
    SCHEDULED,
    /**
     * Açık durumda bekliyor. Henüz zamanlanmadı
     */
    OPEN,
    /**
     * Başarı ile kapandı
     */
    SUCCESS,
    /**
     * Başarısız kapandı
     */
    FAILD
}
