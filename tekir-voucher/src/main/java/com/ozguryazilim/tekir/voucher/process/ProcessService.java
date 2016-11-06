/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.process;

import com.ozguryazilim.tekir.entities.Contact;
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.ProcessStatus;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.telve.sequence.SequenceManager;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author oyas
 */
@Dependent
@Named
public class ProcessService implements Serializable{
    
    @Inject
    private ProcessRepository repository;

    @Inject
    private SequenceManager sequenceManager;
    
    @Transactional
    public Process createProcess( Contact account, String topic, ProcessType type ){
        
        Process result = new Process();
        
        result.setAccount(account);
        result.setTopic(topic);
        result.setType(type);
        result.setStatus(ProcessStatus.OPEN);
        
        //TODO: Prefix'i configden alsak iyi olur
        result.setProcessNo(sequenceManager.getNewSerialNumber("PS", 6));
        
        result = repository.save(result);
        
        return result;
    }
    
    
}
