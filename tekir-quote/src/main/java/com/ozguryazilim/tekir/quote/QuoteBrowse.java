package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.telve.forms.Browse;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.tekir.entities.Quote;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.voucher.VoucherBrowseBase;
import com.ozguryazilim.tekir.voucher.VoucherRepositoryBase;
import com.ozguryazilim.telve.query.columns.LinkColumn;
import javax.inject.Inject;

/**
 * Brwose Control Class
 *
 * @author
 */
@Browse( feature=QuoteFeature.class )
public class QuoteBrowse extends VoucherBrowseBase<Quote, QuoteViewModel> {

    @Inject
    private QuoteRepository repository;

    @Override
    protected void buildQueryDefinition(QueryDefinition<Quote, QuoteViewModel> queryDefinition) {
        queryDefinition
                .addColumn(new LinkColumn<>(VoucherBase_.voucherNo, "voucher.label.VoucherNo"), true);

    }

    @Override
    public VoucherRepositoryBase<Quote, QuoteViewModel> getVoucherRepository() {
        return repository;
    }
}
