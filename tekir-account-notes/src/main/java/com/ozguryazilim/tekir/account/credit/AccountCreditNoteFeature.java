/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.credit;

import com.ozguryazilim.tekir.account.config.AccountNotePages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.tekir.entities.AccountCreditNote;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(caption = "feature.caption.AccountCreditNote", icon = "fa fa-book", permission = "accountCreditNote", forEntity = AccountCreditNote.class )
@Page( type = PageType.BROWSE, page = AccountNotePages.AccountCreditNoteBrowse.class )
@Page( type = PageType.VIEW, page = AccountNotePages.AccountCreditNoteView.class )
@Page( type = PageType.MASTER_VIEW, page = AccountNotePages.AccountCreditNoteMasterView.class )
@Page( type = PageType.EDIT, page = AccountNotePages.AccountCreditNote.class )
public class AccountCreditNoteFeature extends AbstractFeatureHandler{
    
}
