/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account.txn;

import com.google.common.base.Strings;
import com.ozguryazilim.finance.account.FinanceAccountTxnSumModel;
import com.ozguryazilim.tekir.entities.FinanceAccountTxn_;
import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.AccountType;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.FinanceAccountTxn;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.entities.FeaturePointer_;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

	public abstract List<FinanceAccountTxn> findByProcessId( String processId );
	
    public abstract FinanceAccountTxn findOptionalByFeature( FeaturePointer feature );
    
    public abstract FinanceAccountTxn findOptionalByFeatureAndAccount( FeaturePointer feature , FinanceAccount account);

    
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
    
    public List<FinanceAccountTxnSumModel> findFinanceAccounts( String feature, String username, List<AccountType> types ){
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        CriteriaQuery<FinanceAccountTxnSumModel> criteriaQuery = criteriaBuilder.createQuery(FinanceAccountTxnSumModel.class);

        Root<FinanceAccountTxn> from = criteriaQuery.from(FinanceAccountTxn.class);
        
        Expression<BigDecimal> localSumExp = criteriaBuilder.sum(
        		criteriaBuilder.<BigDecimal>selectCase()
                .when(criteriaBuilder.equal(from.get("debit"), true), criteriaBuilder.<BigDecimal>prod(from.get(FinanceAccountTxn_.localAmount),new BigDecimal(-1)))
                .otherwise(from.get(FinanceAccountTxn_.localAmount))
        );
        
        Expression<BigDecimal> sumExp = criteriaBuilder.sum(
        		criteriaBuilder.<BigDecimal>selectCase()
                .when(criteriaBuilder.equal(from.get("debit"), true), criteriaBuilder.<BigDecimal>prod(from.get(FinanceAccountTxn_.amount),new BigDecimal(-1)))
                .otherwise(from.get(FinanceAccountTxn_.amount))
        );
        
        criteriaQuery.multiselect(
                from.get(FinanceAccountTxn_.account),
                localSumExp,
                sumExp,
                from.get(FinanceAccountTxn_.currency)	
        );
        
        criteriaQuery.groupBy(
                from.get(FinanceAccountTxn_.account),
                //,
                //from.get(FinanceAccountTxn_.account).type(),
                from.get(FinanceAccountTxn_.currency)
        );
        
        criteriaQuery.orderBy(criteriaBuilder.asc(from.get(FinanceAccountTxn_.account).get(FinanceAccount_.id)));
        
        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();
        
        if( !Strings.isNullOrEmpty(username)){
            predicates.add(criteriaBuilder.equal(from.get(FinanceAccountTxn_.owner), username));
        }
        
        if( !Strings.isNullOrEmpty(feature)){
            predicates.add(criteriaBuilder.equal(from.get(FinanceAccountTxn_.feature).get(FeaturePointer_.feature), feature));
        }
        
        if( types.size() != 0){
            predicates.add(from.get(FinanceAccountTxn_.account).get(FinanceAccount_.type).in(types));
        }      
       
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        
        TypedQuery<FinanceAccountTxnSumModel> typedQuery = entityManager().createQuery(criteriaQuery);
        List<FinanceAccountTxnSumModel> resultList = typedQuery.getResultList();
        
        return resultList;
    }
}
