package com.ozguryazilim.tekir.quote;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Commodity_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.Process_;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.Criteria;

import javax.enterprise.context.Dependent;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.entities.QuoteItem;
import com.ozguryazilim.tekir.entities.QuoteItem_;
import com.ozguryazilim.tekir.entities.Quote_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroup_;
import com.ozguryazilim.tekir.entities.VoucherProcessBase_;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;

import java.util.ArrayList;
import java.util.Date;
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
        List<Filter<Quote, ?, ?>> filters = queryDefinition.getFilters();

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
                from.get(VoucherProcessBase_.process).get(Process_.id),
                from.get(VoucherProcessBase_.process).get(Process_.processNo),
                from.get(VoucherProcessBase_.account).get(Contact_.id),
                from.get(VoucherProcessBase_.account).get(Contact_.name),
                from.get(VoucherProcessBase_.account).type(),
                //FIXME: from.get(VoucherBase_.code),
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
                from.get(Quote_.total),
                from.get(Quote_.currency)
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends Quote> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(from.get(VoucherBase_.topic), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(VoucherBase_.voucherNo), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(VoucherProcessBase_.account).get(Contact_.name), "%" + searchText + "%")
                    )
            );
        }
    }

    public List<Quote> findByStateAndOwner(VoucherState state, String owner) {
        Criteria<Quote, Quote> cr = criteria();

        if (state != null) {
            cr.eq(Quote_.state, state);
        }

        if (!Strings.isNullOrEmpty(owner)) {
            cr.eq(Quote_.owner, owner);
        }

        return cr.createQuery().getResultList();

    }

    public List<QuoteViewModel> findExiperedQuotes(Date date) {
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye ViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<QuoteViewModel> criteriaQuery = criteriaBuilder.createQuery(QuoteViewModel.class);

        //From 
        Root<Quote> from = criteriaQuery.from(Quote.class);
        Join<Quote, VoucherGroup> joinGroup = from.join(Quote_.group, JoinType.LEFT);

        //Sonuç filtremiz
        buildVieModelSelect(criteriaQuery, from);

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        //Tarih verilen parametreden küçük ve eşit olanlar.
        predicates.add(criteriaBuilder.lessThanOrEqualTo(from.get(Quote_.expireDate), date));
        
        //Olumlu ya da Olumsuz kapanmamış olanlar.
        predicates.add(criteriaBuilder.notLike(from.get(VoucherBase_.state).as(String.class), "CLOSE-%"));
        
        //Oluşan filtreleri sorgumuza ekliyoruz
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        //Haydi bakalım sonuçları alalım
        TypedQuery<QuoteViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        List<QuoteViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }
    
    /**
     * Geriye id'si verilen Quote için QuoteItem listesi döndürür.
     * 
     * @param id
     * @return 
     */
    public List<QuoteItemViewModel> findQuoteItems( Long id ){
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye ViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<QuoteItemViewModel> criteriaQuery = criteriaBuilder.createQuery(QuoteItemViewModel.class);

        //From 
        Root<QuoteItem> from = criteriaQuery.from(QuoteItem.class);

        
        criteriaQuery.multiselect(
                from.get(QuoteItem_.id),
                from.get(QuoteItem_.commodity).get(Commodity_.id),
                from.get(QuoteItem_.commodity).get(Commodity_.name),
                from.get(QuoteItem_.info),
                from.get(QuoteItem_.quantity),
                from.get(QuoteItem_.price),
                from.get(QuoteItem_.discountRate),
                from.get(QuoteItem_.discount),
                from.get(QuoteItem_.total),
                from.get(QuoteItem_.lineTotal)
        );
        
        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        //Parent id'si üzerinden filtreler. 
        //Not: Burada type çevrimi ile ilgili sorun var. Gelen sınıf üzerinde id tanımı yok. O yüzden string ile soruyoruz.
        predicates.add(criteriaBuilder.equal(from.get(QuoteItem_.master).get("id"), id));
        
        //Oluşan filtreleri sorgumuza ekliyoruz
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        
        //Haydi bakalım sonuçları alalım
        TypedQuery<QuoteItemViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        List<QuoteItemViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }
}
