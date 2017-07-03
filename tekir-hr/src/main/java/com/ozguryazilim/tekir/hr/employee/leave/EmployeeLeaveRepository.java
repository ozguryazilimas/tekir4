package com.ozguryazilim.tekir.hr.employee.leave;

import java.util.ArrayList;
import java.util.Collections;
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

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.AbstractPerson;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactAddress;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactPhone;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Employee;
import com.ozguryazilim.tekir.entities.EmployeeLeave;
import com.ozguryazilim.tekir.entities.EmployeeLeave_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.Contact_;

import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;

/**
 *
 * @author oktay
 */
@Repository
@Dependent
public abstract class EmployeeLeaveRepository extends RepositoryBase<EmployeeLeave, EmployeeLeaveViewModel>
		implements CriteriaSupport<EmployeeLeave> {

	private List<String> ownerFilter;

	@Override
	public List<EmployeeLeaveViewModel> browseQuery(QueryDefinition queryDefinition) {
		List<Filter<EmployeeLeave, ?, ?>> filters = queryDefinition.getFilters();

		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		// Geriye PersonLeaveViewModel dönecek cq'yu ona göre oluşturuyoruz.
		CriteriaQuery<EmployeeLeaveViewModel> criteriaQuery = criteriaBuilder.createQuery(EmployeeLeaveViewModel.class);

		// From Tabii ki EmployeeLeave
		Root<EmployeeLeave> from = criteriaQuery.from(EmployeeLeave.class);
		Join<EmployeeLeave, Employee> pm = from.join(EmployeeLeave_.employee, JoinType.LEFT);

		// Sonuç filtremiz
		buildVieModelSelect(criteriaQuery, from);

		// Filtreleri ekleyelim.
		List<Predicate> predicates = new ArrayList<>();

		decorateFilters(filters, predicates, criteriaBuilder, from);

        buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);
		
		if (ownerFilter != null) {
			predicates.add(from.get(VoucherBase_.owner).in(ownerFilter));
		}

		// Oluşan filtreleri sorgumuza ekliyoruz
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

        // Öncelikle default sıralama verelim eğer kullanıcı tarafından tanımlı sıralama var ise onu setleyelim
        if (queryDefinition.getSorters().isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(EmployeeLeave_.employee).get(Contact_.name)));
        } else {
            criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
        }
        
		// Haydi bakalım sonuçları alalım
		TypedQuery<EmployeeLeaveViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
		typedQuery.setMaxResults(queryDefinition.getResultLimit());
		List<EmployeeLeaveViewModel> resultList = typedQuery.getResultList();

		return resultList;

	}

	@Override
	public List<EmployeeLeaveViewModel> lookupQuery(String searchText) {
		return lookupQuery(searchText, null, "");
	}

	public List<EmployeeLeaveViewModel> lookupQuery(String searchText, String type) {
		return lookupQuery(searchText, type, "");
	}

	public List<EmployeeLeaveViewModel> lookupQuery(String searchText, String type, String roles) {
		List<String> rls = Collections.emptyList();
		if (!Strings.isNullOrEmpty(roles)) {
			rls = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(roles);
		}

		return lookupQuery(searchText, type, rls);
	}

	private List<EmployeeLeaveViewModel> lookupQuery(String searchText, String type, List<String> roles) {
		CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
		// Geriye PersonelLeaveModel dönecek cq'yu ona göre oluşturuyoruz.
		CriteriaQuery<EmployeeLeaveViewModel> criteriaQuery = criteriaBuilder.createQuery(EmployeeLeaveViewModel.class);

		Root<EmployeeLeave> from = criteriaQuery.from(EmployeeLeave.class);
		Join<EmployeeLeave, Employee> pm = from.join(EmployeeLeave_.employee, JoinType.LEFT);

		// Sonuç filtremiz
		buildVieModelSelect(criteriaQuery, from);

		// Filtreleri ekleyelim.
		List<Predicate> predicates = new ArrayList<>();
	    
		buildSearchTextControl(searchText, criteriaBuilder, predicates, from);
		
		/*
		 * Hibernate bug'ı yüzünden isim kullanıyor ve as ile arama yapıyoruz.
		 * Converter annotation'ı ile derdi var.
		 * https://hibernate.atlassian.net/browse/HHH-10464
		 */
		if (!roles.isEmpty()) {
			roles.forEach(rs -> predicates
					.add(criteriaBuilder.like(from.get("contactRoles").as(String.class), "%" + rs + "%")));
		}

		// Oluşan filtreleri sorgumuza ekliyoruz
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		 criteriaQuery.orderBy(criteriaBuilder.asc(from.get(EmployeeLeave_.employee).get(Contact_.name)));
		
		// Haydi bakalım sonuçları alalım
		TypedQuery<EmployeeLeaveViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
		typedQuery.setMaxResults(100);
		List<EmployeeLeaveViewModel> resultList = typedQuery.getResultList();

		return resultList;
	}

    @Override
    public List<EmployeeLeave> suggestion(String searchText) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
	
	private void buildVieModelSelect(CriteriaQuery<EmployeeLeaveViewModel> criteriaQuery, Root<EmployeeLeave> from) {
        criteriaQuery.multiselect(
                from.get(EmployeeLeave_.id),
                from.get(EmployeeLeave_.paid),
                from.get(EmployeeLeave_.annual),
                from.get(EmployeeLeave_.startDate),
                from.get(EmployeeLeave_.endDate),
                from.get(EmployeeLeave_.leaveDay),
                
                from.get(EmployeeLeave_.employee).get(Contact_.id),
                from.get(EmployeeLeave_.employee).get(Contact_.name)
        );

	}
	

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends EmployeeLeave> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(criteriaBuilder.like(from.get(EmployeeLeave_.employee).get(Contact_.name), "%" + searchText + "%"));
        }
    }

	public List<String> getOwnerFilter() {
		return ownerFilter;
	}

	public void setOwnerFilter(List<String> ownerFilter) {
		this.ownerFilter = ownerFilter;
	}

}
