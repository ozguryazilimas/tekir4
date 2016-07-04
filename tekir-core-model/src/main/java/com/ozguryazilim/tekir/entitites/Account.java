/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.entitites;

import com.ozguryazilim.telve.entities.EntityBase;

/**
 * Fişlerde kullanılacak olan müşteri hesapları ( cari )
 * 
 * @author Hakan Uygun
 */
public class Account extends EntityBase{

    
    private Contact contact;
    
    /**
     * Account status
     */
    private String status;
    
    /**
     * İlgili status sebebi
     */
    private String statusReason;
    
    /**
     * Vergi bilgileri. Bireysel kullanıcılar için de lazım olabilir.
     * Bu bilgiler Account'a mı gitse?
     * Suggestion
     */
    private String taxOffice;
    private String taxNumber;
    
    
    /**
     * Banka hesap bilgisi : aslında birden fazla olabilir. Nasıl yapsak?
     */
    private String bankAccount;
    
    /**
     * Hangi döviz ile çalışıldığı ISO kodu olarak tutulacak.
     */
    private String currency;
    
    
    /**
     * Contact üzerinde bir category var ama cari hesap için de ek bir category makul olabilir 
     */
    private String category;
    
    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
