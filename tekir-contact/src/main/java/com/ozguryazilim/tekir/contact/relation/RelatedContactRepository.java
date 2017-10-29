/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.contact.relation;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.RelatedContact;
import com.ozguryazilim.tekir.entities.RelatedContact_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.Filter;
import java.util.ArrayList;
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
 * RelatedContact Repository
 * @author Hakan Uygun
 */
@Repository
@Dependent
public abstract class RelatedContactRepository extends RepositoryBase<RelatedContact, RelatedContactViewModel> implements CriteriaSupport<RelatedContact>{
    
    
    private Contact contact;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    @Override
    public List<RelatedContactViewModel> browseQuery(QueryDefinition queryDefinition) {
        List<Filter<RelatedContact, ?, ?>> filters = queryDefinition.getFilters();
        
        CriteriaBuilder criteriaBuilder = entityManager().getCriteriaBuilder();
        //Geriye AccidentAnalysisViewModel dönecek cq'yu ona göre oluşturuyoruz.
        CriteriaQuery<RelatedContactViewModel> criteriaQuery = criteriaBuilder.createQuery(RelatedContactViewModel.class);

        //From Tabii ki PersonWorkHistory
        Root<RelatedContact> from = criteriaQuery.from(RelatedContact.class);
        

        //Sonuç filtremiz
        criteriaQuery.multiselect(
                from.get(RelatedContact_.id),
                from.get(RelatedContact_.sourceContact),
                //from.get(RelatedContact_.sourceContact).get(Contact_.name),
                from.get(RelatedContact_.targetContact),
                //from.get(RelatedContact_.targetContact).get(Contact_.name),
                from.get(RelatedContact_.relation),
                //from.get(RelatedContact_.releation).get(ContactRelation_.name)
                from.get(RelatedContact_.info)
        );

        //Filtreleri ekleyelim.
        List<Predicate> predicates = new ArrayList<>();
        

        decorateFilters(filters, predicates, criteriaBuilder, from);

        //Hem source hem de target üzerinde olanlar gelsin.
        if (contact != null) {
            /*
            predicates.add(criteriaBuilder.or( 
                    criteriaBuilder.equal(from.get(RelatedContact_.sourceContact).get(Contact_.id), contact.getId()),
                    criteriaBuilder.equal(from.get(RelatedContact_.targetContact).get(Contact_.id), contact.getId())));
            */
            predicates.add(criteriaBuilder.equal(from.get(RelatedContact_.sourceContact).get(Contact_.id), contact.getId()));
        }
        
        //filtremize ekledik.
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));


        //Haydi bakalım sonuçları alalım
        TypedQuery<RelatedContactViewModel> typedQuery = entityManager().createQuery(criteriaQuery);
        List<RelatedContactViewModel> resultList = typedQuery.getResultList();

        return resultList;
    }
    
    public abstract List<RelatedContact> findBySourceContact( Contact contact);
    public abstract List<RelatedContact> findByTargetContact( Contact contact);
}
