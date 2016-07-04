/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

/**
 * E-Posta gönderim activitesi
 * 
 * @author Hakan Uygun
 */
public class EMailActivity extends Activity {
    
    /**
     * İletişim için kullanılan e-posta adresi
     */
    private String email;
    
    
    private String from;
    private String to;
    private String cc;
    private String bcc;
    
    //E-postanın raw halini saklamak nasıl bir fikir?
    private String raw;
            
}
