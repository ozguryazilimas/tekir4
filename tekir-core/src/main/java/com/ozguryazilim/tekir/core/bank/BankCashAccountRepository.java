/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.bank;

import com.ozguryazilim.tekir.entities.BankCashAccount;
import com.ozguryazilim.tekir.entities.BankCashAccount_;
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
public abstract class BankCashAccountRepository extends
        ParamRepositoryBase<BankCashAccount, BankCashAccountViewModel>
        implements
        CriteriaSupport<BankCashAccount> {

    @Override
    protected Class<BankCashAccountViewModel> getViewModelClass() {
        return BankCashAccountViewModel.class;
    }

    @Override
    protected SingularAttribute<? super BankCashAccount, Long> getIdAttribute() {
        return BankCashAccount_.id;
    }
}
