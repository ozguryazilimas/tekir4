/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

/**
 * Bir Voucher için temel durum bilgileri.
 * 
 * Burada sadece fişin açık, kapalı olup olmadığının bilgileri tutulur.
 * 
 * Farklı fişler kendi durumları için ek durum tanımları yapmalı.
 * 
 * Örneğin Fatura'nın basılıp gönderilmesi, ödemenin parçalı olarak alınması gibi
 * 
 * @author Hakan Uygun
 */
public enum VocuherStatus {
    /**
     * Henüz draft aşamasında. Raporlarda v.s. görünmez.
     */
    DRAFT,
    /**
     * Kayıt açık durumda. 
     */
    OPEN,
    /**
     * Fiş olumlu bir sonuç ile kapanmış
     */
    WON,
    /**
     * Fiş olumsuz bir sonuç ile kapanmış
     */
    LOST,
    /**
     * Fiş iptal edilmiş. ( Bazı durumlarda fişi silmek yerine kullanılır : mesela fatura basımı sırasında sorun çıktı )
     */
    CANCELED
    
}
