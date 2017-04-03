/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.group;

import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.util.List;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 *
 * @author oyas
 */
@Dependent
@Repository
public abstract class VoucherGroupRepository extends RepositoryBase<VoucherGroup, VoucherGroup> implements CriteriaSupport<VoucherGroup>{
    
    @Override
    public List<VoucherGroup> lookupQuery(String searchText) {
        return criteria().getResultList();
    }
    
    public abstract VoucherGroup findAnyByGroupNo( String groupNo );
    
}
