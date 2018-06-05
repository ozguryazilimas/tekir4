package com.ozguryazilim.tekir.lead;

import com.ozguryazilim.telve.messages.Messages;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.Lead;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.voucher.VoucherOwnerChange;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.group.VoucherGroupTxnService;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;

@Feeder
public class LeadFeeder extends AbstractFeeder<Lead> {

	@Inject
	private Identity identity;
	
	@Inject
	private VoucherGroupTxnService voucherGroupTxnService;

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = LeadFeature.class) @After VoucherStateChange event) {

		if (event.getPayload() instanceof Lead) {
			Lead entity = (Lead) event.getPayload();

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
					entity.getTopic(), getMessage(event), prepareMentionList(entity));
		}
	}

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = LeadFeature.class) @After VoucherOwnerChange event) {

		if (event.getPayload() instanceof Lead) {
			Lead entity = (Lead) event.getPayload();

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
					entity.getTopic(), getMessage(event), prepareMentionList(entity));
		}
	}
	

	public void feed(
			@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = Lead.class) @After EntityChangeEvent event) {
        Lead entity = (Lead) event.getEntity();

        FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

		if (event.getAction() != EntityChangeAction.DELETE) {
			
			if( entity.getGroup()!=null){
				voucherGroupTxnService.saveFeature(voucherPointer, entity.getGroup(), entity.getOwner(), entity.getTopic(),
						entity.getDate(), entity.getState());
			}
		} else if (event.getAction() == EntityChangeAction.DELETE) {
            if (entity.getGroup() != null) {
                voucherGroupTxnService.deleteFeature(voucherPointer, entity.getGroup());
            }
        }

    }

	private List<FeaturePointer> prepareMentionList(Lead entity) {

		List<FeaturePointer> mentions = new ArrayList<>();

		mentions.add(FeatureUtils.getFeaturePointer(entity));

		if (entity.getGroup() != null && entity.getGroup().isPersisted()) {
			mentions.add(FeatureUtils.getVoucherGroupPointer(entity));
		}

		return mentions;
	}

	protected String getMessage(VoucherStateChange event) {
        String reason = event.getPayload().getStateReason();
        if (reason == null) {
            reason = Messages.getMessage("feed.messages.NullReason");
        }
		switch (event.getAction().getName()) {
		case "CREATE":
			return "feeder.messages.LeadFeeder.CREATE$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo();
		case "publish":
			return "feeder.messages.LeadFeeder.PUBLISH$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo();
		case "won":
			return "feeder.messages.LeadFeeder.WON$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo();
		case "loss":
			return "feeder.messages.LeadFeeder.LOST$%&" + event.getPayload().getVoucherNo() + "$%&"
					+ reason;
		case "cancel":
			return "feeder.messages.LeadFeeder.CANCEL$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo() + "$%&" + reason;
        case "revise":
            return "feeder.messages.LeadFeeder.REVISE$%&" + identity.getUserName() + "$%&"
                + event.getPayload().getVoucherNo() + "$%&" + reason;
        default:
            return "feeder.messages.LeadFeeder.DEFAULT$%&" + identity.getUserName() + "$%&"
                + event.getPayload().getVoucherNo();
		}
	}

	protected String getMessage(VoucherOwnerChange event) {
		return "feeder.messages.LeadFeeder.OWNERCHANGE$%&" + identity.getUserName() + "$%&"
				+ event.getPayload().getVoucherNo() + "$%&" + event.getFrom() + "$%&" + event.getTo();
	}
}
