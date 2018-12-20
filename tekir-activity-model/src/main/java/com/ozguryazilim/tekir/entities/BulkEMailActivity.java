package com.ozguryazilim.tekir.entities;

/**
 * Toplu e-posta gönderim aktivitesi
 * 
 * TODO: Bu aslında kampanya modülüne gidecek olan bir aktivite türü.
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
