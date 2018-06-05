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

    @Inject
    private VoucherGroupTxnService voucherGroupTxnService;

    public void refreshVoucherGroupTxns(@Observes RefreshVoucherGroupTxnsEvent event) {
        List<Lead> leads = repository.findAll();
        String feature = FeatureRegistery.getFeatureClass(Lead.class).getSimpleName();
        voucherGroupTxnRepository.deleteByFeature_feature(feature);

        for (Lead entity : leads) {
            if (entity.getGroup() != null) {
                FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);
                voucherGroupTxnService
                    .saveFeature(voucherPointer, entity.getGroup(), entity.getOwner(),
                        entity.getTopic(), entity.getDate(), entity.getState());
            }
        }
    }
}
