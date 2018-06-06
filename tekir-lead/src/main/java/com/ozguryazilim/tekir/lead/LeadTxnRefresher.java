package com.ozguryazilim.tekir.lead;

import com.ozguryazilim.tekir.voucher.group.commands.RefreshVoucherGroupTxnsEvent;
import com.ozguryazilim.tekir.entities.Lead;
import com.ozguryazilim.tekir.entities.VoucherGroupTxn;
import com.ozguryazilim.tekir.voucher.group.VoucherGroupTxnRepository;
import com.ozguryazilim.tekir.voucher.group.VoucherGroupTxnService;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

@Dependent
@Transactional
public class LeadTxnRefresher {

    @Inject
    private LeadRepository repository;

    @Inject
    private VoucherGroupTxnRepository voucherGroupTxnRepository;

    public void refreshVoucherGroupTxns(@Observes RefreshVoucherGroupTxnsEvent event) {
        List<Lead> leads = repository.findByDateBetween(event.getBeginDate().getCalculatedValue(),
            event.getEndDate().getCalculatedValue());
        String feature = FeatureRegistery.getFeatureClass(Lead.class).getSimpleName();
        voucherGroupTxnRepository.deleteByFeature_featureAndDateBetween(feature,
            event.getBeginDate().getCalculatedValue(), event.getEndDate().getCalculatedValue());

        for (Lead entity : leads) {
            if (entity.getGroup() != null) {
                FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

                VoucherGroupTxn txn = new VoucherGroupTxn();

                txn.setGroup(entity.getGroup());
                txn.setFeature(voucherPointer);
                txn.setTopic(entity.getTopic());
                txn.setDate(entity.getDate());
                txn.setOwner(entity.getOwner());
                txn.setState(entity.getState());

                voucherGroupTxnRepository.save(txn);
            }
        }
    }
}
