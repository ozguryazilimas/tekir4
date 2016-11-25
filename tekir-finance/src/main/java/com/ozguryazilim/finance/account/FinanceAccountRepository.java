/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account;

import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.enterprise.context.Dependent;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.criteria.CriteriaSupport;

/**
 *
 * @author oyas
 */
@Repository
@Dependent
public abstract class FinanceAccountRepository extends
        RepositoryBase<FinanceAccount, FinanceAccountViewModel>
        implements
        CriteriaSupport<FinanceAccount> {

    
}
