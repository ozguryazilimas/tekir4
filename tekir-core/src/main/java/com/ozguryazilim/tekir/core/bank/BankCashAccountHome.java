/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.bank;

import com.ozguryazilim.tekir.entities.BankCashAccount;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.telve.forms.ParamEdit;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@ParamEdit
public class BankCashAccountHome extends ParamBase<BankCashAccount, Long> {

    @Inject
    private BankCashAccountRepository repository;

    @Override
    public BankCashAccountRepository getRepository() {
        return this.repository;
    }
}
