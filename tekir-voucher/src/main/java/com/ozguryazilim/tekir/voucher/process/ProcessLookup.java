/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.process;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Process;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.Process_;
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
@Lookup(dialogPage = VoucherPages.Process.ProcessLookup.class)
public class ProcessLookup extends LookupTableControllerBase<Process, Process> {

    @Inject
    private ProcessRepository repository;
    
    @Override
    protected void buildModel(LookupTableModel<Process> model) {
        model.addColumn("processNo", "voucher.label.Process");
        model.addColumn("topic", "general.label.Topic");
    }

    
    @Override
    public void populateData() {
        String accountId = getModel().getProfileProperties().get("A");
        String type = getModel().getProfileProperties().get("T");
        
        ProcessType pt = ProcessType.OTHER;
        
        if( !Strings.isNullOrEmpty(type)){
            pt = ProcessType.valueOf(type);
        }
        
        if( !Strings.isNullOrEmpty(accountId)){
            Long id = Long.parseLong(accountId);
            getModel().setData(repository.lookupQuery(getModel().getSearchText(), id, pt));
        } else {
            getModel().setData(repository.lookupQuery(getModel().getSearchText(), pt));
        }
                
        //Şimdide Repository'den sorgumuz yapıp datayı dolduruyoruz
        
    }
    
    @Override
    protected RepositoryBase<Process, Process> getRepository() {
        return repository;
    }

    @Override
    public String getCaptionFieldName() {
        return Process_.processNo.getName();
    }
    
}
