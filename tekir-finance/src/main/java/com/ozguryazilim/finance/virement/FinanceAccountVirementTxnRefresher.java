package com.ozguryazilim.finance.virement;

import com.ozguryazilim.tekir.entities.FinanceAccountVirement;
import com.ozguryazilim.tekir.voucher.group.commands.RefreshVoucherGroupTxnsEvent;
import com.ozguryazilim.tekir.voucher.group.VoucherGroupTxnRepository;
import com.ozguryazilim.tekir.voucher.group.commands.SaveVoucherGroupTxnsCommand;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import com.ozguryazilim.telve.messagebus.command.CommandSender;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

@Dependent
@Transactional
public class FinanceAccountVirementTxnRefresher {

    @Inject
    private FinanceAccountVirementRepository repository;

    @Inject
    private VoucherGroupTxnRepository voucherGroupTxnRepository;

    @Inject
    private CommandSender commandSender;

    public void refreshVoucherGroupTxns(@Observes RefreshVoucherGroupTxnsEvent event) {
        List<FinanceAccountVirement> financeAccountVirements = repository
            .findByDateBetween(event.getBeginDate().getCalculatedValue(),
                event.getEndDate().getCalculatedValue());
        String feature = FeatureRegistery.getFeatureClass(FinanceAccountVirement.class)
            .getSimpleName();
        voucherGroupTxnRepository.deleteByFeature_featureAndDateBetween(feature,
            event.getBeginDate().getCalculatedValue(), event.getEndDate().getCalculatedValue());

        for (FinanceAccountVirement entity : financeAccountVirements) {
            if (entity.getGroup() != null) {
                FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

                SaveVoucherGroupTxnsCommand saveCommand = new SaveVoucherGroupTxnsCommand(
                    entity.getGroup(),
                    voucherPointer,
                    entity.getTopic(),
                    entity.getDate(),
                    entity.getOwner(),
                    entity.getState());

                commandSender.sendCommand(saveCommand);
            }
        }
    }
}
