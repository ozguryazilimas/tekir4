package com.ozguryazilim.tekir.contact.relation;

import com.google.common.base.Joiner;
import org.apache.deltaspike.data.api.Repository;
import javax.enterprise.context.Dependent;
import com.ozguryazilim.telve.data.ParamRepositoryBase;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;
import com.ozguryazilim.tekir.entities.ContactRelation;
import com.ozguryazilim.tekir.entities.ContactRelation_;
import com.ozguryazilim.telve.entities.ParamEntityBase_;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.metamodel.SingularAttribute;

/**
 * Repository Class
 *
 * @author
 */
@Repository
@Dependent
public abstract class ContactRelationRepository
        extends
        ParamRepositoryBase<ContactRelation, ContactRelationViewModel>
        implements
        CriteriaSupport<ContactRelation> {

    @Override
    protected Class<ContactRelationViewModel> getViewModelClass() {
        return ContactRelationViewModel.class;
    }

    @Override
    protected SingularAttribute<? super ContactRelation, Long> getIdAttribute() {
        return ContactRelation_.id;
    }

    /**
     * Geriye Verilen contactRole listesine uyan Relation tanımlarını döndürür.
     * 
     * Tersine olan ilişkiler için revers değeri true gelecek.
     * 
     * @param roles
     * @return 
     */
    public List<ContactRelationViewModel> findByContactRole(List<String> roles) {

        //Acaba cachelemeye değer mi?
        List<ContactRelation> relations = criteria()
                .eq(ParamEntityBase_.active, true)
                .getResultList();

        //Önce Normalleri buluyoruz
        List<ContactRelationViewModel> result = relations.stream()
                .filter(r -> roles.containsAll(r.getSourceRoles()))
                .map( r -> {
                    ContactRelationViewModel v = new ContactRelationViewModel();
                    v.setId(r.getId());
                    v.setName(r.getVectorName());
                    v.setCode(r.getCode());
                    v.setWeigth(r.getWeigth());
                    
                    v.setTargetRoles(Joiner.on(',').join(r.getTargetRoles()));
                    return v;
                })
                .collect(Collectors.toList());
                
        //Şimdi Tersleri buluyoruz
        List<ContactRelationViewModel> reverses = relations.stream()
                .filter(r -> roles.containsAll(r.getTargetRoles()))
                .map( r -> {
                    ContactRelationViewModel v = new ContactRelationViewModel();
                    v.setId(r.getId());
                    v.setName(r.getReversName());
                    v.setCode(r.getCode());
                    v.setWeigth(r.getWeigth());
                    v.setRevers(Boolean.TRUE);
                    v.setTargetRoles(Joiner.on(',').join(r.getSourceRoles()));
                    return v;
                })
                .collect(Collectors.toList());
        
        //Şidi zaten ekli olmayanları ekliyoruz.
        reverses.stream()
                .forEach( r-> {
                    if( !result.contains(r) ){
                        result.add(r);
                    }
        });
        
        //Şimdi de ağırlığa göre sıralıyoruz.
        Collections.sort(result, (t, t1) -> {
            return t.getWeigth().compareTo(t1.getWeigth());
        });
        
        return result;
    }

}
