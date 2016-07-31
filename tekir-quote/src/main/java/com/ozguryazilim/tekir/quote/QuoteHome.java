package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.entities.Money;
import com.ozguryazilim.tekir.entities.Quantity;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.entities.QuoteItem;
import com.ozguryazilim.tekir.quote.config.QuotePages;
import com.ozguryazilim.tekir.voucher.VoucherFormBase;
import com.ozguryazilim.telve.data.RepositoryBase;
import java.math.BigDecimal;
import javax.inject.Inject;

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

        getEntity().setTotalAmount(new Money(BigDecimal.ZERO, "TRL"));
    }

    
    public void addItem(){
        QuoteItem item = new QuoteItem();
        item.setQuantity(new Quantity(BigDecimal.ZERO, "ADET"));
        item.setUnitPrice(new Money(BigDecimal.ZERO, "TRL"));
        item.setTotalAmount(new Money(BigDecimal.ZERO, "TRL"));
        item.setMaster(getEntity());
        getEntity().getItems().add(item);
    }
}
