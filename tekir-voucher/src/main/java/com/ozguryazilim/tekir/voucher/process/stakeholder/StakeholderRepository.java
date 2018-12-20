package com.ozguryazilim.tekir.voucher.process.stakeholder;

import com.ozguryazilim.tekir.entities.ProcessStakeholder;
import com.ozguryazilim.tekir.entities.Process;
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
public abstract class StakeholderRepository extends RepositoryBase<ProcessStakeholder, ProcessStakeholder> implements CriteriaSupport<ProcessStakeholder>{


    /**
     * Verilen process için stakeholder listesi döndürür.
     * @param process
     * @return 
     */
    public abstract List<ProcessStakeholder> findByProcess( Process process );
    
}
