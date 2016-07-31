package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.quote.config.QuotePages;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.math.BigDecimal;
import javax.inject.Inject;
import org.javamoney.moneta.Money;

/**
 * Home Control Class
 * 
 * @author
 */
@FormEdit(browsePage = QuotePages.QuoteBrowse.class, editPage = QuotePages.Quote.class, viewContainerPage = QuotePages.QuoteView.class, masterViewPage = QuotePages.QuoteMasterView.class)
public class QuoteHome extends VoucherFormBase<Quote> {

	@Inject
	private QuoteRepository repository;

	@Override
	protected RepositoryBase<Quote, QuoteViewModel> getRepository() {
		return repository;
	}

    @Override
    public void createNew() {
        super.createNew(); 
        
        getEntity().setTotalAmount(Money.of(BigDecimal.TEN, "USD"));
    }
        
        
}