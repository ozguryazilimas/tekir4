package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.telve.lookup.Lookup;
import com.ozguryazilim.telve.lookup.LookupTableControllerBase;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.entities.Quote_;
import com.ozguryazilim.tekir.quote.config.QuotePages;
import com.ozguryazilim.telve.lookup.LookupTableModel;
import com.ozguryazilim.telve.data.RepositoryBase;
import javax.inject.Inject;

/**
 * Lookup View Control Class
 * 
 * @author
 */
@Lookup(dialogPage = QuotePages.QuoteLookup.class)
public class QuoteLookup
		extends
			LookupTableControllerBase<Quote, QuoteViewModel> {

	@Inject
	private QuoteRepository repository;

	@Override
	public void buildModel(LookupTableModel<QuoteViewModel> model) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected RepositoryBase<Quote, QuoteViewModel> getRepository() {
		return repository;
	}

	@Override
	public String getCaptionFieldName() {
		return Quote_.voucherNo.getName();
	}
}