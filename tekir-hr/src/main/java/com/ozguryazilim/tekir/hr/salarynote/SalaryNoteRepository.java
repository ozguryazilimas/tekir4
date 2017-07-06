/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.salarynote;

import java.util.ArrayList;
import java.util.Date;
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
import org.apache.deltaspike.data.api.criteria.Criteria;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.SalaryNote;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.SalaryNote_;
import com.ozguryazilim.tekir.entities.VoucherGroup_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;


/**
 * Repository Class
 * 
 * @author oktay
 *
 */
@Repository
@Dependent
public abstract class SalaryNoteRepository extends VoucherRepositoryBase<SalaryNote, SalaryNoteViewModel>{

	 @Override
	    public List<SalaryNoteViewModel>  browseQuery(QueryDefinition queryDefinition) {
		 	
		 List<Filter<SalaryNote, ?, ?>> filters = queryDefinition.getFilters();

	        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
	        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
	        CriteriaQuery<SalaryNoteViewModel> criteriaQuery = criteriaBuilder.createQuery(SalaryNoteViewModel.class);

	        //From 
	        Root<SalaryNote> from = criteriaQuery.from(SalaryNote.class);
	        Join<SalaryNote, VoucherGroup> joinGroup = from.join(SalaryNote_.group, JoinType.LEFT);
	        Join<SalaryNote, FinanceAccount> joinFinancAccount = from.join(SalaryNote_.financeAccount, JoinType.LEFT);
	        
	        //Sonuç filtremiz
	        buildVieModelSelect(criteriaQuery, from);

	        //Filtreleri ekleyelim.
	        List<Predicate> predicates = new ArrayList<>();

	        decorateFilters(filters, predicates, criteriaBuilder, from);

	        buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);

	        //RowLevel yetki kontrol filtresi
	        buildOwnerFilter(predicates, from);

	        //Oluşan filtreleri sorgumuza ekliyoruz
	        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

	        // Öncelikle default sıralama verelim eğer kullanıcı tarafından tanımlı sıralama var ise onu setleyelim
	        if (queryDefinition.getSorters().isEmpty()) {
	            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(VoucherBase_.date)));
	        } else {
	            criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
	        }

	        //Haydi bakalım sonuçları alalım
	        TypedQuery<SalaryNoteViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
	        typedQuery.setMaxResults(queryDefinition.getResultLimit());
	        List<SalaryNoteViewModel> resultList = typedQuery.getResultList();

	        return resultList;
		 
	 }

	    @Override
	    public List<SalaryNote> suggestion(String searchText) {
	        throw new UnsupportedOperationException("Not supported yet.");
	    }

	private void buildVieModelSelect(CriteriaQuery<SalaryNoteViewModel> criteriaQuery, Root<? extends SalaryNote> from) {
		criteriaQuery.multiselect(
                from.get(SalaryNote_.id),
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
                from.get(SalaryNote_.financeAccount).get(FinanceAccount_.id),
                from.get(SalaryNote_.financeAccount).get(FinanceAccount_.name),
                from.get(SalaryNote_.financeAccount).get(FinanceAccount_.bank),
                from.get(SalaryNote_.financeAccount).get(FinanceAccount_.iban),
                from.get(SalaryNote_.paymentDate),
                from.get(SalaryNote_.currency),
                from.get(SalaryNote_.total)         
        );
		
	}
 
	private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates,
			Root<SalaryNote> from) {
		if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(from.get(VoucherBase_.topic), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(SalaryNote_.voucherNo), "%" + searchText + "%"))
            );
        }

	}
	
    public List<SalaryNote> findByStateAndOwner(VoucherState state, String owner) {
        Criteria<SalaryNote, SalaryNote> cr = criteria();

        if (state != null) {
            cr.eq(SalaryNote_.state, state);
        }

        if (!Strings.isNullOrEmpty(owner)) {
            cr.eq(SalaryNote_.owner, owner);
        }

        return cr.createQuery().getResultList();

    }
    
    public List<SalaryNoteViewModel> findExiperedSalesNotes(Date date) {
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye ViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<SalaryNoteViewModel> criteriaQuery = criteriaBuilder.createQuery(SalaryNoteViewModel.class);

        //From 
        Root<SalaryNote> from = criteriaQuery.from(SalaryNote.class);
        Join<SalaryNote, VoucherGroup> joinGroup = from.join(SalaryNote_.group, JoinType.LEFT);
        Join<SalaryNote, FinanceAccount> joinFinancAccount = from.join(SalaryNote_.financeAccount, JoinType.LEFT);

        //Sonuç filtremiz
        buildVieModelSelect(criteriaQuery, from);

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        //Tarih verilen parametreden küçük ve eşit olanlar.
        predicates.add(criteriaBuilder.lessThanOrEqualTo(from.get(VoucherBase_.date), date));
        
        //Olumlu ya da Olumsuz kapanmamış olanlar.
        predicates.add(criteriaBuilder.notLike(from.get(VoucherBase_.state).as(String.class), "CLOSE-%"));
        
        //Oluşan filtreleri sorgumuza ekliyoruz
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        //Haydi bakalım sonuçları alalım
        TypedQuery<SalaryNoteViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        List<SalaryNoteViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }
    
}
