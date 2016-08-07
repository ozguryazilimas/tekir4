/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
