package com.ozguryazilim.tekir.recruit.applicant;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.Applicant_;
import com.ozguryazilim.telve.data.RepositoryBase;
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
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 *
 * @author yusuf
 */
@Repository
@Dependent
public abstract class ApplicantRepository extends
        RepositoryBase<Applicant, ApplicantViewModel>
        implements
        CriteriaSupport<Applicant> {

    @Override
    public List<ApplicantViewModel> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<Applicant, ?, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();

        CriteriaQuery<ApplicantViewModel> criteriaQuery = criteriaBuilder.createQuery(ApplicantViewModel.class);

        Root<Applicant> from = criteriaQuery.from(Applicant.class);

        buildViewModelSelect(criteriaQuery, from);

        List<Predicate> predicates = new ArrayList<>();

        decorateFilters(filters, predicates, criteriaBuilder, from);

        buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        if (queryDefinition.getSorters().isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(Applicant_.firstName)));
        } else {
            criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
        }
        TypedQuery<ApplicantViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<ApplicantViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }

    private void buildViewModelSelect(CriteriaQuery<ApplicantViewModel> criteriaQuery, Root<? extends Applicant> from) {
        criteriaQuery.multiselect(
                from.get(Applicant_.id),
                from.get("skills"),
                from.get("classifications"),
                from.get(Applicant_.militaryDuty),
                from.get(Applicant_.rating),
                from.get(Applicant_.married)
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends Applicant> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(from.get(Applicant_.firstName), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(Applicant_.lastName), "%" + searchText + "%")
            ));
        }
    }

}
