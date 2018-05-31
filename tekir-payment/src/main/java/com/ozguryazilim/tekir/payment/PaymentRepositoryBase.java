/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.PaymentBase;
import com.ozguryazilim.tekir.entities.PaymentBase_;
import com.ozguryazilim.tekir.entities.Process_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroup_;
import com.ozguryazilim.tekir.entities.VoucherProcessBase_;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author oyas
 */
public abstract class PaymentRepositoryBase<E extends PaymentBase, V extends PaymentViewModel> extends VoucherRepositoryBase<E, V>{
    
    
    public abstract Class<E> getEntityClass();
    public abstract Class<V> getViewModelClass();
    
    @Override
    public List<V> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<E, ?, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<V> criteriaQuery = criteriaBuilder.createQuery(getViewModelClass());

        //From 
        Root<E> from = criteriaQuery.from(getEntityClass());
        Join<E, VoucherGroup> joinGroup = from.join(PaymentBase_.group, JoinType.LEFT);

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
        TypedQuery<V> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<V> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Override
    public List<E> suggestion(String searchText) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void buildVieModelSelect(CriteriaQuery<V> criteriaQuery, Root<? extends E> from) {
        criteriaQuery.multiselect(
                from.get(PaymentBase_.id),
                from.get(VoucherProcessBase_.process).get(Process_.id),
                from.get(VoucherProcessBase_.process).get(Process_.processNo),
                from.get(VoucherProcessBase_.account).get(Contact_.id),
                from.get(VoucherProcessBase_.account).get(Contact_.name),
                from.get(VoucherProcessBase_.account).type(),
                from.get("tags"),
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
                from.get(PaymentBase_.financeAccount).get(FinanceAccount_.id),
                from.get(PaymentBase_.financeAccount).get(FinanceAccount_.name),
                from.get(PaymentBase_.currency),
                from.get(PaymentBase_.amount)
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends E> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(from.get(VoucherBase_.topic), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(VoucherBase_.voucherNo), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(VoucherProcessBase_.account).get(Contact_.name), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(PaymentBase_.financeAccount).get(FinanceAccount_.name), "%" + searchText + "%")
                    )
            );
        }
    }
}
