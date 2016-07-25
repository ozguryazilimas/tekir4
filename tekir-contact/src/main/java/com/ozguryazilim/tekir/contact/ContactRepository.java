package com.ozguryazilim.tekir.contact;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;
import com.ozguryazilim.telve.data.RepositoryBase;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.ContactAddress;
import com.ozguryazilim.tekir.entities.ContactEMail;
import com.ozguryazilim.tekir.entities.ContactInformation_;
import com.ozguryazilim.tekir.entities.ContactPhone;
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
        Join<Contact, ContactPhone> pm = from.join(Contact_.primaryMobile, JoinType.LEFT);
        Join<Contact, ContactPhone> pp = from.join(Contact_.primaryPhone, JoinType.LEFT);
        Join<Contact, ContactPhone> pf = from.join(Contact_.primaryFax, JoinType.LEFT);
        Join<Contact, ContactEMail> pe = from.join(Contact_.primaryEmail, JoinType.LEFT);
        Join<Contact, ContactAddress> pa = from.join(Contact_.primaryAddress, JoinType.LEFT);

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
        return lookupQuery(searchText, null, null);
    }
    
    public List<ContactViewModel> lookupQuery(String searchText, String type) {
        return lookupQuery(searchText, type, null);
    }
    
    /**
     * Contact sorgusu yapar.
     * 
     * rol listesini and ile bağlar : Account, Customer verildiyse ikisinin de role lisesinde olmasını kontrol eder.
     * 
     * @param searchText code ve ad içerisinde like ile aranır
     * @param type Person/Corporation değerlerine göre tarama yapar
     * @param roles virgüllerle ayrılmış olan rollere göre sorgu yapar. farklı değerleri and ile bağlar.
     * @return 
     */
    public List<ContactViewModel> lookupQuery(String searchText, String type, String roles ) {

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<ContactViewModel> criteriaQuery = criteriaBuilder.createQuery(ContactViewModel.class);

        //From Profile'a göre tip seçiyoruz...
        /*Root<? extends Contact> from = null;
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
        }*/
        
        Root<Contact> from = criteriaQuery.from(Contact.class);
        Join<Contact, ContactPhone> pm = from.join(Contact_.primaryMobile, JoinType.LEFT);
        Join<Contact, ContactPhone> pp = from.join(Contact_.primaryPhone, JoinType.LEFT);
        //Join<Contact, ContactPhone> pf = from.join(Contact_.primaryFax, JoinType.LEFT);
        Join<Contact, ContactEMail> pe = from.join(Contact_.primaryEmail, JoinType.LEFT);
        //Join<Contact, ContactAddress> pa = from.join(Contact_.primaryAddress, JoinType.LEFT);

        //Sonuç filtremiz
        buildVieModelSelect(criteriaQuery, from);

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        buildSearchTextControl(searchText, criteriaBuilder, predicates, from);
        
        
        if( !Strings.isNullOrEmpty(type)){
            switch( type ){
                case "Person" : 
                    predicates.add(criteriaBuilder.equal(from.type(), Person.class));
                    break;
                case "Corporation" : 
                    predicates.add(criteriaBuilder.equal(from.type(), Corporation.class));
                    break;
                default : 
                    break;
            }
        }
     
        /*
        Hibernate bug'ı yüzünden isim kullanıyor ve as ile arama yapıyoruz. Converter annotation'ı ile derdi var.
        https://hibernate.atlassian.net/browse/HHH-10464
        */
        if( !Strings.isNullOrEmpty(roles)){
            List<String> rls = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(roles);
            rls.forEach(rs -> predicates.add(criteriaBuilder.like(from.get("contactRoles").as(String.class), "%"+ rs +"%")));
        }
        
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
                from.get(Contact_.active),
                from.type(),
                from.get(Contact_.primaryMobile).get(ContactInformation_.id),
                from.get(Contact_.primaryMobile).get(ContactInformation_.address),
                from.get(Contact_.primaryPhone).get(ContactInformation_.id),
                from.get(Contact_.primaryPhone).get(ContactInformation_.address),
                //from.get(Contact_.primaryFax).get(ContactInformation_.id),
                //from.get(Contact_.primaryFax).get(ContactInformation_.address),
                from.get(Contact_.primaryEmail).get(ContactInformation_.id),
                from.get(Contact_.primaryEmail).get(ContactInformation_.address)
                //from.get(Contact_.primaryAddress).get(ContactInformation_.id),
                //from.get(Contact_.primaryAddress).get(ContactInformation_.address)
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends Contact> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(criteriaBuilder.or(criteriaBuilder.like(from.get(Contact_.code), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(Contact_.name), "%" + searchText + "%")));
        }
    }

    
    
    
    
}