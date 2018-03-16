package com.ozguryazilim.tekir.einvoice;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Einvoice;
import com.ozguryazilim.tekir.entities.Einvoice_;
import com.ozguryazilim.tekir.entities.Invoice;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class EinvoiceRepository<E extends Einvoice, V extends EinvoiceViewModel> extends RepositoryBase<E, V> {

    public abstract Class<E> getEntityClass();

    protected abstract Class<V> getViewModelClass();

    @Override
    public List<V> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<E, ?, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<V> criteriaQuery = criteriaBuilder.createQuery(getViewModelClass());

        //From
        Root<E> from = criteriaQuery.from(getEntityClass());

        //Sonuç filtremiz
        buildVieModelSelect(criteriaQuery, from);

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        decorateFilters(filters, predicates, criteriaBuilder, from);

        buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);

        //Oluşan filtreleri sorgumuza ekliyoruz
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // Öncelikle default sıralama verelim eğer kullanıcı tarafından tanımlı sıralama var ise onu setleyelim
        if (queryDefinition.getSorters().isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(Einvoice_.updateDate)));
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
        criteriaQuery.multiselect(from.get(Einvoice_.id), from.get(Einvoice_.invoice), from.get(Einvoice_
                .returnedMessage), from.get(Einvoice_.einvoiceCode), from.get(Einvoice_.einvoiceStatus));
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate>
            predicates, Root<? extends E> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(criteriaBuilder.or(criteriaBuilder.like(from.get(Einvoice_.returnedMessage), "%" +
                    searchText + "%")));
        }
    }
}