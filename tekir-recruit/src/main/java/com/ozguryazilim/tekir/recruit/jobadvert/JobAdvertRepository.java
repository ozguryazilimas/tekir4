package com.ozguryazilim.tekir.recruit.jobadvert;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.tekir.entities.JobAdvert_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deniz
 */
@Repository
@Dependent
public abstract class JobAdvertRepository extends
        RepositoryBase<JobAdvert, JobAdvertViewModel>
        implements
        CriteriaSupport<JobAdvert> {

    @Override
    public List<JobAdvertViewModel> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<JobAdvert, ?, ?>> filters = queryDefinition.getFilters();
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        CriteriaQuery<JobAdvertViewModel> criteriaQuery = criteriaBuilder.createQuery(JobAdvertViewModel.class);
        Root<JobAdvert> from = criteriaQuery.from(JobAdvert.class);

        buildVieModelSelect(criteriaQuery, from);
        List<Predicate> predicates = new ArrayList<>();
        decorateFilters(filters, predicates, criteriaBuilder, from);
        buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);
        //TODO satır bazlı yetki kontrolü yapılmadı (owner,group,all)
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        if (queryDefinition.getSorters().isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(JobAdvert_.topic)));
        } else {
            criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
        }

        TypedQuery<JobAdvertViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<JobAdvertViewModel> resultList = typedQuery.getResultList();
        return resultList;
    }

    private void buildVieModelSelect(CriteriaQuery<JobAdvertViewModel> criteriaQuery, Root<? extends JobAdvert> from) {
        criteriaQuery.multiselect(
                from.get(JobAdvert_.id),
                from.get(JobAdvert_.serial),
                from.get(JobAdvert_.topic),
                from.get(JobAdvert_.info),
                from.get(JobAdvert_.startDate),
                from.get(JobAdvert_.endDate),
                from.get(JobAdvert_.owner),
                from.get(JobAdvert_.status),
                from.get("skills")
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends JobAdvert> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(criteriaBuilder.or(criteriaBuilder.like(from.get(JobAdvert_.serial), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(JobAdvert_.topic), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(JobAdvert_.info), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(JobAdvert_.owner), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(JobAdvert_.status), "%" + searchText + "%")));
        }
    }
    
    /**
     *
     * JobAdvertLookup Dialog için lookupQuery metodu.
     *
     * Aranan text e bagli olarak sorgu olusur ve dialog da gosterilir.
     * Bos text aramasi tum jobAdvert lari getirir.
     * 
     * @param searchText JobAdvert arama-dialog içinde arama yapılacak text
     * @return JobAdvert sonuc listesi
     */
    @Override
    public List<JobAdvertViewModel> lookupQuery(String searchText) {
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        CriteriaQuery<JobAdvertViewModel> criteriaQuery = criteriaBuilder.createQuery(JobAdvertViewModel.class);
        
        Root<JobAdvert> from = criteriaQuery.from(JobAdvert.class);
        
        buildVieModelSelect(criteriaQuery, from);
        
        List<Predicate> predicates = new ArrayList<>();

        buildSearchTextControl(searchText, criteriaBuilder, predicates, from);
        
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.orderBy(criteriaBuilder.asc(from.get(JobAdvert_.topic)));
        
        TypedQuery<JobAdvertViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        List<JobAdvertViewModel> resultList = typedQuery.getResultList();
        
        return resultList;
    }
}
