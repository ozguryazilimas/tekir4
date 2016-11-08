/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.debit;

import com.ozguryazilim.tekir.account.config.AccountNotePages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.tekir.entities.AccountDebitNote;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(caption = "feature.caption.AccountDebitNote", icon = "fa fa-book", permission = "accountCreditNote", forEntity = AccountDebitNote.class )
@Page( type = PageType.BROWSE, page = AccountNotePages.AccountDebitNoteBrowse.class )
@Page( type = PageType.VIEW, page = AccountNotePages.AccountDebitNoteView.class )
@Page( type = PageType.MASTER_VIEW, page = AccountNotePages.AccountDebitNoteMasterView.class )
@Page( type = PageType.EDIT, page = AccountNotePages.AccountDebitNote.class )
public class AccountDebitNoteFeature extends AbstractFeatureHandler{
    
}
