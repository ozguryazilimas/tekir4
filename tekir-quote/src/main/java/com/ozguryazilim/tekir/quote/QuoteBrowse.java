package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.tekir.quote.config.QuotePages;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import javax.inject.Inject;

/**
 * Brwose Control Class
 * 
 * @author
 */
@Browse(browsePage = QuotePages.QuoteBrowse.class, editPage = QuotePages.Quote.class, viewContainerPage = QuotePages.QuoteView.class)
public class QuoteBrowse extends VoucherBrowseBase<Quote, QuoteViewModel> {

	@Inject
	private QuoteRepository repository;

	@Override
	protected void buildQueryDefinition(QueryDefinition<Quote, QuoteViewModel> queryDefinition) {
            queryDefinition
                .addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true);
                
	}

	@Override
	protected RepositoryBase<Quote, QuoteViewModel> getRepository() {
		return repository;
	}
}