package com.ozguryazilim.tekir.hr.credit;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.EmployeeCreditNote;
import com.ozguryazilim.tekir.entities.EmployeeCreditNote_;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Erdem Uslu
 */
@Dependent
@Repository
public abstract class EmployeeCreditNoteRepository extends VoucherRepositoryBase<EmployeeCreditNote, EmployeeCreditNoteViewModel> {

    @Override
    public List<EmployeeCreditNoteViewModel> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<EmployeeCreditNote, ?, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<EmployeeCreditNoteViewModel> criteriaQuery = criteriaBuilder.createQuery(EmployeeCreditNoteViewModel.class);

        //From 
        Root<EmployeeCreditNote> from = criteriaQuery.from(EmployeeCreditNote.class);
        from.join(VoucherBase_.group, JoinType.LEFT);
        from.join(EmployeeCreditNote_.employee, JoinType.LEFT);

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
        TypedQuery<EmployeeCreditNoteViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<EmployeeCreditNoteViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }

    private void buildVieModelSelect(CriteriaQuery<EmployeeCreditNoteViewModel> criteriaQuery, Root<? extends EmployeeCreditNote> from) {
        criteriaQuery.multiselect(
                from.get(EmployeeCreditNote_.id),
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
                from.get(EmployeeCreditNote_.employee),
                from.get(EmployeeCreditNote_.type),
                from.get(EmployeeCreditNote_.financeAccount),
                from.get(EmployeeCreditNote_.paymentDate),
                from.get(EmployeeCreditNote_.currency),
                from.get(EmployeeCreditNote_.amount)
                
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends EmployeeCreditNote> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(
                    criteriaBuilder.or(
                            criteriaBuilder.like(from.get(EmployeeCreditNote_.info), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(VoucherBase_.voucherNo), "%" + searchText + "%"),
                            criteriaBuilder.like(from.get(EmployeeCreditNote_.employee).get(Contact_.name), "%" + searchText + "%")
                    )
            );
        }
    }
}
