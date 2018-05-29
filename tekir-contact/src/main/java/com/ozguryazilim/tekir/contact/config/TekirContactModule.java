package com.ozguryazilim.tekir.contact.config;

import com.ozguryazilim.tekir.contact.ContactRoleRegistery;
import com.ozguryazilim.telve.api.module.TelveModule;
import javax.annotation.PostConstruct;

/**
 * Tekir Contact Module definition
 * @author Hakan Uygun
 */
@TelveModule
public class TekirContactModule {
    
    @PostConstruct
    public void init(){
        ContactRoleRegistery.register("CONTACT", false, false);
        ContactRoleRegistery.register("PERSON", false, true);
        ContactRoleRegistery.register("CORPORATION", false, true);
        ContactRoleRegistery.register("ACCOUNT", false, true);
        ContactRoleRegistery.register("CUSTOMER", true, true);
        ContactRoleRegistery.register("VENDOR", true, true);
        ContactRoleRegistery.register("PARTNER", true, true);
        ContactRoleRegistery.register("COMPETITOR", true, true);
        ContactRoleRegistery.register("RESELLER", true, true);
        ContactRoleRegistery.register("INTERNATIONAL", false, true);
        //ContactRoleRegistery.register("LEAD");
        //ContactRoleRegistery.register("EMPLOYEE");
    }
}
