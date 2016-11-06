/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.virement;

import com.ozguryazilim.tekir.account.config.AccountNotePages;
import com.ozguryazilim.tekir.entities.AccountVirement;
import com.ozguryazilim.tekir.entities.VoucherState;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.tekir.voucher.VoucherStateAction;
import com.ozguryazilim.tekir.voucher.VoucherStateConfig;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.feature.FeatureHandler;
import com.ozguryazilim.telve.forms.FormEdit;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@FormEdit(browsePage = AccountNotePages.AccountVirementBrowse.class, editPage = AccountNotePages.AccountVirement.class, viewContainerPage = AccountNotePages.AccountVirementView.class, masterViewPage = AccountNotePages.AccountVirementMasterView.class)
public class AccountVirementHome extends VoucherFormBase<AccountVirement>{

    @Inject
    private AccountVirementRepository repository;
    
    @Override
    protected VoucherStateConfig buildStateConfig() {
        VoucherStateConfig config = new VoucherStateConfig();
        config.addTranstion(VoucherState.DRAFT, new VoucherStateAction("Publish", "fa fa-check", false, ""), VoucherState.CLOSE);
        config.addTranstion(VoucherState.CLOSE, new VoucherStateAction("Revise", "fa fa-unlock", false, ""), VoucherState.OPEN);
        config.addTranstion(VoucherState.OPEN, new VoucherStateAction("Publish", "fa fa-check", true, ""), VoucherState.CLOSE);
        return config;
    }

    @Override
    public Class<? extends FeatureHandler> getFeatureClass() {
        return AccountVirementFeature.class;
    }

    @Override
    protected RepositoryBase<AccountVirement, ?> getRepository() {
        return repository;
    }
    
}
