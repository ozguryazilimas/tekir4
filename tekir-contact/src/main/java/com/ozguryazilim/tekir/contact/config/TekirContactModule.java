/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        ContactRoleRegistery.register("CONTACT", false);
        ContactRoleRegistery.register("PERSON", false);
        ContactRoleRegistery.register("CORPORATION", false);
        ContactRoleRegistery.register("ACCOUNT", false);
        ContactRoleRegistery.register("CUSTOMER", true);
        ContactRoleRegistery.register("VENDOR", true);
        ContactRoleRegistery.register("PARTNER", true);
        ContactRoleRegistery.register("COMPETITOR", true);
        //ContactRoleRegistery.register("LEAD");
        //ContactRoleRegistery.register("EMPLOYEE");
    }
}
