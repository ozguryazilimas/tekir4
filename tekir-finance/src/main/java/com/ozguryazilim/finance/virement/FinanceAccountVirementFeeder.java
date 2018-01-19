/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.virement;

import com.ozguryazilim.finance.account.FinanceAccountFeature;
import com.ozguryazilim.tekir.entities.FinanceAccountVirement;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.voucher.VoucherOwnerChange;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.tekir.voucher.utils.FeederUtils;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.qualifiers.After;
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
public class FinanceAccountVirementFeeder extends AbstractFeeder<FinanceAccountVirement> {

	@Inject
	private Identity identity;

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = FinanceAccountVirementFeature.class) @After VoucherStateChange event) {

		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		if (event.getPayload() instanceof FinanceAccountVirement) {
			FinanceAccountVirement entity = (FinanceAccountVirement) event.getPayload();

			List<FeaturePointer> mentions = prepareMentionList(entity);

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getInfo(),
					getMessage(event), mentions);

		}
	}

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = FinanceAccountVirementFeature.class) @After VoucherOwnerChange event) {

		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		if (event.getPayload() instanceof FinanceAccountVirement) {
			FinanceAccountVirement entity = (FinanceAccountVirement) event.getPayload();

			List<FeaturePointer> mentions = prepareMentionList(entity);

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getInfo(),
					FeederUtils.getEventMessage(event), mentions);

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
		switch (event.getAction().getName()) {
		case "CREATE":
			return "feeder.messages.FinanceAccountVirementFeeder.CREATE$%&" + identity.getUserName() + "$%&" + event.getPayload().getVoucherNo();
		case "publish":
			return "feeder.messages.FinanceAccountVirementFeeder.PUBLISH$%&" + identity.getUserName() + "$%&" + event.getPayload().getVoucherNo();
        case "reopen":
            return "feeder.messages.FinanceAccountVirementFeeder.REOPEN$%&" + identity.getUserName() + "$%&" + event.getPayload().getVoucherNo() + "$%&" + event.getPayload().getStateReason();
        default:
            return "feeder.messages.FinanceAccountVirementFeeder.DEFAULT$%&" + identity.getUserName() + "$%&" + event.getPayload().getVoucherNo();
		}
	}

	private List<FeaturePointer> prepareMentionList(FinanceAccountVirement entity) {
		List<FeaturePointer> mentions = new ArrayList<>();

		FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
		FeaturePointer fromContactPointer = FeatureUtils.getFeaturePointer(FinanceAccountFeature.class,
				entity.getFromAccount().getName(), entity.getFromAccount().getId());
		FeaturePointer toContactPointer = FeatureUtils.getFeaturePointer(FinanceAccountFeature.class,
				entity.getToAccount().getName(), entity.getToAccount().getId());

		if (entity.getGroup() != null && entity.getGroup().isPersisted()) {
			FeaturePointer groupPointer = FeatureUtils.getVoucherGroupPointer(entity);
			mentions.add(groupPointer);
		}

		mentions.add(voucherPointer);
		mentions.add(fromContactPointer);
		mentions.add(toContactPointer);

		return mentions;
	}

}
