/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.paymentPlan;

import com.ozguryazilim.tekir.entities.PaymentPlan;
import com.ozguryazilim.tekir.entities.PaymentPlan_;
import com.ozguryazilim.telve.data.ParamRepositoryBase;
import javax.enterprise.context.Dependent;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class PaymentPlanRepository extends
        ParamRepositoryBase<PaymentPlan, PaymentPlanViewModel>
        implements
        CriteriaSupport<PaymentPlan> {

    @Override
    protected Class<PaymentPlanViewModel> getViewModelClass() {
        return PaymentPlanViewModel.class;
    }

    @Override
    protected SingularAttribute<? super PaymentPlan, Long> getIdAttribute() {
        return PaymentPlan_.id;
    }
}
