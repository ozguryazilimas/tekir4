/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.finance.account;

import com.ozguryazilim.finance.config.FinancePages;
import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.FeatureCategory;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;

/**
 *
 * @author oyas
 */
@Feature(permission = "financeAccount", forEntity = FinanceAccount.class, category = {FeatureCategory.ACCOUNTABLE} )
@Page( type = PageType.BROWSE, page = FinancePages.FinanceAccountBrowse.class )
@Page( type = PageType.VIEW, page = FinancePages.FinanceAccountView.class )
@Page( type = PageType.MASTER_VIEW, page = FinancePages.FinanceAccountMasterView.class )
@Page( type = PageType.EDIT, page = FinancePages.FinanceAccount.class )
public class FinanceAccountFeature extends AbstractFeatureHandler{
    
}
