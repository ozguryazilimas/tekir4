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
    
    public Process findByProcessNo( String processNo ){
        return repository.findAnyByProcessNo( processNo );
    }
    
    public void incProcessUsage( String processNo ){
        Process p = repository.findAnyByProcessNo( processNo );
        incProcessUsage(p);
    }
    
    public void decProcessUsage( String processNo ){
        Process p = repository.findAnyByProcessNo( processNo );
        decProcessUsage(p);
    }
    
    public void incProcessUsage( Process p){
                p.setCounter( p.getCounter() + 1 );
        repository.save(p);
    }
    
    public void decProcessUsage( Process p ){
        
        p.setCounter( p.getCounter() - 1 );
        //Eğer counter sayısı 0 ya da küçük ise processi kapatıyoruz.
        if( p.getCounter().compareTo(0) < 1){
            p.setStatus(ProcessStatus.CLOSE);
        }
        repository.save(p);
    }
}
