package com.ozguryazilim.tekir.lead;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.deltaspike.data.api.Repository;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Lead;
import com.ozguryazilim.tekir.entities.Lead_;
import com.ozguryazilim.tekir.entities.LeadSource_;
import com.ozguryazilim.tekir.entities.LeadCategory_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.VoucherGroup_;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.entities.TreeNodeEntityBase_;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;

@Dependent
@Repository
public abstract class LeadRepository extends VoucherRepositoryBase<Lead, LeadViewModel> {

	@Override
	public List<LeadViewModel> browseQuery(QueryDefinition queryDefinition) {

		List<Filter<Lead, ?, ?>> filters = queryDefinition.getFilters();

		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		CriteriaQuery<LeadViewModel> criteriaQuery = criteriaBuilder.createQuery(LeadViewModel.class);

		Root<Lead> from = criteriaQuery.from(Lead.class);
		from.join(Lead_.leadSource, JoinType.LEFT);
		from.join(Lead_.leadCategory, JoinType.LEFT);
		from.join(VoucherBase_.group, JoinType.LEFT);

		buildVieModelSelect(criteriaQuery, from);

		List<Predicate> predicates = new ArrayList<>();

		decorateFilters(filters, predicates, criteriaBuilder, from);

		buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);

		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		if (queryDefinition.getSorters().isEmpty()) {
			criteriaQuery.orderBy(criteriaBuilder.desc(from.get(VoucherBase_.date)));
		} else {
			criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
		}

		TypedQuery<LeadViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
		typedQuery.setMaxResults(queryDefinition.getResultLimit());

		return typedQuery.getResultList();
	}

	private void buildVieModelSelect(CriteriaQuery<LeadViewModel> criteriaQuery, Root<? extends Lead> from) {

		criteriaQuery.multiselect(
				from.get(Lead_.id), 
				from.get(VoucherBase_.code), 
				from.get(VoucherBase_.voucherNo),
				from.get(VoucherBase_.info), 
				from.get(VoucherBase_.referenceNo), 
				from.get(VoucherBase_.date),
				from.get(VoucherBase_.owner), 
				from.get(VoucherBase_.state), 
				from.get(VoucherBase_.stateReason),
				from.get(VoucherBase_.stateInfo), 
				from.get(VoucherBase_.group).get(VoucherGroup_.id),
				from.get(VoucherBase_.group).get(VoucherGroup_.groupNo), 
				from.get(VoucherBase_.topic),
				from.get(Lead_.relatedPersonName), 
				from.get(Lead_.relatedPersonSurname),
				from.get(Lead_.relatedCompanyName), 
				from.get(Lead_.relatedPhone), 
				from.get(Lead_.relatedAddress),
				from.get(Lead_.relatedEmail), 
				from.get(Lead_.leadSource).get(LeadSource_.id),
				from.get(Lead_.leadSource).get(TreeNodeEntityBase_.name),
				from.get(Lead_.leadCategory).get(LeadCategory_.id),
				from.get(Lead_.leadCategory).get(TreeNodeEntityBase_.name)
				);
	}

	private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates,
			Root<? extends Lead> from) {
		if (!Strings.isNullOrEmpty(searchText)) {
			predicates.add(
					criteriaBuilder.or(
							criteriaBuilder.like(from.get(VoucherBase_.topic), "%" + searchText + "%"),
							criteriaBuilder.like(from.get(VoucherBase_.voucherNo), "%" + searchText + "%"),
							criteriaBuilder.like(from.get(Lead_.relatedCompanyName), "%" + searchText + "%")
							)
					);
		}
	}
}
