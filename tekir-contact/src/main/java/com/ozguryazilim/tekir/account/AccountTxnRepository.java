/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.account.reports.AccountStatusFilter;
import com.ozguryazilim.tekir.contact.reports.ContactListFilter;
import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.AccountTxn_;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactCategory_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Corporation_;
import com.ozguryazilim.tekir.entities.Industry_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.entities.FeaturePointer_;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.deltaspike.data.api.Query;
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

        // Öncelikle default sıralama verelim eğer kullanıcı tarafından tanımlı sıralama var ise onu setleyelim
        if (queryDefinition.getSorters().isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(AccountTxn_.date)));
        } else {
            criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
        }

        //Haydi bakalım sonuçları alalım
        TypedQuery<AccountTxn> typedQuery = entityManager().createQuery(criteriaQuery);
        List<AccountTxn> resultList = typedQuery.getResultList();

        return resultList;
    }

    public abstract AccountTxn findOptionalByFeature(FeaturePointer feature);

    public abstract AccountTxn findOptionalByFeatureAndAccount(FeaturePointer feature, Contact account);

    public abstract List<AccountTxn> findByProcessId(String processId);

    public abstract List<AccountTxn> findByAccountAndDateBetweenOrderByDate(Contact account, Date beginDate, Date endDate );

    public List<AccountTxn> findByProcessIdAndTagsAndDateBetweenOrderByDate(String processId,
        List<String> tags, Date beginDate, Date endDate) {
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        CriteriaQuery<AccountTxn> criteriaQuery = criteriaBuilder.createQuery(AccountTxn.class);
        Root<AccountTxn> from = criteriaQuery.from(AccountTxn.class);

        criteriaQuery.select(from);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(from.get(AccountTxn_.processId), processId));

        if (beginDate != null && endDate != null){
            predicates.add(criteriaBuilder.between(from.get(AccountTxn_.date), beginDate, endDate));
        }

        if (tags != null && !tags.isEmpty()) {
            tags.forEach(tag -> predicates
                .add(criteriaBuilder.like(from.get("tags").as(String.class), "%|" + tag + "|%")));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        criteriaQuery.orderBy(criteriaBuilder.desc(from.get(AccountTxn_.date)));

        TypedQuery<AccountTxn> typedQuery = entityManager().createQuery(criteriaQuery);
        List<AccountTxn> resultList = typedQuery.getResultList();

        return resultList;
    }
    @Query( "select sum( t.localAmount * ( case when t.debit = true then -1 else 1 end )) from AccountTxn t where t.account = ?1 and t.date < ?2" )
    public abstract BigDecimal findByAccountBalance( Contact account, Date beginDate );
    
    public List<AccountTxn> findOpenTxnsByAccount(Contact account) {
        Criteria<AccountTxn, AccountTxn> crit = criteria()
                .eq(AccountTxn_.account, account)
                .or(
                        criteria().like(AccountTxn_.status, "DRAFT%"),
                        criteria().like(AccountTxn_.status, "OPEN%")
                )
                .orderDesc(AccountTxn_.date);

        return crit.getResultList();
    }

    public List<AccountTxn> findPayables(Contact account) {
        Criteria<AccountTxn, AccountTxn> crit = criteria()
                .eq(AccountTxn_.account, account)
                .eq(AccountTxn_.accountable, true)
                .eq(AccountTxn_.debit, true)
                .in(AccountTxn_.status, "OPEN")
                .orderAsc(AccountTxn_.date);
        return null;
    }

    public List<AccountTxn> findReceivables(Contact account) {
        Criteria<AccountTxn, AccountTxn> crit = criteria()
                .eq(AccountTxn_.account, account)
                .eq(AccountTxn_.accountable, true)
                .eq(AccountTxn_.debit, false)
                .in(AccountTxn_.status, "OPEN")
                .orderAsc(AccountTxn_.date);
        return null;
    }

    /**
     *
     * @param feature hangi evrak türü. boş olması hepsi demek
     * @param username hangi kullanıcı için boş olması hesap demek
     * @param startDate hangi tarihten başlayacağız
     * @param limit geriye kaç sonuç dönecek
     * @return
     */
    public List<AccountTxnSumModel> findTopAccounts(String feature, String username, Date startDate, int limit) {
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye AccidentAnalysisViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<AccountTxnSumModel> criteriaQuery = criteriaBuilder.createQuery(AccountTxnSumModel.class);

        //From Tabii ki PersonWorkHistory
        Root<AccountTxn> from = criteriaQuery.from(AccountTxn.class);

        criteriaQuery.multiselect(
                from.get(AccountTxn_.account).get(Contact_.id),
                from.get(AccountTxn_.account).get(Contact_.name),
                //from.get(AccountTxn_.account).type(),
                criteriaBuilder.sum(from.get(AccountTxn_.localAmount))
        );

        criteriaQuery.groupBy(
                from.get(AccountTxn_.account).get(Contact_.id),
                from.get(AccountTxn_.account).get(Contact_.name)
        //,
        //from.get(AccountTxn_.account).type(),
        //from.get(AccountTxn_.currency)
        );

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        if (!Strings.isNullOrEmpty(username)) {
            predicates.add(criteriaBuilder.equal(from.get(AccountTxn_.owner), username));
        }

        if (!Strings.isNullOrEmpty(feature)) {
            predicates.add(criteriaBuilder.equal(from.get(AccountTxn_.feature).get(FeaturePointer_.feature), feature));
        }

        predicates.add(criteriaBuilder.greaterThanOrEqualTo(from.get(AccountTxn_.date), startDate));

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<AccountTxnSumModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(limit);
        List<AccountTxnSumModel> resultList = typedQuery.getResultList();

        return resultList;
    }
    
    public List<AccountTxnStatusModel> findAccountStatus( AccountStatusFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye AccidentAnalysisViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<AccountTxnStatusModel> criteriaQuery = criteriaBuilder.createQuery(AccountTxnStatusModel.class);

        //From Tabii ki PersonWorkHistory
        Root<AccountTxn> from = criteriaQuery.from(AccountTxn.class);

        criteriaQuery.multiselect(
                from.get(AccountTxn_.account).get(Contact_.id),
                from.get(AccountTxn_.account).get(Contact_.name),
                //from.get(AccountTxn_.account).type(),
                criteriaBuilder.sum(
                        criteriaBuilder.<Number>selectCase()
                            .when(criteriaBuilder.equal(from.get(AccountTxn_.debit), Boolean.TRUE), from.get(AccountTxn_.localAmount))
                            .otherwise(0)
                ),
                criteriaBuilder.sum(
                        criteriaBuilder.<Number>selectCase()
                            .when(criteriaBuilder.equal(from.get(AccountTxn_.debit), Boolean.FALSE), from.get(AccountTxn_.localAmount))
                            .otherwise(0)
                )
        );

        criteriaQuery.groupBy(
                from.get(AccountTxn_.account).get(Contact_.id),
                from.get(AccountTxn_.account).get(Contact_.name)
                //from.get(AccountTxn_.account).type()
        );

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        if (!Strings.isNullOrEmpty(filter.getCode())) {
            predicates.add(criteriaBuilder.like(from.get(AccountTxn_.account).get(Contact_.code), "%" + filter.getCode() + "%"));
        }
        
        if (!Strings.isNullOrEmpty(filter.getName())) {
            predicates.add(criteriaBuilder.like(from.get(AccountTxn_.account).get(Contact_.name), "%" + filter.getName()+ "%"));
        }
        
        if (filter.getContactCategory() != null) {
            predicates.add(criteriaBuilder.like(from.get(AccountTxn_.account).get(Contact_.category).get(ContactCategory_.path), filter.getContactCategory().getPath() + "%"));
        }
        
        if (filter.getIndustry() != null) {
            predicates.add(criteriaBuilder.like(from.get(AccountTxn_.account).get(Contact_.industry).get(Industry_.path), filter.getIndustry().getPath() + "%"));
        }
        
        if (filter.getTerritory() != null) {
            predicates.add(criteriaBuilder.equal(from.get(AccountTxn_.account).get(Contact_.territory), filter.getTerritory()));
        }
        
        if (filter.getCorporationType() != null) {
            //CorporationType sadece Contact mirascısı Corporation'da dolayısı ile cast ediyoruz.
            Path<Corporation> corpPath = criteriaBuilder.treat(from.get(AccountTxn_.account), Corporation.class);
            predicates.add(criteriaBuilder.equal(corpPath.get(Corporation_.corporationType), filter.getCorporationType())); 
        }

        predicates.add(criteriaBuilder.lessThanOrEqualTo(from.get(AccountTxn_.date), filter.getDate().getCalculatedValue()));

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        criteriaQuery.orderBy(criteriaBuilder.asc(from.get(AccountTxn_.account).get(Contact_.name)));
        
        TypedQuery<AccountTxnStatusModel> typedQuery = entityManager().createQuery(criteriaQuery);
        //typedQuery.setMaxResults(limit);
        List<AccountTxnStatusModel> resultList = typedQuery.getResultList();

        return resultList;
        
    }

    public List<AccountTxn> findOpenTxnByContactId(Long contactId, ContactListFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        CriteriaQuery<AccountTxn> criteriaQuery = criteriaBuilder.createQuery(AccountTxn.class);
        Root<AccountTxn> from = criteriaQuery.from(AccountTxn.class);

        criteriaQuery.select(from);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder
            .equal(from.get(AccountTxn_.account).get(Contact_.id), contactId));
        predicates.add(criteriaBuilder
            .or(
                criteriaBuilder.like(from.get(AccountTxn_.status), "DRAFT%"),
                criteriaBuilder.like(from.get(AccountTxn_.status), "OPEN%")
            ));

        if (filter.getTag() != null && !filter.getTag().isEmpty()) {
            filter.getTag().forEach(
                tag -> predicates.add(criteriaBuilder.like(from.get("tags").as(String.class), "%|" + tag + "|%")));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        criteriaQuery.orderBy(criteriaBuilder.desc(from.get(AccountTxn_.date)));

        TypedQuery<AccountTxn> typedQuery = entityManager().createQuery(criteriaQuery);
        List<AccountTxn> resultList = typedQuery.getResultList();

        return resultList;
    }

    public abstract List<AccountTxn> findByAccountOrderByDateDesc(Contact c);
}
