package com.ozguryazilim.tekir.hr.credit;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.entities.EmployeeCreditNote;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.entities.VoucherBase;
import com.ozguryazilim.tekir.voucher.VoucherOwnerChange;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.tekir.voucher.utils.FeederUtils;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;

/**
 *
 * @author oktay
 */
@Feeder
public class EmployeeCreditNoteFeeder extends AbstractFeeder<EmployeeCreditNote> {

    @Inject
    private Identity identity;

    public void feed(EmployeeCreditNote entity) {

        List<FeaturePointer> mentions = new ArrayList<>();

        FeaturePointer emp = new FeaturePointer();
        emp.setBusinessKey(entity.getEmployee().getName());
        emp.setPrimaryKey(entity.getEmployee().getId());
        emp.setFeature(FeatureRegistery.getFeatureClass(entity.getEmployee().getClass()).getSimpleName());
        mentions.add(emp);

        FeaturePointer fp = new FeaturePointer();
        fp.setBusinessKey(entity.getInfo());
        fp.setPrimaryKey(entity.getId());
        fp.setFeature(EmployeeCreditNote.class.getSimpleName());
        mentions.add(fp);
    }

    private List<FeaturePointer> prepareMentionList(EmployeeCreditNote entity) {

        List<FeaturePointer> mentions = new ArrayList<>();
        FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
        FeaturePointer employeePointer = FeatureUtils.getAccountFeaturePointer(entity.getEmployee());

        if (entity.getGroup() != null && entity.getGroup().isPersisted()) {
            FeaturePointer groupPointer = FeatureUtils.getVoucherGroupPointer(entity);
            mentions.add(groupPointer);
        }

        mentions.add(voucherPointer);
        mentions.add(employeePointer);

        return mentions;

    }

    public void feed(@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = EmployeeCreditNoteFeature.class) @After VoucherStateChange event) {

        if (event.getPayload() instanceof EmployeeCreditNote) {

            EmployeeCreditNote entity = (EmployeeCreditNote) event.getPayload();

            List<FeaturePointer> mentions = prepareMentionList(entity);

            sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
                    entity.getTopic(), getMessage(event), mentions);

        }
    }

    public void feed(@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = EmployeeCreditNoteFeature.class) @After VoucherOwnerChange event) {

        // FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
        if (event.getPayload() instanceof EmployeeCreditNote) {

            EmployeeCreditNote entity = (EmployeeCreditNote) event.getPayload();

            List<FeaturePointer> mentions = prepareMentionList(entity);

            sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
                    entity.getTopic(), FeederUtils.getEventMessage(event), mentions);
        }
    }

    public void feed(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = EmployeeCreditNote.class) @After EntityChangeEvent event) {

        if (event.getAction() != EntityChangeAction.DELETE) {
            EmployeeCreditNote entity = (EmployeeCreditNote) event.getEntity();

            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

        }
    }

    protected String getMessage(VoucherStateChange event) {
        switch (event.getAction().getName()) {
            case "CREATE":
                return "feeder.messages.EmployeeCreditNoteFeeder.CREATE$%&" + identity.getUserName() + "$%&"
                        + event.getPayload().getVoucherNo();
            case "publish":
                return "feeder.messages.EmployeeCreditNoteFeeder.PUBLISH$%&" + identity.getUserName() + "$%&"
                        + event.getPayload().getVoucherNo();
            case "cancel":
                return "feeder.messages.EmployeeCreditNoteFeeder.CANCEL$%&" + identity.getUserName() + "$%&"
                        + event.getPayload().getVoucherNo();

        }

        switch (event.getTo().getName()) {
            case "OPEN":
                return "feeder.messages.EmployeeCreditNoteFeeder.CREATE$%&" + identity.getUserName() + "$%&"
                        + event.getPayload().getVoucherNo();
            case "COMPLETE":
                return "feeder.messages.EmployeeCreditNoteFeeder.COMPLETE$%&" + identity.getUserName() + "$%&"
                        + event.getPayload().getVoucherNo();
            case "CANCEL":
                return "feeder.messages.EmployeeCreditNoteFeeder.CANCEL$%&" + identity.getUserName() + "$%&"
                        + event.getPayload().getVoucherNo();
            case "REVISE":
                return "feeder.messages.EmployeeCreditNoteFeeder.REVISE$%&" + identity.getUserName() + "$%&"
                        + event.getPayload().getVoucherNo();
            default:
                return "feeder.messages.EmployeeCreditNoteFeeder.DEFAULT$%&" + identity.getUserName() + "$%&"
                        + event.getPayload().getVoucherNo();
        }   
    }
    
}
