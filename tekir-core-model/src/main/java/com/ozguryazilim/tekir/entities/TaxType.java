package com.ozguryazilim.tekir.entities;

/**
 * Vergi hesaplama kavramı ve kullanımı açısından filtrelemek için vergi tipleri.
 * 
 * Aslında bu bir enum değil strategy olmalı. Hesaplama modülleri ile birlikte sisteme registger edilmeli.
 * 
 * @author Hakan Uygun
 */
public enum TaxType {
   
    KDV,
    OIV,
    OTV,
    BSMV,
    DV,
    TEVKIFAT
    
}
