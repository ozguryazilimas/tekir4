package com.ozguryazilim.tekir.hr.config;

import com.ozguryazilim.tekir.contact.ContactRoleRegistery;
import com.ozguryazilim.telve.api.module.TelveModule;
import javax.annotation.PostConstruct;

/**
 * Tekir HR Module tanımı
 * @author Hakan Uygun
 */
@TelveModule
public class TekirHRModule {
    
    @PostConstruct
    public void init(){
        ContactRoleRegistery.register("EMPLOYEE", false, false);
    }
    
}
