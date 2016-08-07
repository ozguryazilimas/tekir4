package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
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

    @Inject
    private CurrencyService currencyService;
    
    private QuoteItem selectedItem;
    
    @Override
    protected RepositoryBase<Quote, QuoteViewModel> getRepository() {
        return repository;
    }

    @Override
    public void createNew() {
        super.createNew();

        getEntity().setTotal(new Money(currencyService.getDefaultCurrency()));
    }

    
    public void addItem(){
        QuoteItem item = new QuoteItem();
        item.setQuantity(new Quantity(BigDecimal.ZERO, "HDE:Karton"));
        item.setPrice(new Money(BigDecimal.ZERO, "TRY"));
        item.setTotal(new Money(BigDecimal.ZERO, "TRY"));
        item.setMaster(getEntity());
        selectedItem = item;
    }
    
    public void editItem( QuoteItem item ){
        selectedItem = item;
    }
    
    public void deleteIten( QuoteItem item ){
        getEntity().getItems().remove(item);
    }
    
    public void saveItem(){
        getEntity().getItems().add(selectedItem);
    }

    public QuoteItem getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(QuoteItem selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    
}
