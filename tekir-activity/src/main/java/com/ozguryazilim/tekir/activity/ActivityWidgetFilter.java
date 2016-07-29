/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

/**
 * Activity Widget üzerinde kullanılabilecek olan filtre türleri
 * @author Hakan Uygun
 */
public enum ActivityWidgetFilter {
    /**
     * Hepsi
     */
    ALL,
    /**
     * Benim işler
     */
    MINE,
    /**
     * Zamanı aşan işler
     */
    OVERDUE,
    /**
     * Zamanlanmış açık işler
     */
    SCHEDULED,
    /**
     * Başarı ile kapanmış işler
     */
    SUCCESS,
    
    /**
     * Başarısız kapanmış işler
     */
    FAILD,
    /**
     * FallowUp yapılmış işler
     */
    FOLLOWUP
    
}
