package com.ozguryazilim.tekir.voucher.process.stakeholder;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.voucher.process.ProcessRepository;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.ProcessStakeholder;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureController;
import com.ozguryazilim.telve.feature.FeatureLink;
import com.ozguryazilim.telve.feature.FeatureUtils;
import java.util.List;
import org.apache.deltaspike.jpa.api.transaction.Transactional;


/**
 *
 * @author oyas
 */
@Named
@GroupedConversationScoped
public class StakeholderWidget implements Serializable{
    
    @Inject
    private ProcessRepository processRepository;
    @Inject
    private StakeholderRepository stakeholderRepository;
    @Inject
    private FeatureController featureController;
    
    private Process process;
    
    private ProcessStakeholder stakeholder;
    
    public void init( Process process ){
        this.process = process;
    }
    
    public void init( String processNo ){
        process = processRepository.findAnyByProcessNo(processNo);
    }
    
    public List<ProcessStakeholder> getStakeHolders(){
        return stakeholderRepository.findByProcess(process);
    }
    
    
    
    public void createStakeholder(){
        stakeholder = new ProcessStakeholder();
        stakeholder.setProcess(process);
    }
    
    @Transactional
    public void save(){
        stakeholderRepository.save(stakeholder);
    }
    
    @Transactional
    public void delete(ProcessStakeholder stakeholder){
        stakeholderRepository.remove(stakeholder);
    }
    
    public void edit(ProcessStakeholder stakeholder){
        this.stakeholder = stakeholder;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public ProcessStakeholder getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(ProcessStakeholder stakeholder) {
        this.stakeholder = stakeholder;
    }
    
    public FeaturePointer getFeaturePointer( Contact contact ){
        return FeatureUtils.getFeaturePointer(contact);
    }
    
    public FeatureLink getFeatureLink( Contact contact ){
        return  featureController.getFeatureLink(getFeaturePointer(contact));
    }
}
