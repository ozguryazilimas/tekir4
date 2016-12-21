/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.virement;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.FinanceAccountVirement_;
import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.tekir.entities.FinanceAccountVirement;
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
public abstract class FinanceAccountVirementRepository extends VoucherRepositoryBase<FinanceAccountVirement, FinanceAccountVirementViewModel> {

    @Override
    public List<FinanceAccountVirementViewModel> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<FinanceAccountVirement, ?, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<FinanceAccountVirementViewModel> criteriaQuery = criteriaBuilder.createQuery(FinanceAccountVirementViewModel.class);

        //From 
        Root<FinanceAccountVirement> from = criteriaQuery.from(FinanceAccountVirement.class);
        Join<FinanceAccountVirement, VoucherGroup> joinGroup = from.join(FinanceAccountVirement_.group, JoinType.LEFT);

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
        TypedQuery<FinanceAccountVirementViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<FinanceAccountVirementViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }

    private void buildVieModelSelect(CriteriaQuery<FinanceAccountVirementViewModel> criteriaQuery, Root<? extends FinanceAccountVirement> from) {
        criteriaQuery.multiselect(
                from.get(FinanceAccountVirement_.id),
                from.get(FinanceAccountVirement_.fromAccount),
                from.get(FinanceAccountVirement_.toAccount),
                from.get(FinanceAccountVirement_.currency),
                from.get(FinanceAccountVirement_.amount),
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
                from.get(VoucherBase_.topic)
                
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends FinanceAccountVirement> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(from.get(FinanceAccountVirement_.info), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(VoucherBase_.voucherNo), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(FinanceAccountVirement_.fromAccount).get(FinanceAccount_.name), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(FinanceAccountVirement_.toAccount).get(FinanceAccount_.name), "%" + searchText + "%")
                    )
            );
        }
    }
}
