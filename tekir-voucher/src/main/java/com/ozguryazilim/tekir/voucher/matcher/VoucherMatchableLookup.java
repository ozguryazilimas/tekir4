/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.matcher;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.VoucherMatchable;
import com.ozguryazilim.tekir.entities.VoucherMatchable_;
import com.ozguryazilim.tekir.voucher.config.VoucherPages;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Lookup(dialogPage = VoucherPages.Voucher.VoucherMatchableLookup.class)
public class VoucherMatchableLookup extends
        LookupTableControllerBase<VoucherMatchable, VoucherMatchable> {

    @Inject
    private VoucherMachableRepository repository;

    @Override
    protected void buildModel(LookupTableModel<VoucherMatchable> model) {
        model.addColumn("feature", "general.label.Code");
        model.addColumn("topic", "general.label.Topic");
    }

    @Override
    protected RepositoryBase<VoucherMatchable, VoucherMatchable> getRepository() {
        return repository;
    }

    @Override
    public String getCaptionFieldName() {
        return VoucherMatchable_.topic.getName();
    }

    @Override
    public void populateData() {
        String accountId = getModel().getProfileProperties().get("A");
        String featureName = getModel().getProfileProperties().get("F");
        String processNo = getModel().getProfileProperties().get("P");
        
        Long acc = null;
        
        if( !Strings.isNullOrEmpty(accountId)){
            acc = Long.parseLong(accountId);
        }
        
        if( acc == null ){
            getModel().setData(repository.findByFeatureName(featureName));
        } else {
            getModel().setData(repository.findByAccountIdAndFeature(acc, featureName));
        }
        
    }

    
}
