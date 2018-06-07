package com.ozguryazilim.tekir.order.purchase;

import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.account.commands.RefreshAccountTxnsEvent;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.entities.PurchaseOrder;
import com.ozguryazilim.tekir.order.OrderTxnRefresher;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import com.ozguryazilim.telve.messagebus.command.CommandSender;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

@Dependent
@Transactional
public class PurchaseOrderTxnRefresher extends OrderTxnRefresher<PurchaseOrder> {

    @Inject
    private PurchaseOrderRepository repository;

    @Inject
    private AccountTxnRepository accountTxnRepository;

    @Inject
    private CommandSender commandSender;

    public void refreshAccountTxns(@Observes RefreshAccountTxnsEvent event) {
        List<PurchaseOrder> purchaseOrders = repository
            .findByDateBetween(event.getBeginDate().getCalculatedValue(),
                event.getEndDate().getCalculatedValue());
        String feature = FeatureRegistery.getFeatureClass(PurchaseOrder.class).getSimpleName();
        accountTxnRepository.deleteByFeature_featureAndDateBetween(feature,
            event.getBeginDate().getCalculatedValue(), event.getEndDate().getCalculatedValue());

        for (PurchaseOrder entity : purchaseOrders) {
            sendAccountTxnSaveCommand(entity);
        }
    }

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.PURCHASE;
    }
}