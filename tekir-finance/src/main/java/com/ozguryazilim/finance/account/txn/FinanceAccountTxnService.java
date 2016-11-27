/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account.txn;

import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.FinanceAccountTxn;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author oyas
 */
@Named
@RequestScoped
public class FinanceAccountTxnService implements Serializable{
    
    @Inject
    private FinanceAccountTxnRepository repository;

    @Transactional
    public void saveFeature( FeaturePointer feature, FinanceAccount account, String code, String info, Boolean accountable, Boolean debit, Currency currency, BigDecimal amount, Date date,  String owner, String processId,  String status, String statusReason ){
        
        FinanceAccountTxn txn = repository.findOptionalByFeature( feature );
        
        if( txn == null ){
            txn = new FinanceAccountTxn();
        }
        
        txn.setAccount(account);
        txn.setAmount(amount);
        txn.setCurrency(currency);
        txn.setDebit(debit);
        txn.setDate(date);
        txn.setFeature(feature);
        txn.setCode(code);
        txn.setInfo(info);
        txn.setOwner(owner);
        txn.setProcessId(processId);
        txn.setStatus(status);
        txn.setStatusReason(statusReason);
        
        repository.save(txn);
    }
    
    
    
}
