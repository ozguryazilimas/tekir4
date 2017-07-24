/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.group;

import java.util.List;
import javax.inject.Inject;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.EntityBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureLinkController;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroupTxn;

/**
 * Voucher Group View Controller
 * @author oyas
 */
@FormEdit(feature=VoucherGroupFeature.class)
public class VoucherGroupHome extends FormBase<VoucherGroup, Long>{

	@Inject
	private VoucherGroupRepository repository;
	
	@Inject
	private VoucherGroupTxnRepository voucherGroupTxnRepository;

	@Inject
	private VoucherGroupService voucherGroupService;
	
	private List<VoucherGroupTxn> txnList;
	
	@Override
	protected RepositoryBase<VoucherGroup, ?> getRepository() {
		return repository;
	}	

    public boolean onAfterLoad() {
        setTxnList(null);
        refreshTxn();
        return super.onAfterLoad();
    }

    protected void refreshTxn() {
        txnList = voucherGroupTxnRepository.findByGroupId(getEntity());
  
    }

	public boolean showDelete() {
        return txnList.isEmpty();
    }

	public FeaturePointer getFeaturePointer() {
		FeaturePointer result = new FeaturePointer();
		result.setBusinessKey(getEntity().getTopic());
		result.setFeature(getFeatureClass().getSimpleName());
		result.setPrimaryKey(getEntity().getId());
		return result;
	}

	@Override
	protected VoucherGroup getNewEntity() {
		// TODO Auto-generated method stub
		VoucherGroup e = super.getNewEntity();
		e.setGroupNo(voucherGroupService.getNewSerialNumber());	
		
		return e;
	}

    public List<VoucherGroupTxn> getTxnList() {
        if (txnList == null) {
            refreshTxn();
        }
        return txnList;
    }

    public void setTxnList(List<VoucherGroupTxn> txnList) {
        this.txnList = txnList;
    }
}
