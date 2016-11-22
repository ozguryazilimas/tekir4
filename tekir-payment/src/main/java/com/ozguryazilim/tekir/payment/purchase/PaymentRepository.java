/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.payment.purchase;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.BankCashAccount_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.Payment;
import com.ozguryazilim.tekir.entities.PaymentReceived_;
import com.ozguryazilim.tekir.entities.Payment_;
import com.ozguryazilim.tekir.entities.Process_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroup_;
import com.ozguryazilim.tekir.entities.VoucherProcessBase_;
import com.ozguryazilim.tekir.payment.PaymentViewModel;
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
@Repository
@Dependent
public abstract class PaymentRepository extends VoucherRepositoryBase<Payment, PaymentViewModel>{
    @Override
    public List<PaymentViewModel> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<Payment, ?, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<PaymentViewModel> criteriaQuery = criteriaBuilder.createQuery(PaymentViewModel.class);

        //From 
        Root<Payment> from = criteriaQuery.from(Payment.class);
        Join<Payment, VoucherGroup> joinGroup = from.join(Payment_.group, JoinType.LEFT);

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
        TypedQuery<PaymentViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<PaymentViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Override
    public List<Payment> suggestion(String searchText) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void buildVieModelSelect(CriteriaQuery<PaymentViewModel> criteriaQuery, Root<? extends Payment> from) {
        criteriaQuery.multiselect(
                from.get(PaymentReceived_.id),
                from.get(VoucherProcessBase_.process).get(Process_.id),
                from.get(VoucherProcessBase_.process).get(Process_.processNo),
                from.get(VoucherProcessBase_.account).get(Contact_.id),
                from.get(VoucherProcessBase_.account).get(Contact_.name),
                from.get(VoucherProcessBase_.account).type(),
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
                from.get(Payment_.bankCashAccount).get(BankCashAccount_.id),
                from.get(Payment_.bankCashAccount).get(BankCashAccount_.name),
                from.get(Payment_.currency),
                from.get(Payment_.amount)
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends Payment> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(from.get(VoucherBase_.topic), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(VoucherBase_.voucherNo), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(VoucherProcessBase_.account).get(Contact_.name), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(Payment_.bankCashAccount).get(BankCashAccount_.name), "%" + searchText + "%")
                    )
            );
        }
    }
}
