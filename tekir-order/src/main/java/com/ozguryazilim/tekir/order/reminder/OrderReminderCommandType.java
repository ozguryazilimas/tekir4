/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.order.reminder;

/**
 * Sipariş Hatırlatma Komutu için tip.
 * 
 * Sipariş hatırlatma komutunun ilgili tarihe yaklaşırken mi veya 
 * geçtikten sonra mı çalışacağını belirten tip tanımlarıdır.
 * 
 * @author Erdem Uslu
 * @version 4.0.0
 * @since 2017-06-29
 */
public enum OrderReminderCommandType {

    /**
     * Yaklaşanlar
     */
    UPCOMING,
    
    /**
     * Geçenler
     */
    EXPIRED
}
