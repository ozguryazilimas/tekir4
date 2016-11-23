/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.invoice.purchase;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.entities.PurchaseInvoice;
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
 *
 * @author oyas
 */
@Dependent
public class PurchaseInvoiceAccountTxnFeeder implements Serializable{
    @Inject
    private AccountTxnService accountTxnService;
    
    
    public void feed(@Observes @EntityQualifier(entity = PurchaseInvoice.class) @After EntityChangeEvent event) {
        
        if( event.getAction() != EntityChangeAction.DELETE   ) {
            PurchaseInvoice entity = (PurchaseInvoice) event.getEntity();
            
            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
            
            accountTxnService.saveFeature(voucherPointer, entity.getAccount(), entity.getCode(), entity.getInfo(), Boolean.FALSE, Boolean.FALSE, entity.getCurrency(), entity.getTotal(), entity.getDate(), entity.getOwner(), entity.getProcess().getProcessNo(), entity.getState().toString(), entity.getStateReason());
        }
        
        //TODO: Delete edildiğinde de gidip txn'den silme yapılmalı.
            
    }
}
