/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.voucher.VoucherOwnerChange;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.tekir.voucher.utils.FeederUtils;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Feeder
public class QuoteFeeder extends AbstractFeeder<Quote> {

	@Inject
	private Identity identity;

	@Inject
	private AccountTxnService accountTxnService;

	@Inject
	private ProcessService processService;

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = QuoteFeature.class) @After VoucherStateChange event) {

		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		if (event.getPayload() instanceof Quote) {

			Quote entity = (Quote) event.getPayload();

			List<FeaturePointer> mentions = prepareMentionList(entity);

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
					entity.getVoucherNo(), getMessage(event), mentions);

			// Process feed
			if ("OPEN".equals(event.getTo().getName()) && "DRAFT".equals(event.getFrom().getName())) {
				processService.incProcessUsage(entity.getProcess());
			}

			if (event.getTo().getType().equals(VoucherStateType.CLOSE)) {
				processService.decProcessUsage(entity.getProcess());
			}
		}
	}

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = QuoteFeature.class) @After VoucherOwnerChange event) {

		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		if (event.getPayload() instanceof Quote) {

			Quote entity = (Quote) event.getPayload();

			List<FeaturePointer> mentions = prepareMentionList(entity);

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
					entity.getVoucherNo(), FeederUtils.getEventMessage(event), mentions);
		}
	}

	public void feed(
			@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = Quote.class) @After EntityChangeEvent event) {

		if (event.getAction() != EntityChangeAction.DELETE) {
			Quote entity = (Quote) event.getEntity();

			FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

			accountTxnService.saveFeature(voucherPointer, entity.getAccount(), entity.getCode(), entity.getInfo(),
					Boolean.FALSE, Boolean.FALSE, entity.getCurrency(), entity.getTotal(), entity.getLocalAmount(),
					entity.getDate(), entity.getOwner(), entity.getProcess().getProcessNo(),
					entity.getState().toString(), entity.getStateReason());
		}

		// TODO: Delete edildiğinde de gidip txn'den silme yapılmalı.
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
			return "Quote created";
		case "CLOSE":
			return "Quote Won. Congrats!";
		case "LOST":
			return "Quote lost! " + event.getPayload().getStateReason();
		case "CANCELED":
			return "Quote canceled. " + event.getPayload().getStateReason();
		default:
			return "Quote created";
		}
	}

	private List<FeaturePointer> prepareMentionList(Quote entity) {
		List<FeaturePointer> mentions = new ArrayList<>();

		FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
		FeaturePointer contactPointer = FeatureUtils.getAccountFeaturePointer(entity);
		FeaturePointer processPointer = FeatureUtils.getProcessPointer(entity);

		if (entity.getGroup() != null && entity.getGroup().isPersisted()) {
			FeaturePointer groupPointer = FeatureUtils.getVoucherGroupPointer(entity);
			mentions.add(groupPointer);
		}

		mentions.add(processPointer);
		mentions.add(contactPointer);
		mentions.add(voucherPointer);

		return mentions;
	}
}
