package com.ozguryazilim.tekir.hr.salarynote;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.entities.SalaryNote;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.hr.salarynote.SalaryNoteFeature;
import com.ozguryazilim.tekir.voucher.VoucherOwnerChange;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
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
 * @author oktay
 *
 */
public class SalaryNoteFeeder extends AbstractFeeder<SalaryNote>{

	@Inject
	private Identity identity;

	@Inject
	private AccountTxnService accountTxnService;

	@Inject
	private ProcessService processService;

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = SalaryNoteFeature.class) @After VoucherStateChange event) {

		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		if (event.getPayload() instanceof SalaryNote) {

			SalaryNote entity = (SalaryNote) event.getPayload();

			List<FeaturePointer> mentions = prepareMentionList(entity);

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
					entity.getVoucherNo(), getMessage(event), mentions);

		}
	}

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = SalaryNoteFeature.class) @After VoucherOwnerChange event) {

		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		if (event.getPayload() instanceof SalaryNote) {

			SalaryNote entity = (SalaryNote) event.getPayload();

			List<FeaturePointer> mentions = prepareMentionList(entity);

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
					entity.getVoucherNo(), FeederUtils.getEventMessage(event), mentions);
		}
	}

	public void feed(
			@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = SalaryNote.class) @After EntityChangeEvent event) {

		if (event.getAction() != EntityChangeAction.DELETE) {
			SalaryNote entity = (SalaryNote) event.getEntity();

			FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
		}
	}

	/**
	 * Geriye event bilgilerine bakarak feed body mesajını hazırlayıp döndürür.
	 *
	 * TODO: i18n problemi ve action / state karşılaştırması + const kullanımına
	 * ihtiyaç var.
	 *
	 * @param event
	 * @return
	 */
	protected String getMessage(VoucherStateChange event) {
		switch (event.getTo().getName()) {
		case "OPEN":
			return "Salary note created";
		case "CLOSE":
			return "Salary note approved";
		case "LOST":
			return "Salary note not approved! " + event.getPayload().getStateReason();
		case "CANCELED":
			return "Salary note canceled. " + event.getPayload().getStateReason();
		default:
			return "Salary note created";
		}
	}

	private List<FeaturePointer> prepareMentionList(SalaryNote entity) {
		List<FeaturePointer> mentions = new ArrayList<>();

		FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
		FeaturePointer contactPointer = FeatureUtils.getFeaturePointer(entity);

		if (entity.getGroup() != null && entity.getGroup().isPersisted()) {
			FeaturePointer groupPointer = FeatureUtils.getVoucherGroupPointer(entity);
			mentions.add(groupPointer);
		}

		mentions.add(contactPointer);
		mentions.add(voucherPointer);

		return mentions;
	}
}

