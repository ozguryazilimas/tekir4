/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.debit;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.entities.AccountDebitNote;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

/**
 * Alacak dekontu'nu cari hareket'e işler.
 * @author Hakan Uygun
 */
@Dependent
public class AccountDebitNoteAccountTxnFeeder implements Serializable{
    
    @Inject
    private AccountTxnService accountTxnService;
    
    
    public void feed(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = AccountDebitNote.class) @After EntityChangeEvent event) {
        AccountDebitNote entity = (AccountDebitNote) event.getEntity();

        FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

        if( event.getAction() != EntityChangeAction.DELETE) {

            accountTxnService.saveFeature(voucherPointer, entity.getAccount(), entity.getInfo(), entity.getTags(),
                    Boolean.TRUE, Boolean.TRUE, entity.getCurrency(), entity.getAmount(), entity.getLocalAmount(),
                    entity.getDate(), entity.getOwner(), null, entity.getState().toString(),
                    entity.getStateReason(), entity.getTopic());
        } else if (event.getAction() == EntityChangeAction.DELETE) {
            accountTxnService.deleteFeature(voucherPointer);
        }

    }
}
