/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account.txn;

import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.FinanceAccountTxn;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class FinanceAccountTxnRepository extends RepositoryBase<FinanceAccountTxn, FinanceAccountTxn> implements   CriteriaSupport<FinanceAccountTxn> {

    public abstract FinanceAccountTxn findOptionalByFeature( FeaturePointer feature );
    
    /**
     * Account için verilen tarihten daha büyük değerleri toplar.
     * 
     * @param account
     * @param date
     * @return 
     */
    public abstract List<FinanceAccountTxn> findByAccountAndDateGreaterThanEqualsOrderByDateAsc( FinanceAccount account, Date date );
    
    /**
     * Verilen tarih için kasa durum bilgisini döndürür.
     * 
     * @param account
     * @param date
     * @return 
     */
    @Query( "select sum( t.localAmount * ( case when t.debit = true then -1 else 1 end )) from FinanceAccountTxn t where t.account = ?1 and t.date < ?2" )
    public abstract BigDecimal findByAccountBalance( FinanceAccount account, Date date );
}
