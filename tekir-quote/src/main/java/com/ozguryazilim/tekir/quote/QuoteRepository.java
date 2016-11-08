package com.ozguryazilim.tekir.quote;

import com.google.common.base.Strings;
import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.entities.Quote_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.VoucherGroup;
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
 * Repository Class
 *
 * @author
 */
@Repository
@Dependent
public abstract class QuoteRepository extends VoucherRepositoryBase<Quote, QuoteViewModel> {

    @Override
    public List<QuoteViewModel> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<Quote, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<QuoteViewModel> criteriaQuery = criteriaBuilder.createQuery(QuoteViewModel.class);

        //From 
        Root<Quote> from = criteriaQuery.from(Quote.class);
        Join<Quote, VoucherGroup> joinGroup = from.join(Quote_.group, JoinType.LEFT);

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
        TypedQuery<QuoteViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<QuoteViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Override
    public List<Quote> suggestion(String searchText) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void buildVieModelSelect(CriteriaQuery<QuoteViewModel> criteriaQuery, Root<? extends Quote> from) {
        criteriaQuery.multiselect(
                from.get(Quote_.id),
                from.get(VoucherProcessBase_.process),
                from.get(VoucherProcessBase_.account),
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

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends Quote> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            /*predicates.add(criteriaBuilder.or(criteriaBuilder.like(from.get(Quote_.account), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(VoucherBase_.voucherNo), "%" + searchText + "%")));*/
        }
    }
}
