package com.ozguryazilim.finance.virement;

import com.ozguryazilim.finance.account.txn.FinanceAccountTxnRepository;
import com.ozguryazilim.finance.account.txn.commands.RefreshFinanceAccountTxnsEvent;
import com.ozguryazilim.finance.account.txn.commands.SaveFinanceAccounTxnsCommand;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.tekir.entities.FinanceAccountVirement;
import com.ozguryazilim.tekir.voucher.group.commands.RefreshVoucherGroupTxnsEvent;
import com.ozguryazilim.tekir.voucher.group.VoucherGroupTxnRepository;
import com.ozguryazilim.tekir.voucher.group.commands.SaveVoucherGroupTxnsCommand;
import com.ozguryazilim.tekir.voucher.utils.FeatureUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import com.ozguryazilim.telve.messagebus.command.CommandSender;
import java.math.BigDecimal;
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
    private FinanceAccountTxnRepository financeAccountTxnRepository;

    @Inject
    private CommandSender commandSender;

    @Inject
    private CurrencyService currencyService;

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

    public void refreshFinanceAccountTxns(@Observes RefreshFinanceAccountTxnsEvent event) {
        List<FinanceAccountVirement> financeAccountVirements = repository
            .findByDateBetween(event.getBeginDate().getCalculatedValue(),
                event.getEndDate().getCalculatedValue());
        String feature = FeatureRegistery.getFeatureClass(FinanceAccountVirement.class)
            .getSimpleName();
        financeAccountTxnRepository.deleteByFeature_featureAndDateBetween(feature,
            event.getBeginDate().getCalculatedValue(), event.getEndDate().getCalculatedValue());

        for (FinanceAccountVirement entity : financeAccountVirements) {
            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

            BigDecimal fromLocalAmount;
            BigDecimal toLocalAmount;
            if (entity.getFromCurrency() == currencyService.getReportCurrency()) {
                fromLocalAmount = entity.getFromAmount();
            } else {
                fromLocalAmount = entity.getLocalAmount();
            }

            if (entity.getToCurrency() == currencyService.getReportCurrency()) {
                toLocalAmount = entity.getToAmount();
            } else {
                toLocalAmount = entity.getLocalAmount();
            }

            SaveFinanceAccounTxnsCommand saveFromCommand = new SaveFinanceAccounTxnsCommand(
                voucherPointer, entity.getFromAccount(), entity.getInfo(), entity.getTags(),
                Boolean.TRUE, Boolean.TRUE, entity.getFromCurrency(), entity.getFromAmount(),
                fromLocalAmount, entity.getDate(), entity.getOwner(), null,
                entity.getState().toString(), entity.getStateReason(), null);

            commandSender.sendCommand(saveFromCommand);

            SaveFinanceAccounTxnsCommand saveToCommand = new SaveFinanceAccounTxnsCommand(
                voucherPointer, entity.getToAccount(), entity.getInfo(), entity.getTags(),
                Boolean.TRUE, Boolean.FALSE, entity.getToCurrency(), entity.getToAmount(),
                toLocalAmount, entity.getDate(), entity.getOwner(), null,
                entity.getState().toString(), entity.getStateReason(), null);

            commandSender.sendCommand(saveToCommand);
        }
    }
}
