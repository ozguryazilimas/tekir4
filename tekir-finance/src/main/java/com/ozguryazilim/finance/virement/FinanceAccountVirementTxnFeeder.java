/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.virement;

import com.ozguryazilim.finance.account.txn.FinanceAccountTxnService;
import com.ozguryazilim.tekir.entities.FinanceAccountVirement;
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

import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 * Virman dekontu'nu cari hareket'e işler.
 * @author Hakan Uygun
 */
@Dependent
public class FinanceAccountVirementTxnFeeder implements Serializable{
    
    @Inject
    private FinanceAccountTxnService financeAccountTxnService;
    
   
    public void feed(@Observes @EntityQualifier(entity = FinanceAccountVirement.class) @After EntityChangeEvent event) {
        
        if( event.getAction() != EntityChangeAction.DELETE   ) {
        	FinanceAccountVirement entity = (FinanceAccountVirement) event.getEntity();
            
            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
            
            financeAccountTxnService.saveFeature(voucherPointer, entity.getFromAccount(), entity.getCode(), entity.getInfo(), Boolean.TRUE, Boolean.TRUE, entity.getCurrency(), entity.getAmount(), entity.getLocalAmount(), entity.getDate(), entity.getOwner(), null, entity.getState().toString(), entity.getStateReason());
            financeAccountTxnService.saveFeature(voucherPointer, entity.getToAccount(), entity.getCode(), entity.getInfo(), Boolean.TRUE, Boolean.FALSE, entity.getCurrency(), entity.getAmount(), entity.getLocalAmount(), entity.getDate(), entity.getOwner(), null, entity.getState().toString(), entity.getStateReason());
        }
        
        //TODO: Delete edildiğinde de gidip txn'den silme yapılmalı.
            
    }
}
