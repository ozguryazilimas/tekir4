/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.voucher.utils.FeederUtils;
import com.ozguryazilim.telve.audit.AuditLogger;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.qualifiers.After;

/**
 * StateChange eventlerini dinleyerek bunları AuditLog'a gönderir.
 *
 * @author Hakan Uygun
 */
@ApplicationScoped
public class VoucherOwnerChangeLogger extends AbstractFeeder<VoucherBase> {

    private static final String LOG_CATEGORY = "OWNER_CHANGE";

    @Inject
    private Identity identity;

    @Inject
    private AuditLogger auditLogger;

    public void feed(@Observes(during = TransactionPhase.AFTER_SUCCESS) @After VoucherOwnerChange event) {

        auditLogger.actionLog(event.getPayload().getClass().getSimpleName(),
                event.getPayload().getId(), event.getPayload().getVoucherNo(),
                "ACTION", LOG_CATEGORY,
                identity.getLoginName(),
                FeederUtils.getEventMessage(event));

    }
}
