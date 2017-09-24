/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.paymentPlan;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;
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
@AutoCode(caption = "module.caption.PaymentPlan", size = 3, serial = "PP")
public class PaymentPlanHome extends ParamBase<PaymentPlan, Long>{

    @Inject
    private PaymentPlanRepository repository;
    
    @Inject
    private AutoCodeService codeService;
    
    @Override
    protected PaymentPlan getNewEntity() {
        
        PaymentPlan result = new PaymentPlan();
        result.setCode(codeService.getNewSerialNumber(PaymentPlanHome.class.getSimpleName()));
        
        return result;
    }
    
    @Override
    protected RepositoryBase<PaymentPlan, ?> getRepository() {
        return repository;
    }
    
}
