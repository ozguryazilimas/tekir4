package com.ozguryazilim.tekir.quote;

import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.account.commands.RefreshAccountTxnsEvent;
import com.ozguryazilim.tekir.account.commands.SaveAccountTxnsCommand;
import com.ozguryazilim.tekir.entities.Quote;
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
public class QuoteTxnRefresher {

    @Inject
    private QuoteRepository repository;

    @Inject
    private VoucherGroupTxnRepository voucherGroupTxnRepository;

    @Inject
    private AccountTxnRepository accountTxnRepository;

    @Inject
    private CommandSender commandSender;

    public void refreshVoucherGroupTxns(@Observes RefreshVoucherGroupTxnsEvent event) {
        List<Quote> quotes = repository.findByDateBetween(event.getBeginDate().getCalculatedValue(),
            event.getEndDate().getCalculatedValue());
        String feature = FeatureRegistery.getFeatureClass(Quote.class).getSimpleName();
        voucherGroupTxnRepository.deleteByFeature_featureAndDateBetween(feature,
            event.getBeginDate().getCalculatedValue(), event.getEndDate().getCalculatedValue());

        for (Quote entity : quotes) {
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

    public void refreshAccountTxns(@Observes RefreshAccountTxnsEvent event) {
        List<Quote> quotes = repository
            .findByDateBetween(event.getBeginDate().getCalculatedValue(),
                event.getEndDate().getCalculatedValue());
        String feature = FeatureRegistery.getFeatureClass(Quote.class).getSimpleName();
        accountTxnRepository.deleteByFeature_featureAndDateBetween(feature,
            event.getBeginDate().getCalculatedValue(), event.getEndDate().getCalculatedValue());

        for (Quote entity : quotes) {
            FeaturePointer voucherPointer = FeatureUtils.getFeaturePointer(entity);

            SaveAccountTxnsCommand saveCommand = new SaveAccountTxnsCommand(voucherPointer,
                entity.getAccount(), entity.getInfo(), entity.getTags(), Boolean.FALSE,
                Boolean.FALSE, entity.getCurrency(), entity.getTotal(), entity.getLocalAmount(),
                entity.getDate(), entity.getOwner(), entity.getProcess().getProcessNo(),
                entity.getState().toString(), entity.getStateReason(), entity.getTopic());

            commandSender.sendCommand(saveCommand);
        }
    }
}
