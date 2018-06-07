package com.ozguryazilim.tekir.order.sales;

import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.account.commands.RefreshAccountTxnsEvent;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.SalesOrder;
import com.ozguryazilim.tekir.order.OrderTxnRefresher;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

@Dependent
@Transactional
public class SalesOrderTxnRefresher extends OrderTxnRefresher<SalesOrder> {

    @Inject
    private SalesOrderRepository repository;

    @Inject
    private AccountTxnRepository accountTxnRepository;

    public void refreshAccountTxns(@Observes RefreshAccountTxnsEvent event) {
        List<SalesOrder> salesInvoices = repository
            .findByDateBetween(event.getBeginDate().getCalculatedValue(),
                event.getEndDate().getCalculatedValue());
        String feature = FeatureRegistery.getFeatureClass(SalesOrder.class).getSimpleName();
        accountTxnRepository.deleteByFeature_featureAndDateBetween(feature,
            event.getBeginDate().getCalculatedValue(), event.getEndDate().getCalculatedValue());

        for (SalesOrder entity : salesInvoices) {
            sendAccountTxnSaveCommand(entity);
        }
    }

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.SALES;
    }
}