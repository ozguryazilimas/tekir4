/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

/**
 * Tüzel Kişi 
 * 
 * Şirket, Organizasyon, Kurum v.s.
 * 
 * @author Hakan Uygun
 */
public class Corporation extends Contact{
    
    //Kurumsal contactlar için kurum tam ünvanı
    private String organizastionName;
    
    /**
     * Şirketler için ana bağlantı. İletişim varsayılan olarak bununla yapılır.
     * Kişiler için şirket bağlantısı. ( Olmayanlar için yukarıdaki companyName kullanılabilir.
     */
    private Person primaryContact;
    
    /**
     * Eğer bir birine bağlı kurumsal hesaplarsa
     */
    private Corporation parentCorporation;
    
    /**
     * Kurum tipi : 
     * 
     * Özel, Kamu, STK, Bilişim, Entegrator? Bunları bir tanım Tablosundan mı alsak?
     */
    private CorporationType corporationType;
    
    
            
}
