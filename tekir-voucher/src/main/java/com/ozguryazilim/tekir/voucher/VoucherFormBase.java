/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.sequence.SequenceManager;
import java.util.Date;
import javax.inject.Inject;

/**
 * Voucher tabanlı formlar için temel kontrol sınıfı
 * 
 * @author Hakan Uygun
 */
public abstract class VoucherFormBase<E extends VoucherBase> extends FormBase<E, Long>{

    @Inject 
    private Identity identity;

    @Inject
    private SequenceManager sequenceManager;
    
    @Override
    protected E getNewEntity() {
        E e = super.getNewEntity(); //To change body of generated methods, choose Tools | Templates.
        
        e.setDate(new Date());
        //FIXME: Bunu config'den sınıf adına göre almak en temizi olur.
        String s = e.getClass().getSimpleName().substring(0, 2);
        e.setVoucherNo(sequenceManager.getNewSerialNumber(s, 6));
        
        e.setOwner(identity.getLoginName());
        
        return e;
    }
    
    
}
