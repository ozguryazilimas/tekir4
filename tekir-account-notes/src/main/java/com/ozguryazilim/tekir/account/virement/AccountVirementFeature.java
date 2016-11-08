/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.virement;

import com.ozguryazilim.tekir.account.config.AccountNotePages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.tekir.entities.AccountVirement;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(caption = "feature.caption.AccountVirement", icon = "fa fa-book", permission = "accountVirement", forEntity = AccountVirement.class )
@Page( type = PageType.BROWSE, page = AccountNotePages.AccountVirementBrowse.class )
@Page( type = PageType.VIEW, page = AccountNotePages.AccountVirementView.class )
@Page( type = PageType.MASTER_VIEW, page = AccountNotePages.AccountVirementMasterView.class )
@Page( type = PageType.EDIT, page = AccountNotePages.AccountVirement.class )
public class AccountVirementFeature extends AbstractFeatureHandler{
    
}
