/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account;

import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.AccountTxn_;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.util.List;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.Criteria;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 * AccountTxn için repository sınıfı.
 * 
 * @author Hakan Uygun
 */
@Repository
@Dependent
public abstract class AccountTxnRepository extends
        RepositoryBase<AccountTxn, AccountTxn>
        implements
        CriteriaSupport<AccountTxn> {
    
    
    public abstract AccountTxn findOptionalByFeature( FeaturePointer feature );
    
    public abstract List<AccountTxn> findByProcessId( String processId );
    
    public List<AccountTxn> findOpenTxnsByAccount( Contact account ){
        Criteria<AccountTxn,AccountTxn> crit = criteria()
                .eq(AccountTxn_.account, account)
                .in(AccountTxn_.status, "DRAFT", "OPEN")
                .orderDesc(AccountTxn_.date);
        
        return crit.getResultList();
    }
}
