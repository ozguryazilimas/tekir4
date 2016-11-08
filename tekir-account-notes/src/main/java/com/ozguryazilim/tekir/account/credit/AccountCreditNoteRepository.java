/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.credit;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.AccountCreditNote;
import com.ozguryazilim.tekir.entities.AccountCreditNote_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
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

/**
 *
 * @author oyas
 */
@Dependent
@Repository
public abstract class AccountCreditNoteRepository extends VoucherRepositoryBase<AccountCreditNote, AccountCreditNoteViewModel> {

    @Override
    public List<AccountCreditNoteViewModel> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<AccountCreditNote, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<AccountCreditNoteViewModel> criteriaQuery = criteriaBuilder.createQuery(AccountCreditNoteViewModel.class);

        //From 
        Root<AccountCreditNote> from = criteriaQuery.from(AccountCreditNote.class);
        Join<AccountCreditNote, VoucherGroup> joinGroup = from.join(AccountCreditNote_.group, JoinType.LEFT);

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
        TypedQuery<AccountCreditNoteViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<AccountCreditNoteViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }

    private void buildVieModelSelect(CriteriaQuery<AccountCreditNoteViewModel> criteriaQuery, Root<? extends AccountCreditNote> from) {
        criteriaQuery.multiselect(
                from.get(AccountCreditNote_.id),
                from.get(VoucherBase_.code),
                from.get(VoucherBase_.voucherNo),
                from.get(VoucherBase_.info),
                from.get(VoucherBase_.referenceNo),
                from.get(VoucherBase_.date),
                from.get(VoucherBase_.owner),
                from.get(VoucherBase_.state),
                from.get(VoucherBase_.stateReason),
                from.get(VoucherBase_.stateInfo),
                from.get(VoucherBase_.group),
                from.get(VoucherBase_.topic),
                from.get(AccountCreditNote_.account),
                from.get(AccountCreditNote_.currency),
                from.get(AccountCreditNote_.amount)
                
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends AccountCreditNote> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(from.get(AccountCreditNote_.info), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(VoucherBase_.voucherNo), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(AccountCreditNote_.account).get(Contact_.name), "%" + searchText + "%")
                    )
            );
        }
    }
}
