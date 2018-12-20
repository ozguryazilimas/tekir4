package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.ViewModel;
import java.util.List;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 * VoucherBase'i miras alan modeller için repository taımları
 * 
 * @author Hakan Uygun
 */
public abstract class VoucherRepositoryBase<E extends VoucherBase, R extends ViewModel> 
        extends RepositoryBase<E, R> 
        implements CriteriaSupport<E>, VoucherRepository<E, R>{

    private List<String> ownerFilter;

    
    protected void buildOwnerFilter(List<Predicate> predicates, Root<? extends VoucherBase> from){
        if( ownerFilter != null ){
            predicates.add(from.get(VoucherBase_.owner).in(ownerFilter));
        }
    }
    
    @Override
    public List<String> getOwnerFilter() {
        return ownerFilter;
    }

    @Override
    public void setOwnerFilter(List<String> ownerFilter) {
        this.ownerFilter = ownerFilter;
    }
    
    
}
