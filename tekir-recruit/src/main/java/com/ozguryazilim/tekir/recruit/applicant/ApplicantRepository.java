package com.ozguryazilim.tekir.recruit.applicant;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Applicant;
import com.ozguryazilim.tekir.entities.Applicant_;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactInformation_;
import com.ozguryazilim.tekir.entities.ContactPhone;
import com.ozguryazilim.tekir.entities.Contact_;
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
        Join<Applicant, ContactPhone> pm = from.join(Contact_.primaryMobile, JoinType.LEFT);
        Join<Applicant, ContactPhone> pp = from.join(Contact_.primaryPhone, JoinType.LEFT);
        Join<Applicant, ContactEMail> pe = from.join(Contact_.primaryEmail, JoinType.LEFT);

        buildViewModelSelect(criteriaQuery, from);

        List<Predicate> predicates = new ArrayList<>();

        decorateFilters(filters, predicates, criteriaBuilder, from);

        buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        if (queryDefinition.getSorters().isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(Contact_.name)));
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
                from.get(Contact_.id),
                from.get(Contact_.code),
                from.get(Contact_.name),
                from.get("skills"),
                from.get("classifications"),
                from.get(Applicant_.militaryDuty),
                from.get(Applicant_.rating),
                from.get(Applicant_.married),
                from.get(Contact_.info),
                from.get(Contact_.active),
                from.type(),
                from.get(Contact_.primaryMobile).get(ContactInformation_.id),
                from.get(Contact_.primaryMobile).get(ContactInformation_.address),
                from.get(Contact_.primaryPhone).get(ContactInformation_.id),
                from.get(Contact_.primaryPhone).get(ContactInformation_.address),
                from.get(Contact_.primaryEmail).get(ContactInformation_.id),
                from.get(Contact_.primaryEmail).get(ContactInformation_.address)    
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends Applicant> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(criteriaBuilder.or(criteriaBuilder.like(from.get(Contact_.code), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(Contact_.name), "%" + searchText + "%")
            ));
        }
    }

}
