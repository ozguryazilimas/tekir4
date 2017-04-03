/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.group;

import com.ozguryazilim.tekir.entities.Contact;
import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroupStatus;
import com.ozguryazilim.telve.sequence.SequenceManager;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author oyas
 */
@Dependent
@Named
public class VoucherGroupService implements Serializable{
    
    @Inject
    private VoucherGroupRepository repository;

    @Inject
    private SequenceManager sequenceManager;
    
    @Transactional
    public VoucherGroup saveVoucherGroup( VoucherGroup voucherGroup){
        //TODO: Prefix'i configden alsak iyi olur
    	voucherGroup.setGroupNo(sequenceManager.getNewSerialNumber("VG", 6));
        
    	voucherGroup = repository.save(voucherGroup);
        
        return voucherGroup;
    }
    
    public VoucherGroup findByVoucherGroupNo( String processNo ){
        return repository.findAnyByGroupNo( processNo );
    }
    
}
