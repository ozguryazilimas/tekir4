/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account;

import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.AccountTxn_;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    
    private Contact account;

    public Contact getAccount() {
        return account;
    }

    public void setAccount(Contact account) {
        this.account = account;
    }

    @Override
    public List<AccountTxn> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<AccountTxn, ?, ?>> filters = queryDefinition.getFilters();
        
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye AccidentAnalysisViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<AccountTxn> criteriaQuery = criteriaBuilder.createQuery(AccountTxn.class);

        //From Tabii ki PersonWorkHistory
        Root<AccountTxn> from = criteriaQuery.from(AccountTxn.class);
        

        //Sonuç filtremiz
        /*
        criteriaQuery.multiselect(
                from.get(RelatedContact_.id),
                from.get(RelatedContact_.sourceContact),
                //from.get(RelatedContact_.sourceContact).get(Contact_.name),
                from.get(RelatedContact_.targetContact),
                //from.get(RelatedContact_.targetContact).get(Contact_.name),
                from.get(RelatedContact_.relation),
                //from.get(RelatedContact_.releation).get(ContactRelation_.name)
                from.get(RelatedContact_.info)
        );*/

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();
        

        decorateFilters(filters, predicates, criteriaBuilder, from);

        //Hem source hem de target üzerinde olanlar gelsin.
        if (account != null) {
            /*
            predicates.add(criteriaBuilder.or( 
                    criteriaBuilder.equal(from.get(RelatedContact_.sourceContact).get(Contact_.id), contact.getId()),
                    criteriaBuilder.equal(from.get(RelatedContact_.targetContact).get(Contact_.id), contact.getId())));
            */
            predicates.add(criteriaBuilder.equal(from.get(AccountTxn_.account).get(Contact_.id), account.getId()));
        }
        
        //filtremize ekledik.
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));


        //Haydi bakalım sonuçları alalım
        TypedQuery<AccountTxn> typedQuery = entityManager().createQuery(criteriaQuery);
        List<AccountTxn> resultList = typedQuery.getResultList();

        return resultList;
    }
    
    
    public abstract AccountTxn findOptionalByFeature( FeaturePointer feature );
    
    public abstract List<AccountTxn> findByProcessId( String processId );
    
    public List<AccountTxn> findOpenTxnsByAccount( Contact account ){
        Criteria<AccountTxn,AccountTxn> crit = criteria()
                .eq(AccountTxn_.account, account)
                .in(AccountTxn_.status, "DRAFT", "OPEN")
                .orderDesc(AccountTxn_.date);
        
        return crit.getResultList();
    }
    
    public List<AccountTxn> findPayables( Contact account ){
        Criteria<AccountTxn,AccountTxn> crit = criteria()
                .eq(AccountTxn_.account, account)
                .eq(AccountTxn_.accountable, true)
                .eq(AccountTxn_.debit, true)
                .in(AccountTxn_.status, "OPEN")
                .orderAsc(AccountTxn_.date);
        return null;
    }
    
    public List<AccountTxn> findReceivables( Contact account ){
        Criteria<AccountTxn,AccountTxn> crit = criteria()
                .eq(AccountTxn_.account, account)
                .eq(AccountTxn_.accountable, true)
                .eq(AccountTxn_.debit, false)
                .in(AccountTxn_.status, "OPEN")
                .orderAsc(AccountTxn_.date);
        return null;
    }
}