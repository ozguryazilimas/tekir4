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
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

/**
 * Virman dekontu'nu cari hareket'e işler.
 * @author Hakan Uygun
 */
@Dependent
public class AccountVirementAccountTxnFeeder implements Serializable{
    
    @Inject
    private AccountTxnService accountTxnService;
    
    
    public void feed(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = AccountVirement.class) @After EntityChangeEvent event) {
        
        if( event.getAction() != EntityChangeAction.DELETE   ) {
            AccountVirement entity = (AccountVirement) event.getEntity();
            
            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

            accountTxnService.saveFeature(voucherPointer, entity.getFromAccount(), entity.getInfo(), entity.getTags(),
                    Boolean.TRUE, Boolean.TRUE, entity.getCurrency(), entity.getAmount(), entity.getLocalAmount(),
                    entity.getDate(), entity.getOwner(), null, entity.getState().toString(),
                    entity.getStateReason(), entity.getTopic());

            accountTxnService.saveFeature(voucherPointer, entity.getToAccount(), entity.getInfo(), entity.getTags(),
                    Boolean.TRUE, Boolean.FALSE, entity.getCurrency(), entity.getAmount(), entity.getLocalAmount(),
                    entity.getDate(), entity.getOwner(), null, entity.getState().toString(), entity.getStateReason(),
                    entity.getTopic());
        }
        
        //TODO: Delete edildiğinde de gidip txn'den silme yapılmalı.
            
    }
}
