/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.virement;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.entities.AccountVirement;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.voucher.VoucherOwnerChange;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.group.VoucherGroupTxnService;
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
public class AccountVirementFeeder extends AbstractFeeder<AccountVirement> {

	@Inject
	private Identity identity;
	
    @Inject
    private AccountTxnService accountTxnService;
	
	@Inject
	private VoucherGroupTxnService voucherGroupTxnService;

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = AccountVirementFeature.class) @After VoucherStateChange event) {

		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		if (event.getPayload() instanceof AccountVirement) {

			AccountVirement entity = (AccountVirement) event.getPayload();

			List<FeaturePointer> mentions = prepareMentionList(entity);

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
				entity.getTopic(), getMessage(event), mentions);

		}
	}

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = AccountVirementFeature.class) @After VoucherOwnerChange event) {

		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		if (event.getPayload() instanceof AccountVirement) {

			AccountVirement entity = (AccountVirement) event.getPayload();

			List<FeaturePointer> mentions = prepareMentionList(entity);

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
				entity.getTopic(), FeederUtils.getEventMessage(event), mentions);

		}
	}

    public void feed(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = AccountVirement.class) @After EntityChangeEvent event) {
        AccountVirement entity = (AccountVirement) event.getEntity();

        FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

        if( event.getAction() != EntityChangeAction.DELETE   ) {

            if( entity.getGroup()!=null){
    				voucherGroupTxnService.saveFeature(voucherPointer, entity.getGroup(), entity.getOwner(), entity.getTopic(),
    						entity.getDate(), entity.getState());
    		}
        }

        if (event.getAction() == EntityChangeAction.DELETE) {
            if (entity.getGroup() != null) {
                voucherGroupTxnService.deleteFeature(voucherPointer, entity.getGroup());
            }
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
			return "feeder.messages.AccountNoteFeeder.CREATE$%&" + identity.getUserName() + "$%&" + event.getPayload().getVoucherNo();
		case "publish":
			return "feeder.messages.AccountNoteFeeder.PUBLISH$%&" + identity.getUserName() + "$%&" + event.getPayload().getVoucherNo();
        case "reopen":
            return "feeder.messages.AccountNoteFeeder.REOPEN$%&" + identity.getUserName() + "$%&" + event.getPayload().getVoucherNo() + "$%&" + event.getPayload().getStateReason();
        default:
            return "feeder.messages.AccountNoteFeeder.DEFAULT$%&" + identity.getUserName() + "$%&" + event.getPayload().getVoucherNo();
		}
	}

	private List<FeaturePointer> prepareMentionList(AccountVirement entity) {
		List<FeaturePointer> mentions = new ArrayList<>();

		FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
		FeaturePointer fromContactPointer = FeatureUtils.getAccountFeaturePointer(entity.getFromAccount());
		FeaturePointer toContactPointer = FeatureUtils.getAccountFeaturePointer(entity.getToAccount());

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
