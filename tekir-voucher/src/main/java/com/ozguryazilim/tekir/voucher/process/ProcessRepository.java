package com.ozguryazilim.tekir.voucher.process;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactCategory_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.ContactAddress;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactPhone;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Corporation_;
import com.ozguryazilim.tekir.entities.Industry_;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.ProcessStatus;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.Process_;
import com.ozguryazilim.tekir.voucher.process.reports.ProcessReportFilter;
import com.ozguryazilim.tekir.voucher.process.reports.ProcessReportModel;
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
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 *
 * @author oyas
 */
@Dependent
@Repository
public abstract class ProcessRepository extends RepositoryBase<Process, Process> implements CriteriaSupport<Process> {

	@Override
	public List<Process> lookupQuery(String searchText) {
		return criteria().getResultList();
	}

	@Override
	public List<Process> suggestion(String searchText) {
		return criteria()
				.or(criteria().like(Process_.processNo, "%" + searchText + "%"),
					criteria().like(Process_.topic, "%" + searchText + "%"))
				.eq(Process_.status, ProcessStatus.OPEN).getResultList();		
	}
	

	public List<Process> lookupQuery(String searchText, Long accountId, ProcessType type) {
		return criteria().eq(Process_.type, type).eq(Process_.status, ProcessStatus.OPEN)
				.join(Process_.account, where(Contact.class).eq(Contact_.id, accountId)).getResultList();
	}

	public List<Process> lookupQuery(String searchText, ProcessType type) {
		return criteria().eq(Process_.type, type).eq(Process_.status, ProcessStatus.OPEN).getResultList();
	}

	@Override
	public List<Process> browseQuery(QueryDefinition queryDefinition) {
		List<Filter<Process, ?, ?>> filters = queryDefinition.getFilters();

		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();

		CriteriaQuery<Process> criteriaQuery = criteriaBuilder.createQuery(Process.class);

		Root<Process> from = criteriaQuery.from(Process.class);
		Join<Process, Contact> pa = from.join(Process_.account, JoinType.LEFT);

		// Filtreleri ekleyelim.
		List<Predicate> predicates = new ArrayList<>();

		decorateFilters(filters, predicates, criteriaBuilder, from);

		buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);

		// Oluşan filtreleri sorgumuza ekliyoruz
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		// Öncelikle default sıralama verelim eğer kullanıcı tarafından tanımlı
		// sıralama var ise onu setleyelim
		if (queryDefinition.getSorters().isEmpty()) {
			criteriaQuery.orderBy(criteriaBuilder.asc(from.get(Process_.topic)));
		} else {
			criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
		}

		// Haydi bakalım sonuçları alalım
		TypedQuery<Process> typedQuery = entityManager().createQuery(criteriaQuery);
		typedQuery.setMaxResults(queryDefinition.getResultLimit());
		List<Process> resultList = typedQuery.getResultList();

		return resultList;
	}

	private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates,
			Root<? extends Process> from) {
		if (!Strings.isNullOrEmpty(searchText)) {
			predicates.add(criteriaBuilder.or(
					criteriaBuilder.like(from.get(Process_.account).get(Contact_.name), "%" + searchText + "%"),
					criteriaBuilder.like(from.get(Process_.topic), "%" + searchText + "%")));
		}
	}

	public abstract Process findAnyByProcessNo(String processNo);

    public List<ProcessReportModel> findByFilter(ProcessReportFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();

        CriteriaQuery<ProcessReportModel> criteriaQuery = criteriaBuilder
            .createQuery(ProcessReportModel.class);

        Root<Process> from = criteriaQuery.from(Process.class);

        criteriaQuery.multiselect(
            from.get(Process_.processNo),
            from.get(Process_.topic),
            from.get(Process_.account),
            from.get(Process_.type).as(String.class),
            from.get(Process_.status).as(String.class)
        );

        List<Predicate> predicates = new ArrayList<>();

        if (!Strings.isNullOrEmpty(filter.getCode())) {
            predicates.add(criteriaBuilder
                .like(from.get(Process_.processNo), "%" + filter.getCode() + "%"));
        }

        if (!Strings.isNullOrEmpty(filter.getName())) {
            predicates.add(criteriaBuilder
                .like(from.get(Process_.account).get(Contact_.name), "%" + filter.getName() + "%"));
        }

        if (filter.getCorporationType() != null) {
            Path<Corporation> corpPath = criteriaBuilder
                .treat(from.get(Process_.account), Corporation.class);
            predicates.add(criteriaBuilder
                .equal(corpPath.get(Corporation_.corporationType), filter.getCorporationType()));
        }

        if (filter.getContactCategory() != null) {
            predicates.add(criteriaBuilder
                .like(from.get(Process_.account).get(Contact_.category).get(ContactCategory_.path),
                    filter.getContactCategory().getPath()));
        }

        if (filter.getIndustry() != null) {
            predicates.add(criteriaBuilder
                .like(from.get(Process_.account).get(Contact_.industry).get(Industry_.path),
                    filter.getIndustry().getPath()));
        }

        if (filter.getTerritory() != null) {
            predicates.add(criteriaBuilder
                .equal(from.get(Process_.account).get(Contact_.territory), filter.getTerritory()));
        }

        if (!Strings.isNullOrEmpty(filter.getOwner())) {
            predicates.add(criteriaBuilder
                .equal(from.get(Process_.account).get(Contact_.owner), filter.getOwner()));
        }

        if (filter.getStatus() != null) {
            predicates.add(criteriaBuilder
                .equal(from.get(Process_.status), filter.getStatus()));
        }

        if (filter.getType() != null) {
            predicates.add(criteriaBuilder
                .equal(from.get(Process_.type), filter.getType()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        criteriaQuery.orderBy(criteriaBuilder.desc(from.get(Process_.processNo)));

        TypedQuery<ProcessReportModel> typedQuery = entityManager().createQuery(criteriaQuery);
        List<ProcessReportModel> resultList = typedQuery.getResultList();

        return resultList;
    }

}
