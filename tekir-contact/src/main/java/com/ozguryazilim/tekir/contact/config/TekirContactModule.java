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
        ContactRoleRegistery.register("CONTACT");
        ContactRoleRegistery.register("ACCOUNT");
        ContactRoleRegistery.register("CUSTOMER");
        ContactRoleRegistery.register("VENDOR");
        ContactRoleRegistery.register("PARTNER");
        ContactRoleRegistery.register("COMPETITOR");
        //ContactRoleRegistery.register("LEAD");
        //ContactRoleRegistery.register("EMPLOYEE");
    }
}
