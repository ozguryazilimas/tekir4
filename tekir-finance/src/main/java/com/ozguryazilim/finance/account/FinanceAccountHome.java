/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account;

import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@FormEdit( feature = FinanceAccountFeature.class )
public class FinanceAccountHome extends FormBase<FinanceAccount, Long> {

    @Inject
    private FinanceAccountRepository repository;

    @Override
    public FinanceAccountRepository getRepository() {
        return this.repository;
    }
}
