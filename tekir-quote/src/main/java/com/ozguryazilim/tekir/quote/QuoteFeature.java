/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.quote.config.QuotePages;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import javax.enterprise.inject.Default;

/**
 * Quote Feature Definition
 * @author Hakan Uygun
 */
@Feature(permission = "quote", forEntity = Quote.class )
@Page( type = PageType.BROWSE, page = QuotePages.QuoteBrowse.class )
@Page( type = PageType.VIEW, page = QuotePages.QuoteView.class )
@Page( type = PageType.MASTER_VIEW, page = QuotePages.QuoteMasterView.class )
@Page( type = PageType.EDIT, page = QuotePages.Quote.class )
@Voucher @Default
public class QuoteFeature extends AbstractFeatureHandler{
    
}
