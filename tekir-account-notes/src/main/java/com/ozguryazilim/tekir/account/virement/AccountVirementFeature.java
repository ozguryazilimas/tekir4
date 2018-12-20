package com.ozguryazilim.tekir.account.virement;

import com.ozguryazilim.tekir.account.config.AccountNotePages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.tekir.entities.AccountVirement;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import javax.enterprise.inject.Default;

/**
 *
 * @author oyas
 */
@Feature(permission = "accountVirement", forEntity = AccountVirement.class )
@Page( type = PageType.BROWSE, page = AccountNotePages.AccountVirementBrowse.class )
@Page( type = PageType.VIEW, page = AccountNotePages.AccountVirementView.class )
@Page( type = PageType.MASTER_VIEW, page = AccountNotePages.AccountVirementMasterView.class )
@Page( type = PageType.EDIT, page = AccountNotePages.AccountVirement.class )
@Voucher @Default
public class AccountVirementFeature extends AbstractFeatureHandler{
    
}
