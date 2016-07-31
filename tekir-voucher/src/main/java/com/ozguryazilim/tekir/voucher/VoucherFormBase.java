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
import org.apache.deltaspike.core.api.config.view.ViewConfig;

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
    public Class<? extends ViewConfig> create() {
        Class<? extends ViewConfig> result = super.create();
        
        getEntity().setDate(new Date());
        //FIXME: Bunu config'den sınıf adına göre almak en temizi olur.
        String s = getEntity().getClass().getSimpleName().substring(0, 2);
        getEntity().setVoucherNo(sequenceManager.getNewSerialNumber(s, 6));
        
        getEntity().setOwner(identity.getLoginName());
        
        return  result;
    }


    
    
}
