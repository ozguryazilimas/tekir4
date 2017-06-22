/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.AbstractPerson;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.ActivityStatus;
import com.ozguryazilim.tekir.entities.Activity_;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.Criteria;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 *
 * @author oyas
 */
@Dependent
@Repository
public abstract class ActivityRepository extends RepositoryBase<Activity, Activity> implements CriteriaSupport<Activity>{
    
    
    @Inject
    private Identity identity;
    
    @Override
    public List<Activity> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<Activity, ?, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye Activity dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<Activity> criteriaQuery = criteriaBuilder.createQuery(Activity.class);
        

        //From
        Root<Activity> from = criteriaQuery.from(Activity.class);
        

        //Sonuç filtremiz : Sınıfın kendisi dönecek zati
        //buildVieModelSelect(criteriaQuery, from);

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        decorateFilters(filters, predicates, criteriaBuilder, from);

        buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);

        //Oluşan filtreleri sorgumuza ekliyoruz
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // Öncelikle default sıralama verelim eğer kullanıcı tarafından tanımlı sıralama var ise onu setleyelim
        if (queryDefinition.getSorters().isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(Activity_.dueDate)));
        } else {
            criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
        }

        //Haydi bakalım sonuçları alalım
        TypedQuery<Activity> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<Activity> resultList = typedQuery.getResultList();

        return resultList;
    }
    
    /*
    private void buildVieModelSelect(CriteriaQuery<Activity> criteriaQuery, Root<? extends Activity> from) {
        criteriaQuery.multiselect(
                from.get(Activity_.id),
                from.get(Activity_.subject),
                from.get(Activity_.name),
                from.get(Contact_.info),
                from.get(Contact_.active),
                from.type(),
                from.get(Contact_.primaryMobile).get(ContactInformation_.id),
                from.get(Contact_.primaryMobile).get(ContactInformation_.address),
                from.get(Contact_.primaryPhone).get(ContactInformation_.id),
                from.get(Contact_.primaryPhone).get(ContactInformation_.address),
                //from.get(Contact_.primaryFax).get(ContactInformation_.id),
                //from.get(Contact_.primaryFax).get(ContactInformation_.address),
                from.get(Contact_.primaryEmail).get(ContactInformation_.id),
                from.get(Contact_.primaryEmail).get(ContactInformation_.address)
                //from.get(Contact_.primaryAddress).get(ContactInformation_.id),
                //from.get(Contact_.primaryAddress).get(ContactInformation_.address)
        );
    }
    */
    
    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends Activity> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(criteriaBuilder.or(criteriaBuilder.like(from.get(Activity_.subject), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(Activity_.body), "%" + searchText + "%")));
        }
    }

 
    /**
     * Geriye Person'la ilgili olan activity'leri döndürür.
     * 
     * @param person
     * @return 
     */
    public List<Activity> findByPerson( AbstractPerson person, ActivityWidgetFilter filter ){
        Criteria<Activity,Activity> crit = criteria()
                .eq(Activity_.person, person)
                .orderDesc(Activity_.dueDate);
                
        switch( filter ){
            case MINE : crit.eq(Activity_.assignee, identity.getLoginName()); break;
            case OVERDUE : crit.gt(Activity_.dueDate, new Date()); break;
            case SCHEDULED : crit.eq( Activity_.status, ActivityStatus.SCHEDULED); break;
            case SUCCESS : crit.eq( Activity_.status, ActivityStatus.SUCCESS); break;
            case FAILED : crit.eq( Activity_.status, ActivityStatus.FAILED); break;
            case FOLLOWUP : break; //TODO:
        }

        //Criteria üzerinde pagein limit yok
        
        return crit.createQuery().setMaxResults(5).getResultList();
                
    }
    
    /**
     * Geriye Corporation'la ilgili activity'leri döndürür
     * @param corporation
     * @return 
     */
    public List<Activity> findByCorporation( Corporation corporation, ActivityWidgetFilter filter ){
        Criteria<Activity,Activity> crit = criteria()
                .eq(Activity_.corporation, corporation)
                .orderDesc(Activity_.dueDate);
                
        switch( filter ){
            case MINE : crit.eq(Activity_.assignee, identity.getLoginName()); break;
            case OVERDUE : crit.gt(Activity_.dueDate, new Date()); break;
            case SCHEDULED : crit.eq( Activity_.status, ActivityStatus.SCHEDULED); break;
            case SUCCESS : crit.eq( Activity_.status, ActivityStatus.SUCCESS); break;
            case FAILED : crit.eq( Activity_.status, ActivityStatus.FAILED); break;
            case FOLLOWUP : break; //TODO:
        }

        //Criteria üzerinde pagein limit yok
        
        return crit.createQuery().setMaxResults(5).getResultList();
                
    }
    
    /**
     * Geriye Feature ile ilgili activity'leri döndürür.
     * @param featurePointer
     * @return 
     */
    public List<Activity> findByFeature( FeaturePointer featurePointer, ActivityWidgetFilter filter ){
        Criteria<Activity,Activity> crit = criteria()
                .eq(Activity_.regarding, featurePointer)
                .orderDesc(Activity_.dueDate);
                
        
        switch( filter ){
            case MINE : crit.eq(Activity_.assignee, identity.getLoginName()); break;
            case OVERDUE : crit.gt(Activity_.dueDate, new Date()); break;
            case SCHEDULED : crit.eq( Activity_.status, ActivityStatus.SCHEDULED); break;
            case SUCCESS : crit.eq( Activity_.status, ActivityStatus.SUCCESS); break;
            case FAILED : crit.eq( Activity_.status, ActivityStatus.FAILED); break;
            case FOLLOWUP : break; //TODO:
        }

        //Criteria üzerinde pagein limit yok
        return crit.createQuery().setMaxResults(5).getResultList();
                
    }
    
    
    public List<Activity> findForCalendarSource( String assignee, Date beginDate, Date endDate, Class<? extends Activity> clazz, Boolean showClocedEvents){
        
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye Activity dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<Activity> criteriaQuery = criteriaBuilder.createQuery(Activity.class);
        

        //From
        Root<Activity> from = criteriaQuery.from(Activity.class);
        

        //Sonuç filtremiz : Sınıfın kendisi dönecek zati
        //buildVieModelSelect(criteriaQuery, from);

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(from.get(Activity_.assignee), assignee));
        if( !showClocedEvents ){
            predicates.add(criteriaBuilder.equal(from.get(Activity_.status), ActivityStatus.SCHEDULED));
        }
        predicates.add(criteriaBuilder.equal(from.type(), clazz));
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(from.get(Activity_.dueDate), beginDate));
        predicates.add(criteriaBuilder.lessThanOrEqualTo(from.get(Activity_.dueDate), endDate));
        
        //Oluşan filtreleri sorgumuza ekliyoruz
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        
        //Haydi bakalım sonuçları alalım
        TypedQuery<Activity> typedQuery = entityManager().createQuery(criteriaQuery);
        List<Activity> resultList = typedQuery.getResultList();

        return resultList;
        
    }
    
    /**
     * Belirtilen süreden sonra kapanmamış aktiviteleri veritabanında arar.
     *
     * @param date Aktivitelerin bitiş tarihinden itibaren geçecek süre
     * @return Geçen süreden sonra kapanmamış olan aktiviteler
     * @see Activity
     * @see Activity_
     */
    public List<Activity> findExpiredActivities(Date date) {
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye ViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<Activity> criteriaQuery = criteriaBuilder.createQuery(Activity.class);

        //From 
        Root<Activity> from = criteriaQuery.from(Activity.class);

        //Sonuç filtremiz : Zaten sınıfın kendisi döner.
        //buildVieModelSelect(criteriaQuery, from);

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        //Tarih verilen parametreden küçük ve eşit olanlar.
        predicates.add(criteriaBuilder.lessThanOrEqualTo(from.get(Activity_.dueDate), date));

        //Olumlu ya da Olumsuz kapanmamış olanlar.
        predicates.add(criteriaBuilder.notLike(from.get(Activity_.status).as(String.class), "SUCCESS"));

        //Oluşan filtreleri sorgumuza ekliyoruz
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        //Haydi bakalım sonuçları alalım
        TypedQuery<Activity> typedQuery = entityManager().createQuery(criteriaQuery);
        List<Activity> resultList = typedQuery.getResultList();

        return resultList;
    }
    
}
