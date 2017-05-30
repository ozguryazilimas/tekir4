package com.ozguryazilim.tekir.core.dialogs;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;

import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.telve.view.DialogBase;

@Named
@SessionScoped
public class ExchangeRateFetchDialog extends DialogBase implements Serializable {

	private FormBase bean;
	
	@Inject
	private ViewNavigationHandler viewNavigationHandler;
	
	public void openDialog(FormBase bean) {
		this.bean = bean;
		openDialog();
	}
	
	@Override
	public void closeDialog() {
		closeDialogWindow();
	    viewNavigationHandler.navigateTo(bean.save());
	}

	@Override
	public Class<? extends ViewConfig> getDialogViewConfig() {
		return null;
	}
	
	@Override
	public String getDialogName() {
		return "/dialogs/exchangeRateFetchDialog";
	}
	
	@Override
	protected void decorateDialog(Map<String, Object> options) {
		super.decorateDialog(options);
		options.put("modal", false);
	}

}
