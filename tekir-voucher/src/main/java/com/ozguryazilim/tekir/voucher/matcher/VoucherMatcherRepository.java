/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.matcher;

import com.ozguryazilim.tekir.entities.VoucherMatchable;
import com.ozguryazilim.tekir.entities.VoucherMatcher;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.util.List;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class VoucherMatcherRepository extends RepositoryBase<VoucherMatcher, VoucherMatcher>{
    
    
    public abstract VoucherMatcher findAnyByMatchableAndFeature( VoucherMatchable matchable, FeaturePointer feature);
    
    public abstract List<VoucherMatcher> findByMatchable( VoucherMatchable matchable);
}
