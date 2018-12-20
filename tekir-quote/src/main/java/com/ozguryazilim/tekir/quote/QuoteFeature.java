package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.quote.config.QuotePages;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import com.ozguryazilim.telve.feature.search.Search;
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
@Search(handler = QuoteSearchHandler.class )
@Voucher @Default
public class QuoteFeature extends AbstractFeatureHandler{
    
}
