/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.debit;

import com.ozguryazilim.tekir.account.config.AccountNotePages;
import com.ozguryazilim.tekir.entities.AccountDebitNote;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.FeatureCategory;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import com.ozguryazilim.telve.feature.search.Search;

import javax.enterprise.inject.Default;

/**
 *
 * @author oyas
 */
@Feature(permission = "accountCreditNote", forEntity = AccountDebitNote.class, category = {FeatureCategory.ACCOUNTABLE} )
@Page( type = PageType.BROWSE, page = AccountNotePages.AccountDebitNoteBrowse.class )
@Page( type = PageType.VIEW, page = AccountNotePages.AccountDebitNoteView.class )
@Page( type = PageType.MASTER_VIEW, page = AccountNotePages.AccountDebitNoteMasterView.class )
@Page( type = PageType.EDIT, page = AccountNotePages.AccountDebitNote.class )
@Search(handler = AccountDebitNoteSearchHandler.class )
@Voucher @Default
public class AccountDebitNoteFeature extends AbstractFeatureHandler{
    
}
