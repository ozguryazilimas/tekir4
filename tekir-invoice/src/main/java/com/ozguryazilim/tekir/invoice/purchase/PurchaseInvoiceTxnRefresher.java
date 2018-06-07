package com.ozguryazilim.tekir.invoice.purchase;

import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.account.commands.RefreshAccountTxnsEvent;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.PurchaseInvoice;
import com.ozguryazilim.tekir.invoice.InvoiceTxnRefresher;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

@Dependent
@Transactional
public class PurchaseInvoiceTxnRefresher extends InvoiceTxnRefresher<PurchaseInvoice> {

    @Inject
    private PurchaseInvoiceRepository repository;

    @Inject
    private AccountTxnRepository accountTxnRepository;

    public void refreshAccountTxns(@Observes RefreshAccountTxnsEvent event) {
        List<PurchaseInvoice> purchaseInvoices = repository
            .findByDateBetween(event.getBeginDate().getCalculatedValue(),
                event.getEndDate().getCalculatedValue());
        String feature = FeatureRegistery.getFeatureClass(PurchaseInvoice.class).getSimpleName();
        accountTxnRepository.deleteByFeature_featureAndDateBetween(feature,
            event.getBeginDate().getCalculatedValue(), event.getEndDate().getCalculatedValue());

        for (PurchaseInvoice entity : purchaseInvoices) {
            sendAccountTxnSaveCommand(entity);
        }
    }

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.SALES;
    }
}