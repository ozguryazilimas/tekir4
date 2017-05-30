/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.group;

import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroupStatus;
import com.ozguryazilim.tekir.voucher.config.VoucherPages;
import com.ozguryazilim.telve.view.DialogBase;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

/**
 * Yeni Grup Tanım Popup Controller
 * 
 * @author Hakan Uygun
 */
@Named
@SessionScoped
public class NewVoucherGroupDialog extends DialogBase implements Serializable {

	@Inject
	private VoucherGroupService voucherGroupService;

	@Inject
	private VoucherGroupRepository repository;

	private VoucherGroup entity;
	
	@Override
	public void beforeOpenDialog() {
		entity = new VoucherGroup();
	}

	/**
	 * Yeni person'ı save eder.
	 */
	@Override
	public void closeDialog() {
		entity.setStatus(VoucherGroupStatus.ACTIVE);
		voucherGroupService.saveVoucherGroup(entity);

		// navigationParameterContext.addPageParameter("eid",
		// voucherGroup.getId());
		// viewNavigationHandler.navigateTo(VoucherGroupPages.VoucherGroupView.class);
		
		closeDialogWindow();
	}

	@Override
	public Class<? extends ViewConfig> getDialogViewConfig() {
		return VoucherPages.Voucher.Group.NewVoucherGroupPopup.class;
	}

	public VoucherGroup getEntity() {
		return entity;
	}

	public void setEntity(VoucherGroup entity) {
		this.entity = entity;
	}

}
