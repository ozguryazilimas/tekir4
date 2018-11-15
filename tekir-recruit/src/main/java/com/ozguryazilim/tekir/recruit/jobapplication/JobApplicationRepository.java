package com.ozguryazilim.tekir.recruit.jobapplication;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.JobAdvert;
import com.ozguryazilim.tekir.entities.JobApplication;
import com.ozguryazilim.tekir.entities.JobApplication_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Erdem Uslu
 */
@Dependent
@Repository
@Transactional
public abstract class JobApplicationRepository
        extends RepositoryBase<JobApplication, JobApplicationViewModel>
        implements CriteriaSupport<JobApplication> {

    /**
     * Verilen İş İlanına'a ait tüm iş başvurularını döndürür.
     * 
     * @param advert
     * @return 
     */
    @Query("select c from JobApplication c where advert = ?1")
    public abstract List<JobApplication> findByJobAdvert(JobAdvert advert);
    
    /**
     * Verilen Başvuru Adayına ait tüm iş başvurularını döndürür.
     * 
     * @param applicant
     * @return 
     */
    @Query("select c from JobApplication c where applicant = ?1")
    public abstract List<JobApplication> findByApplicant(Applicant applicant);
    
    @Override
    public List<JobApplicationViewModel> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<JobApplication, ?, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        CriteriaQuery<JobApplicationViewModel> criteriaQuery = criteriaBuilder.createQuery(JobApplicationViewModel.class);

        Root<JobApplication> from = criteriaQuery.from(JobApplication.class);
        Join<JobApplication, JobAdvert> jj = from.join(JobApplication_.advert, JoinType.LEFT);
        Join<JobApplication, Applicant> ja = from.join(JobApplication_.applicant, JoinType.LEFT);

        buildViewModelSelect(criteriaQuery, from);

        List<Predicate> predicates = new ArrayList<>();

        decorateFilters(filters, predicates, criteriaBuilder, from);

        buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        if (queryDefinition.getSorters().isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(JobApplication_.info)));
        } else {
            criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
        }
        
        TypedQuery<JobApplicationViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<JobApplicationViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }

    private void buildViewModelSelect(
            CriteriaQuery<JobApplicationViewModel> criteriaQuery,
            Root<? extends JobApplication> from) {

        criteriaQuery.multiselect(
                from.get(JobApplication_.id),
                from.get(JobApplication_.date),
                from.get(JobApplication_.owner),
                from.get(JobApplication_.state),
                from.get(JobApplication_.info),
                from.get(JobApplication_.advert),
                from.get(JobApplication_.applicant)
        );
    }

    private void buildSearchTextControl(
            String searchText,
            CriteriaBuilder criteriaBuilder,
            List<Predicate> predicates,
            Root<? extends JobApplication> from) {

        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(criteriaBuilder.or(criteriaBuilder.like(from.get(JobApplication_.owner), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(JobApplication_.state), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(JobApplication_.info), "%" + searchText + "%")));
        }
    }

}
