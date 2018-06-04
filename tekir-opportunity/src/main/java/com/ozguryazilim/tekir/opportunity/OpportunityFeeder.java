/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.opportunity;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.entities.Opportunity;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.voucher.VoucherOwnerChange;
import com.ozguryazilim.tekir.voucher.VoucherStateChange;
import com.ozguryazilim.tekir.voucher.group.VoucherGroupTxnService;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.tekir.voucher.utils.FeederUtils;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureQualifier;
import com.ozguryazilim.telve.forms.EntityChangeAction;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.messages.Messages;
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
public class OpportunityFeeder extends AbstractFeeder<Opportunity> {

	@Inject
	private Identity identity;

	@Inject
	private AccountTxnService accountTxnService;

	@Inject
	private ProcessService processService;

	@Inject
	private VoucherGroupTxnService voucherGroupTxnService;

	public void feed(@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = OpportunityFeature.class) @After VoucherStateChange event) {
			
		if (event.getPayload() instanceof Opportunity) {

			Opportunity entity = (Opportunity) event.getPayload();
		
			List<FeaturePointer> mentions = prepareMentionList(entity);
		
			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
					entity.getTopic(), getMessage(event), mentions);

			// Process feed
			if ("OPEN".equals(event.getTo().getName()) && "DRAFT".equals(event.getFrom().getName())) {
				processService.incProcessUsage(entity.getProcess());
			}

			if (event.getTo().getType().equals(VoucherStateType.CLOSE)) {
				processService.decProcessUsage(entity.getProcess());
			}

		}
	}
	
	public void feed(@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = OpportunityFeature.class) @After VoucherOwnerChange event) {

		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		if (event.getPayload() instanceof Opportunity) {

			Opportunity entity = (Opportunity) event.getPayload();

			List<FeaturePointer> mentions = prepareMentionList(entity);

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
					entity.getTopic(), FeederUtils.getEventMessage(event), mentions);
		}
	}

	public void feed(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = Opportunity.class) @After EntityChangeEvent event) {
        Opportunity entity = (Opportunity) event.getEntity();

        FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

        if (event.getAction() != EntityChangeAction.DELETE) {

			accountTxnService.saveFeature(voucherPointer, entity.getAccount(), entity.getInfo(), entity.getTags(),
					Boolean.FALSE, Boolean.TRUE, entity.getCurrency(), entity.getBudget(), entity.getLocalBudget(),
					entity.getDate(), entity.getOwner(), entity.getProcess().getProcessNo(), entity.getState().toString(),
					entity.getStateReason(), entity.getTopic());
			
			
			if( entity.getGroup()!=null){
				voucherGroupTxnService.saveFeature(voucherPointer, entity.getGroup(), entity.getOwner(), entity.getTopic(),
						entity.getDate(), entity.getState());
			}
		}

        if (event.getAction() == EntityChangeAction.DELETE) {
            accountTxnService.deleteFeature(voucherPointer, entity.getAccount());

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
		String reason = event.getPayload().getStateReason();
		if (reason == null) {
			reason = Messages.getMessage("feed.messages.NullReason");
		}
		switch (event.getAction().getName()) {
			case "CREATE":
				return "feeder.messages.OpportunityFeeder.CREATE$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo();
			case "publish":
				return "feeder.messages.OpportunityFeeder.PUBLISH$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo();
			case "won":
				return "feeder.messages.OpportunityFeeder.WON$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo();
			case "loss":
				return "feeder.messages.OpportunityFeeder.LOST$%&" + event.getPayload().getVoucherNo() + "$%&"
					+ reason;
			case "cancel":
				return "feeder.messages.OpportunityFeeder.CANCEL$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo() + "$%&" + reason;
			case "revise":
				return "feeder.messages.OpportunityFeeder.REVISE$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo() + "$%&" + reason;
			default:
				return "feeder.messages.OpportunityFeeder.DEFAULT$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo();
		}
	}
	
	private List<FeaturePointer> prepareMentionList(Opportunity entity){
		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		
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
