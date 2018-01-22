package com.ozguryazilim.tekir.entities;

/**
 * Çalışan Alacak Fişi tipleri.
 * FIXME: Burada tipler kontrol edilecek.
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @see 2017-06-30
 */
public enum EmployeeCreditNoteType {
    
    /**
     * Maaş
     */
    SALARY,
    
    /**
     * Masraf
     */
    COST,
    
    /**
     * Tazminat
     */
    COMPENSATION,
    
    /**
     * Avans
     */
    IMPREST, 
    
    /**
     * İkramiye
     */
    BONUS,
    
    /**
     * Diğer
     */
    OTHER
}
