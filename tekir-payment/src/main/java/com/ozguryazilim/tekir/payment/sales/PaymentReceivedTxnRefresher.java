package com.ozguryazilim.tekir.payment.sales;

import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.account.commands.RefreshAccountTxnsEvent;
import com.ozguryazilim.tekir.entities.PaymentReceived;
import com.ozguryazilim.tekir.entities.ProcessType;
import com.ozguryazilim.tekir.payment.PaymentBaseTxnRefresher;
import com.ozguryazilim.tekir.voucher.group.commands.RefreshVoucherGroupTxnsEvent;
import com.ozguryazilim.tekir.voucher.group.VoucherGroupTxnRepository;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

@Dependent
@Transactional
public class PaymentReceivedTxnRefresher extends PaymentBaseTxnRefresher<PaymentReceived> {

    @Inject
    private PaymentReceivedRepository repository;

    @Inject
    private VoucherGroupTxnRepository voucherGroupTxnRepository;

    @Inject
    private AccountTxnRepository accountTxnRepository;

    public void refreshVoucherGroupTxns(@Observes RefreshVoucherGroupTxnsEvent event) {
        List<PaymentReceived> paymentReceiveds = repository
            .findByDateBetween(event.getBeginDate().getCalculatedValue(),
                event.getEndDate().getCalculatedValue());
        String feature = FeatureRegistery.getFeatureClass(PaymentReceived.class).getSimpleName();
        voucherGroupTxnRepository.deleteByFeature_featureAndDateBetween(feature,
            event.getBeginDate().getCalculatedValue(), event.getEndDate().getCalculatedValue());

        for (PaymentReceived entity : paymentReceiveds) {
            if (entity.getGroup() != null) {
                sendVoucherGroupTxnSaveCommand(entity);
            }
        }
    }

    public void refreshAccountTxns(@Observes RefreshAccountTxnsEvent event) {
        List<PaymentReceived> paymentReceiveds = repository
            .findByDateBetween(event.getBeginDate().getCalculatedValue(),
                event.getEndDate().getCalculatedValue());
        String feature = FeatureRegistery.getFeatureClass(PaymentReceived.class).getSimpleName();
        accountTxnRepository.deleteByFeature_featureAndDateBetween(feature,
            event.getBeginDate().getCalculatedValue(), event.getEndDate().getCalculatedValue());

        for (PaymentReceived entity : paymentReceiveds) {
            sendAccountTxnSaveCommand(entity);
        }
    }

    @Override
    protected ProcessType getProcessType() {
        return ProcessType.SALES;
    }
}
