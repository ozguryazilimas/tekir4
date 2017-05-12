package com.ozguryazilim.tekir.lead;

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
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.qualifiers.After;

@Feeder
public class LeadFeeder extends AbstractFeeder<Lead> {

	@Inject
	private Identity identity;

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = LeadFeature.class) @After VoucherStateChange event) {

		if (event.getPayload() instanceof Lead) {
			Lead entity = (Lead) event.getPayload();

			sendFeed(entity.getState().getName(), entity.getClass().getSimpleName(), identity.getLoginName(),
					entity.getTopic(), getMessage(event), prepareMentionList(entity));
		}
	}

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = LeadFeature.class) @After VoucherOwnerChange event) {

		if (event.getPayload() instanceof Lead) {
			Lead entity = (Lead) event.getPayload();

			sendFeed(entity.getState().getName(), entity.getClass().getSimpleName(), identity.getLoginName(),
					entity.getTopic(), getMessage(event), prepareMentionList(entity));
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
		switch (event.getAction().getName()) {
		case "CREATE":
			return "feeder.messages.LeadFeeder.CREATE$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo();
		case "publish":
			return "Lead published";
		case "won":
			return "feeder.messages.LeadFeeder.WON$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo();
		case "loss":
			return "feeder.messages.LeadFeeder.LOST$%&" + event.getPayload().getVoucherNo() + "$%&"
					+ event.getPayload().getStateReason();
		case "cancel":
			return "feeder.messages.LeadFeeder.CANCEL$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo() + "$%&" + event.getPayload().getStateReason();
		}

		switch (event.getTo().getName()) {
		case "OPEN":
			return "Lead created";
		case "CLOSE":
			return "Lead Won. Congrats!";
		case "LOST":
			return "Lead lost! " + event.getPayload().getStateReason();
		case "CANCELED":
			return "Lead canceled. " + event.getPayload().getStateReason();
		default:
			return "Lead created";
		}
	}

	protected String getMessage(VoucherOwnerChange event) {
		return "feeder.messages.LeadFeeder.OWNERCHANGE$%&" + identity.getUserName() + "$%&"
				+ event.getPayload().getVoucherNo() + "$%&" + event.getFrom() + "$%&" + event.getTo() + "$%&"
				+ event.getPayload().getStateReason();
	}
}
