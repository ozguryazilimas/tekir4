/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
