/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.virement;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.entities.AccountVirement;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * Virman dekontu'nu cari hareket'e işler.
 * @author Hakan Uygun
 */
@Dependent
public class AccountVirementAccountTxnFeeder implements Serializable{
    
    @Inject
    private AccountTxnService accountTxnService;
    
    
    public void feed(@Observes @EntityQualifier(entity = AccountVirement.class) @After EntityChangeEvent event) {
        
        if( event.getAction() != EntityChangeAction.DELETE   ) {
            AccountVirement entity = (AccountVirement) event.getEntity();
            
            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
            
            accountTxnService.saveFeature(voucherPointer, entity.getFromAccount(), entity.getCode(), entity.getInfo(), Boolean.TRUE, Boolean.TRUE, entity.getCurrency(), entity.getAmount(), entity.getLocalAmount(), entity.getDate(), entity.getOwner(), null, entity.getState().toString(), entity.getStateReason());
            accountTxnService.saveFeature(voucherPointer, entity.getToAccount(), entity.getCode(), entity.getInfo(), Boolean.TRUE, Boolean.FALSE, entity.getCurrency(), entity.getAmount(), entity.getLocalAmount(), entity.getDate(), entity.getOwner(), null, entity.getState().toString(), entity.getStateReason());
        }
        
        //TODO: Delete edildiğinde de gidip txn'den silme yapılmalı.
            
    }
}
