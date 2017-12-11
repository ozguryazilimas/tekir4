/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.employee.leave;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

import com.ozguryazilim.tekir.account.AccountTxnService;
import com.ozguryazilim.tekir.entities.EmployeeLeave;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.tekir.entities.VoucherBase;
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
*
* @author oktay
*/
@Feeder
public class EmployeeLeaveFeeder extends AbstractFeeder<EmployeeLeave> {

	@Inject
	private Identity identity;
	

    public void feed(EmployeeLeave entity) {

        List<FeaturePointer> mentions = new ArrayList<>();
        
        FeaturePointer emp = new FeaturePointer();
        emp.setBusinessKey(entity.getEmployee().getName());
        emp.setPrimaryKey(entity.getEmployee().getId());
        emp.setFeature(FeatureRegistery.getFeatureClass(entity.getEmployee().getClass()).getSimpleName());
        mentions.add(emp);
        
      //EmployeeLeave 'in kendisini de katalım. Netekim izin detayını da görme ihtiyacı olacaktır.
        FeaturePointer fp = new FeaturePointer();
        fp.setBusinessKey(entity.getInfo());
        fp.setPrimaryKey(entity.getId());
        fp.setFeature(EmployeeLeave.class.getSimpleName());
        mentions.add(fp);
    }
    
    
    private List<FeaturePointer> prepareMentionList(EmployeeLeave entity){
		
		List<FeaturePointer> mentions = new ArrayList<>();
		FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
		FeaturePointer contactPointer = FeatureUtils.getAccountFeaturePointer(entity.getEmployee());


		if (entity.getGroup() != null && entity.getGroup().isPersisted()) {
			FeaturePointer groupPointer = FeatureUtils.getVoucherGroupPointer(entity);
			mentions.add(groupPointer);
		}

		mentions.add(voucherPointer);
		mentions.add(contactPointer);
		
		return mentions;
    	
    }
     
	public void feed(@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = EmployeeLeaveFeature.class) @After VoucherStateChange event) {
		
		if (event.getPayload() instanceof EmployeeLeave) {

			EmployeeLeave entity = (EmployeeLeave) event.getPayload();
		
			List<FeaturePointer> mentions = prepareMentionList(entity);
		
			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
					entity.getTopic(), getMessage(event), mentions);

		}
	}
    
	public void feed(@Observes(during = TransactionPhase.AFTER_SUCCESS) @FeatureQualifier(feauture = EmployeeLeaveFeature.class) @After VoucherOwnerChange event) {

		// FIXME: acaba bunun için bir Qualifier yapabilir miyiz?
		if (event.getPayload() instanceof EmployeeLeave) {

			EmployeeLeave entity = (EmployeeLeave) event.getPayload();

			List<FeaturePointer> mentions = prepareMentionList(entity);

			sendFeed(entity.getState().getName(), getClass().getSimpleName(), identity.getLoginName(),
					entity.getTopic(), FeederUtils.getEventMessage(event), mentions);
		}
	}
	
	public void feed(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = EmployeeLeave.class) @After EntityChangeEvent event) {

		if (event.getAction() != EntityChangeAction.DELETE) {
			EmployeeLeave entity = (EmployeeLeave) event.getEntity();

			FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

		}
	}

 
	protected String getMessage(VoucherStateChange event) {
		switch (event.getAction().getName()) {
		case "CREATE":
			return "feeder.messages.EmployeeLeaveFeeder.CREATE$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo();
		case "publish":
			return "Employee leave published";
		case "approved":
			return "feeder.messages.EmployeeLeaveFeeder.WON$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo();
		case "cancel":
			return "feeder.messages.EmployeeLeaveFeeder.CANCEL$%&" + identity.getUserName() + "$%&"
					+ event.getPayload().getVoucherNo() + "$%&" + event.getPayload().getStateReason();

		}

		switch (event.getTo().getName()) {
		case "OPEN":
			return "Employee leave created";
		case "CLOSE":
			return "Employee leave approved";
		case "CANCELED":
			return "Employee leave canceled. " + event.getPayload().getStateReason();
		default:
			return "Employee leave created";
		}
	} 
}
