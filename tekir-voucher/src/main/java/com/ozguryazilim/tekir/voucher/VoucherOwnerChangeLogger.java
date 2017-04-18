/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.entities.VoucherProcessBase;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.audit.AuditLogger;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.entities.ViewModel;
import com.ozguryazilim.telve.qualifiers.After;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

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

        List<FeaturePointer> mentions = new ArrayList<>();
        VoucherBase entity = event.getPayload();

        FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

        if (entity instanceof VoucherProcessBase) {
            FeaturePointer contactPointer = FeatureUtils.getAccountFeaturePointer(((Voucher) entity).getAccount());
        

        if (entity.getGroup() != null && entity.getGroup().isPersisted()) {
            FeaturePointer groupPointer = FeatureUtils.getVoucherGroupPointer(entity);
            mentions.add(groupPointer);
        }

        mentions.add(voucherPointer);
        mentions.add(contactPointer);
        }

        sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getInfo(),
                getMessage(event), mentions);

        auditLogger.actionLog(event.getPayload().getClass().getSimpleName(),
                event.getPayload().getId(), event.getPayload().getVoucherNo(),
                "ACTION", LOG_CATEGORY,
                identity.getLoginName(),
                event.generateMessage());

    }
}
