/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

import javax.persistence.Column;

/**
 * E-Posta gönderim activitesi
 * 
 * TODO: Aslında burada karar verilmesi gereken bazı şeyler var.
 * Örneğin : 
 *  - E-posta istemcisi ile entegrasyon. 
 *  - Bu verilerin cc/bcc üzerinden gelecek bir adresi dinleyip otomatik eklenmesi
 *  - Uygulama içinden e-posta göndermek?
 * 
 * FIXME: Şimdilik tablo kısmını pass mı geçsek?
 * 
 * @author Hakan Uygun
 */
public class EMailActivity extends Activity {
    
    /**
     * İletişim için kullanılan/kullanılacak olan e-posta adresi
     */
    @Column(name = "EMAIL")
    private String email;
    
    //E-posta üzerindeki adres bilgileri
    @Column(name = "EM_FROM")
    private String from;
    @Column(name = "EM_TO")
    private String to;
    @Column(name = "EM_CC")
    private String cc;
    @Column(name = "EM_BCC")
    private String bcc;
    
    //E-postanın raw halini saklamak nasıl bir fikir?
    @Column(name = "EM_DATA")
    private String raw;
 
}
