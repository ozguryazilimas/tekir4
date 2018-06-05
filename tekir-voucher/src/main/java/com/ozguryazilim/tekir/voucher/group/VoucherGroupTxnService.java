/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.group;

import java.io.Serializable;

import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroupTxn;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.telve.entities.FeaturePointer;

import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
* VoucherGroup Txn işlemleri için Servis Sınıfı.
*
* @author oktay
*/
@Named
@RequestScoped
public class VoucherGroupTxnService implements Serializable{
	
    @Inject
    private VoucherGroupTxnRepository repository;
    
    @Transactional
    public void saveFeature( FeaturePointer feature, VoucherGroup group, String owner, String topic,
    		Date date,  VoucherState state ){
        	VoucherGroupTxn txn= repository.findOptionalByFeatureAndGroup(feature, group);
        
            if( txn == null ){
                txn = new VoucherGroupTxn();
            }
            
            txn.setGroup(group);
            txn.setFeature(feature);
            txn.setTopic(topic);
            txn.setDate(date);
            txn.setOwner(owner);
            txn.setState(state);
            
            repository.save(txn);
    }

    public List<VoucherGroupTxn> getGroupVouchers(VoucherGroup voucherGroup ){
        return repository.findByGroupId(voucherGroup);
    }

    @Transactional
    public void deleteFeature(FeaturePointer feature) {
        repository.deleteByFeature(feature);
    }

}
