/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.paymentPlan;

import com.ozguryazilim.tekir.entities.PaymentPlan;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.telve.forms.ParamEdit;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@ParamEdit
public class PaymentPlanHome extends ParamBase<PaymentPlan, Long>{

    @Inject
    private PaymentPlanRepository repository;
    
    @Override
    protected RepositoryBase<PaymentPlan, ?> getRepository() {
        return repository;
    }
    
}
