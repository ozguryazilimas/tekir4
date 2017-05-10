package com.ozguryazilim.tekir.lead;

import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.Lead;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherPrintOutAction;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.forms.FormEdit;

@FormEdit(feature = LeadFeature.class)
public class LeadHome extends VoucherFormBase<Lead> {

	@Inject
	private LeadRepository repository;

	@Override
	protected VoucherStateConfig buildStateConfig() {
		VoucherStateConfig config = new VoucherStateConfig();

		config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("publish", "fa fa-check"), VoucherState.OPEN);
		config.addTranstion(VoucherState.OPEN, new VoucherStateAction("won", "fa fa-check"), VoucherState.WON);
		config.addTranstion(VoucherState.OPEN, new VoucherStateAction("loss", "fa fa-close", true), VoucherState.LOSS);
		config.addTranstion(VoucherState.OPEN, new VoucherStateAction("cancel", "fa fa-ban", true), VoucherState.CLOSE);
		config.addTranstion(VoucherState.OPEN, new VoucherStateAction("revise", "fa fa-unlock", true),
				VoucherState.DRAFT);

		config.addStateTypeAction(VoucherStateType.OPEN, new VoucherPrintOutAction(this));
		config.addStateTypeAction(VoucherStateType.CLOSE, new VoucherPrintOutAction(this));

		return config;
	}

	@Override
	protected RepositoryBase<Lead, ?> getRepository() {
		return repository;
	}

	@Override
	public boolean onBeforeSave() {

		if (getEntity().getState().equals(VoucherState.DRAFT) && !getEntity().isPersisted()) {
			getEntity().setState(VoucherState.OPEN);
		}

		return super.onBeforeSave();
	}

}
