/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.group;

import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.VoucherGroup;
import com.ozguryazilim.tekir.entities.VoucherGroupStatus;
import com.ozguryazilim.tekir.voucher.config.VoucherPages;
import com.ozguryazilim.tekir.voucher.process.ProcessService;
import com.ozguryazilim.telve.sequence.SequenceManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.deltaspike.core.api.config.view.navigation.NavigationParameterContext;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.primefaces.context.RequestContext;

/**
 * Yeni Grup Tanım Popup Controller
 * 
 * @author Hakan Uygun
 */
@Named
@SessionScoped
public class NewVoucherGroupDialog implements Serializable {

	@Inject
	private ViewConfigResolver viewConfigResolver;

	@Inject
	private VoucherGroupService voucherGroupService;

	@Inject
	private VoucherGroupRepository repository;

	private VoucherGroup entity;

	public void openDialog() {
		entity = new VoucherGroup();

		openDialogImpl();
	}

	protected void openDialogImpl() {
		Map<String, Object> options = new HashMap<>();
		options.put("modal", true);
		// options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 450);

		RequestContext.getCurrentInstance().openDialog(getDialogName(), options, null);
	}

	/**
	 * Yeni person'ı save eder.
	 */
	public void closeDialog() {
		entity.setStatus(VoucherGroupStatus.ACTIVE);
		voucherGroupService.saveVoucherGroup(entity);

		// navigationParameterContext.addPageParameter("eid",
		// voucherGroup.getId());
		// viewNavigationHandler.navigateTo(VoucherGroupPages.VoucherGroupView.class);

		RequestContext.getCurrentInstance().closeDialog(null);
	}

	/**
	 * Bir şey yapmadan çık.
	 */
	public void cancelDialog() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	/**
	 * startPopup dialog adını döndürür.
	 *
	 * @return
	 */
	public String getDialogName() {
		String viewId = viewConfigResolver.getViewConfigDescriptor(VoucherPages.Group.NewVoucherGroupPopup.class).getViewId();
		return viewId.substring(0, viewId.indexOf(".xhtml"));
	}

	public VoucherGroup getEntity() {
		return entity;
	}

	public void setEntity(VoucherGroup entity) {
		this.entity = entity;
	}

}
