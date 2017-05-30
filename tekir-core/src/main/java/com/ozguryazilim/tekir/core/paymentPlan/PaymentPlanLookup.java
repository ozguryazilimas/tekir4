/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.paymentPlan;

import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.tekir.entities.PaymentPlan;
import com.ozguryazilim.tekir.entities.PaymentPlan_;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Lookup(dialogPage = CorePages.Core.PaymentPlanLookup.class)
public class PaymentPlanLookup extends
			LookupTableControllerBase<PaymentPlan, PaymentPlanViewModel> {

    @Inject
    private PaymentPlanRepository repository;
    
    @Override
    protected void buildModel(LookupTableModel<PaymentPlanViewModel> model) {
        model.addColumn("code", "general.label.Code");
        model.addColumn("name", "general.label.Name");
    }

    @Override
    protected RepositoryBase<PaymentPlan, PaymentPlanViewModel> getRepository() {
        return repository;
    }

    @Override
    public String getCaptionFieldName() {
        return PaymentPlan_.name.getName();
    }
    
}
