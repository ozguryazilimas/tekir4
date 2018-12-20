package com.ozguryazilim.tekir.voucher.process;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.activity.ActivityMentionEvent;
import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.Contact;
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.ProcessStatus;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.sequence.SequenceManager;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
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
    private AccountTxnRepository accountTxnRepository;

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
    
    /**
     * Verilen FeaturePointer için bir Process varsa onun FeaturePointer'ını döndürür.
     * @param featurePointer
     * @return 
     */
    public Process getProcessForFeaturePointer( FeaturePointer featurePointer ){
        
        AccountTxn accountTxn = accountTxnRepository.findOptionalByFeature(featurePointer);
        if( accountTxn != null && !Strings.isNullOrEmpty(accountTxn.getProcessId())){
            return findByProcessNo(accountTxn.getProcessId());
        }
        
        return null;
    }
    
    public void activityListener(@Observes(during = TransactionPhase.IN_PROGRESS) ActivityMentionEvent event){
        //Eğer activity'nin regarding'i doluysa
        if( event.getActivity().getRegarding() != null ){
            //Bak bakalım Process var mı?
             Process p = getProcessForFeaturePointer(event.getActivity().getRegarding());

            if( p != null ){
                FeaturePointer fp = new FeaturePointer();
                fp.setBusinessKey(p.getProcessNo());
                fp.setPrimaryKey(p.getId());
                fp.setFeature(ProcessFeature.class.getSimpleName());
                event.addMention(fp);
            }
        }
    }
    
}
