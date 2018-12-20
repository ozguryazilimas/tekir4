package com.ozguryazilim.tekir.voucher.matcher;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.VoucherMatchable;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.util.List;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class VoucherMachableRepository extends RepositoryBase<VoucherMatchable, VoucherMatchable>{
    
    
    @Query("select c from VoucherMatchable c where c.account = ?1 and c.feature.feature = ?2 and c.status = 'OPEN' order by c.txnDate")
    public abstract List<VoucherMatchable> findByAccountAndFeatureName( Contact account, String featureName );
    
    @Query("select c from VoucherMatchable c where c.feature.feature = ?1 and c.status = 'OPEN' order by c.txnDate")
    public abstract List<VoucherMatchable> findByFeatureName( String featureName );
    
    @Query("select c from VoucherMatchable c where c.account.id = ?1 and c.feature.feature = ?2 and c.status = 'OPEN' order by c.txnDate")
    public abstract List<VoucherMatchable> findByAccountIdAndFeature( Long accountId, String featureName );
    
    @Query("select c from VoucherMatchable c where c.account = ?1 and c.feature.feature = ?2 and c.processNo = ?3 and c.status = 'OPEN' order by c.txnDate")
    public abstract List<VoucherMatchable> findByAccountAndFeature( Contact account, String featureName, String processNo );

    @Override
    public List<VoucherMatchable> lookupQuery(String searchText) {
        return super.lookupQuery(searchText); //To change body of generated methods, choose Tools | Templates.
    }
    
    public abstract VoucherMatchable findAnyByFeature( FeaturePointer feature);
}
