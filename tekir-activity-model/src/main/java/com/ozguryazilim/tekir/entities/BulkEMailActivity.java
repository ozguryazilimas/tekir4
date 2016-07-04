/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entities;

/**
 * Toplu e-posta gönderim aktivitesi
 * 
 * @author Hakan Uygun
 */
public class BulkEMailActivity extends Activity{
   
    /**
     * Gönderimin yapıldığı eposta adresi
     */
    private String email;
    
    /**
     * Gönderilim yapılan e-posta kodu.
     * Burası nasıl olsa ki? 3. parti araçlara bir bakmak lazım.
     */
    private String bulkCode;
}
