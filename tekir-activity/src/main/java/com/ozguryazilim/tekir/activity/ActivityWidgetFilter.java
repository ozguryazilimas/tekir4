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
    FAILED,
    /**
     * FallowUp yapılmış işler
     */
    FOLLOWUP
    
}
