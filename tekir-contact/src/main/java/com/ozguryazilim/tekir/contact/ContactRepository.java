package com.ozguryazilim.tekir.contact;

import com.google.common.base.Strings;
import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;
import com.ozguryazilim.telve.data.RepositoryBase;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Repository Class
 *
 * @author
 */
@Repository
@Dependent
public abstract class ContactRepository
        extends
        RepositoryBase<Contact, ContactViewModel>
        implements
        CriteriaSupport<Contact> {

    @Override
    public List<ContactViewModel> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<Contact, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<ContactViewModel> criteriaQuery = criteriaBuilder.createQuery(ContactViewModel.class);

        //From Tabii ki User
        Root<Contact> from = criteriaQuery.from(Contact.class);

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
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(Contact_.name)));
        } else {
            criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
        }

        //Haydi bakalım sonuçları alalım
        TypedQuery<ContactViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<ContactViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }

    
    @Override
    public List<ContactViewModel> lookupQuery(String searchText) {
        return lookupQuery(searchText, null);
    }
    
    
    public List<ContactViewModel> lookupQuery(String searchText, String type ) {

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<ContactViewModel> criteriaQuery = criteriaBuilder.createQuery(ContactViewModel.class);

        //From Profile'a göre tip seçiyoruz...
        Root<? extends Contact> from = null;
        if( !Strings.isNullOrEmpty(type)){
            switch( type ){
                case "Person" : 
                    from = criteriaQuery.from(Person.class);
                    break;
                case "Corporation" : 
                    from = criteriaQuery.from(Corporation.class);
                    break;
                default : 
                    from = criteriaQuery.from(Contact.class);
                    break;
            }
            
        } else {
            from = criteriaQuery.from(Contact.class);
        }

        //Sonuç filtremiz
        buildVieModelSelect(criteriaQuery, from);

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        buildSearchTextControl(searchText, criteriaBuilder, predicates, from);
        
        //Oluşan filtreleri sorgumuza ekliyoruz
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        
        criteriaQuery.orderBy(criteriaBuilder.asc(from.get(Contact_.name)));
        

        //Haydi bakalım sonuçları alalım
        TypedQuery<ContactViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(100);
        List<ContactViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }
    
    @Override
    public List<Contact> suggestion(String searchText) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void buildVieModelSelect(CriteriaQuery<ContactViewModel> criteriaQuery, Root<? extends Contact> from) {
        criteriaQuery.multiselect(
                from.get(Contact_.id),
                from.get(Contact_.code),
                from.get(Contact_.name),
                from.get(Contact_.info),
                from.get(Contact_.active)
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends Contact> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(criteriaBuilder.or(criteriaBuilder.like(from.get(Contact_.code), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(Contact_.name), "%" + searchText + "%")));
        }
    }

    
    
    
    
}
