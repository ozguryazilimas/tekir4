/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.telve.audit.AuditLogger;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.qualifiers.After;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * StateChange eventlerini dinleyerek bunları AuditLog'a gönderir.
 * 
 * @author Hakan Uygun
 */
@ApplicationScoped
public class VoucherStateChangeLogger {

    private static final String LOG_CATEGORY = "STATE_CHANGE";
    
    @Inject
    private Identity identity;
    
    @Inject
    private AuditLogger auditLogger;
    
    public void feed(@Observes @After VoucherStateChange event) {
    
        auditLogger.actionLog( event.getPayload().getClass().getSimpleName(), 
                               event.getPayload().getId(), 
                               event.getPayload().getVoucherNo(), 
                               LOG_CATEGORY,
                               event.getAction().getName(), 
                               identity.getLoginName(), 
                               event.getFrom().getName() + " -> " + event.getTo().getName());
        
    }
}
