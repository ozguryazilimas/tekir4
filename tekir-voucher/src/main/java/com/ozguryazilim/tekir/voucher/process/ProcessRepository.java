/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.process;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Contact_;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.ProcessStatus;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.Process_;
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
public abstract class ProcessRepository extends RepositoryBase<Process, Process> implements CriteriaSupport<Process>{

    @Override
    public List<Process> lookupQuery(String searchText) {
        return criteria().getResultList();
    }
    
    
    public List<Process> lookupQuery(String searchText, Long accountId, ProcessType type ) {
        return criteria()
                .eq(Process_.type, type)
                .eq(Process_.status, ProcessStatus.OPEN)
                .join(Process_.account,
                    where(Contact.class)
                        .eq(Contact_.id, accountId)
                )
                .getResultList();
    }
    
    
    public List<Process> lookupQuery(String searchText, ProcessType type ) {
        return criteria()
                .eq(Process_.type, type)
                .eq(Process_.status, ProcessStatus.OPEN)
                .getResultList();
    }
    
    public abstract Process findAnyByProcessNo( String processNo );
    
}
