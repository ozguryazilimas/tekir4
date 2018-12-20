package com.ozguryazilim.finance.account;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.entities.FinanceAccount_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class FinanceAccountRepository extends
        RepositoryBase<FinanceAccount, FinanceAccount>
        implements
        CriteriaSupport<FinanceAccount> {

    
    private List<String> ownerFilter;
    
    @Override
    public List<FinanceAccount> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<FinanceAccount, ?, ?>> filters = queryDefinition.getFilters();

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<FinanceAccount> criteriaQuery = criteriaBuilder.createQuery(FinanceAccount.class);
        

        //From Tabii ki 
        Root<FinanceAccount> from = criteriaQuery.from(FinanceAccount.class);
        

        //Sonuç filtremiz
        //buildVieModelSelect(criteriaQuery, from);

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        decorateFilters(filters, predicates, criteriaBuilder, from);
        
        buildSearchTextControl(queryDefinition.getSearchText(), criteriaBuilder, predicates, from);
        
        //Satır bazlı yetki kontrolü
        //Sorun 1: kullanıcı yetkisi owner, group, all mu nasıl öğreneceğiz?
        //Sorun 2: kullanıcı bilgisini nasıl alacağız?
        //Burada contact:select:owner durumu var
        //predicates.add(criteriaBuilder.equal(from.get(Contact_.owner), identity.getLoginName()));
        //Burada da contact:select:group durumu var
        if( ownerFilter != null ){
            predicates.add(from.get(FinanceAccount_.owner).in(ownerFilter));
        }
        

        //Oluşan filtreleri sorgumuza ekliyoruz
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // Öncelikle default sıralama verelim eğer kullanıcı tarafından tanımlı sıralama var ise onu setleyelim
        if (queryDefinition.getSorters().isEmpty()) {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(FinanceAccount_.name)));
        } else {
            criteriaQuery.orderBy(decorateSorts(queryDefinition.getSorters(), criteriaBuilder, from));
        }

        //Haydi bakalım sonuçları alalım
        TypedQuery<FinanceAccount> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(queryDefinition.getResultLimit());
        List<FinanceAccount> resultList = typedQuery.getResultList();

        return resultList;
    }

    
    @Override
    public List<FinanceAccount> lookupQuery(String searchText) {
        return lookupQuery(searchText, null, "");
    }
    
    public List<FinanceAccount> lookupQuery(String searchText, String type) {
        return lookupQuery(searchText, type, "");
    }
    
    
    public List<FinanceAccount> lookupQuery(String searchText, String type, String roles ) {
        List<String> rls = Collections.emptyList();
        if( !Strings.isNullOrEmpty(roles)){
            rls = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(roles);
        } 
        
        return lookupQuery(searchText, type, rls);
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
    public List<FinanceAccount> lookupQuery(String searchText, String type, List<String> roles ) {

        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye PersonViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<FinanceAccount> criteriaQuery = criteriaBuilder.createQuery(FinanceAccount.class);

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
        
        Root<FinanceAccount> from = criteriaQuery.from(FinanceAccount.class);
        
        //Sonuç filtremiz
        //buildVieModelSelect(criteriaQuery, from);

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();

        buildSearchTextControl(searchText, criteriaBuilder, predicates, from);
        
        /* FIXME: Burası nasıl olmalı
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
        }*/
     
        /*
        Hibernate bug'ı yüzünden isim kullanıyor ve as ile arama yapıyoruz. Converter annotation'ı ile derdi var.
        https://hibernate.atlassian.net/browse/HHH-10464
        */
        if( !roles.isEmpty()){
            roles.forEach(rs -> predicates.add(criteriaBuilder.like(from.get("accountRoles").as(String.class), "%"+ rs +"%")));
        }
        
        //Oluşan filtreleri sorgumuza ekliyoruz
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        
        criteriaQuery.orderBy(criteriaBuilder.asc(from.get(FinanceAccount_.name)));
        

        //Haydi bakalım sonuçları alalım
        TypedQuery<FinanceAccount> typedQuery = entityManager().createQuery(criteriaQuery);
        typedQuery.setMaxResults(100);
        List<FinanceAccount> resultList = typedQuery.getResultList();

        return resultList;
    }
    
    @Override
    public List<FinanceAccount> suggestion(String searchText) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void buildVieModelSelect(CriteriaQuery<FinanceAccountViewModel> criteriaQuery, Root<? extends FinanceAccount> from) {
        criteriaQuery.multiselect(
                from.get(FinanceAccount_.id),
                from.get(FinanceAccount_.code),
                from.get(FinanceAccount_.name)
                //from.get(FinanceAccount_.info)
                //from.type(),
                
        );
    }

    private void buildSearchTextControl(String searchText, CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Root<? extends FinanceAccount> from) {
        if (!Strings.isNullOrEmpty(searchText)) {
            predicates.add(criteriaBuilder.or(criteriaBuilder.like(from.get(FinanceAccount_.code), "%" + searchText + "%"),
                    criteriaBuilder.like(from.get(FinanceAccount_.name), "%" + searchText + "%")));
        }
    }

    public List<String> getOwnerFilter() {
        return ownerFilter;
    }

    public void setOwnerFilter(List<String> ownerFilter) {
        this.ownerFilter = ownerFilter;
    }
    
}
