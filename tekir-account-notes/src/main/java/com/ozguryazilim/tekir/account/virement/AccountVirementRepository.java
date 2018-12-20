package com.ozguryazilim.tekir.account.virement;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.AccountVirement;
import com.ozguryazilim.tekir.entities.AccountVirement_;
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
public abstract class AccountVirementRepository extends VoucherRepositoryBase<AccountVirement, AccountVirementViewModel> {

    @Override
    public List<AccountVirementViewModel> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<AccountVirement, ?, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<AccountVirementViewModel> criteriaQuery = criteriaBuilder.createQuery(AccountVirementViewModel.class);

        //From 
        Root<AccountVirement> from = criteriaQuery.from(AccountVirement.class);
        Join<AccountVirement, VoucherGroup> joinGroup = from.join(AccountVirement_.group, JoinType.LEFT);

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
        TypedQuery<AccountVirementViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<AccountVirementViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }

    private void buildVieModelSelect(CriteriaQuery<AccountVirementViewModel> criteriaQuery, Root<? extends AccountVirement> from) {
        criteriaQuery.multiselect(
                from.get(AccountVirement_.id),
                from.get(AccountVirement_.fromAccount),
                from.get(AccountVirement_.toAccount),
                from.get(AccountVirement_.currency),
                from.get(AccountVirement_.amount),
                from.get("tags"),
                from.get(VoucherBase_.voucherNo),
                from.get(VoucherBase_.info),                from.get(VoucherBase_.referenceNo),
                from.get(VoucherBase_.date),
                from.get(VoucherBase_.owner),
                from.get(VoucherBase_.state),
                from.get(VoucherBase_.stateReason),
                from.get(VoucherBase_.stateInfo),
                from.get(VoucherBase_.group),
                from.get(VoucherBase_.topic)
                
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends AccountVirement> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(from.get(AccountVirement_.info), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(VoucherBase_.voucherNo), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(AccountVirement_.fromAccount).get(Contact_.name), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(AccountVirement_.toAccount).get(Contact_.name), "%" + searchText + "%")
                    )
            );
        }
    }
}
