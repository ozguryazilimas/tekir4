package com.ozguryazilim.finance.config;

import com.ozguryazilim.finance.account.AccountRoleRegitery;
import com.ozguryazilim.telve.api.module.TelveModule;
import javax.annotation.PostConstruct;

/**
 *
 * @author oyas
 */
@TelveModule
public class TekirFinanceModule {
    
    @PostConstruct
    public void init(){
        AccountRoleRegitery.register("CASH", false);
        AccountRoleRegitery.register("BANK", false);
        AccountRoleRegitery.register("CREDIT_CARD", false);
        AccountRoleRegitery.register("PAYMENT", true);
        AccountRoleRegitery.register("PAYMENT_RECEIVED", true);
        AccountRoleRegitery.register("MULTI_CURRENCY", true);
    }
}
