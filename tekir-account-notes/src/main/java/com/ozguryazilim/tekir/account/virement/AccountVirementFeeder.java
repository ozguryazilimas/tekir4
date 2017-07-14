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

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getInfo(),
					getMessage(event), mentions);

		}
	}

	public void feed(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = AccountVirementFeature.class) @After VoucherOwnerChange event) {

		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		if (event.getPayload() instanceof AccountVirement) {

			AccountVirement entity = (AccountVirement) event.getPayload();

			List<FeaturePointer> mentions = prepareMentionList(entity);

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(), entity.getInfo(),
					FeederUtils.getEventMessage(event), mentions);

		}
	}

    public void feed(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = AccountVirement.class) @After EntityChangeEvent event) {
        
        if( event.getAction() != EntityChangeAction.DELETE   ) {
            AccountVirement entity = (AccountVirement) event.getEntity();
            
            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

            if( entity.getGroup()!=null){
    				voucherGroupTxnService.saveFeature(voucherPointer, entity.getGroup(), entity.getOwner(), entity.getTopic(),
    						entity.getDate(), entity.getState());
    		}
        }
        
        //TODO: Delete edildiğinde de gidip txn'den silme yapılmalı.
        
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
			return "Credit Note created";
		case "Publish":
			return "Credit Note published";
		case "Won":
			return "Credit Note";
		case "Loss":
			return "Opportunity Lost" + event.getPayload().getStateReason();
		case "Cancel":
			return "Opportunity canceled. " + event.getPayload().getStateReason();

		}

		switch (event.getTo().getName()) {
		case "OPEN":
			return "Opportunity created";
		case "CLOSE":
			return "Opportunity Won. Congrats!";
		case "LOST":
			return "Opportunity lost! " + event.getPayload().getStateReason();
		case "CANCELED":
			return "Opportunity canceled. " + event.getPayload().getStateReason();
		default:
			return "Opportunity created";
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
