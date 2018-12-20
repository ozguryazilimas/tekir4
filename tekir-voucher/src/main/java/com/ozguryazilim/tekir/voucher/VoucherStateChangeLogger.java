package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.telve.audit.AuditLogger;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.qualifiers.After;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

/**
 * OwnerChange eventlerini dinleyerek bunları AuditLog'a gönderir.
 * 
 * @author Ceyhun Onur
 */
@ApplicationScoped
public class VoucherStateChangeLogger extends AbstractFeeder<Object>{

    private static final String LOG_CATEGORY = "STATE_CHANGE";
    
    @Inject
    private Identity identity;
    
    @Inject
    private AuditLogger auditLogger;
    
    public void feed(@Observes(during = TransactionPhase.AFTER_SUCCESS) @After VoucherStateChange event) {   	
    
        auditLogger.actionLog( event.getPayload().getClass().getSimpleName(), 
                               event.getPayload().getId(), 
                               event.getPayload().getVoucherNo(), 
                               LOG_CATEGORY,
                               event.getAction().getName(), 
                               identity.getLoginName(), 
                               event.getFrom().getName() + " -> " + event.getTo().getName());
        
    }
}
