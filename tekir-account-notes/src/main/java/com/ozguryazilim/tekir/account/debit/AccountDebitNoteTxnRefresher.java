package com.ozguryazilim.tekir.account.debit;

import com.ozguryazilim.tekir.entities.AccountDebitNote;
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
public class AccountDebitNoteTxnRefresher {

    @Inject
    private AccountDebitNoteRepository repository;

    @Inject
    private VoucherGroupTxnRepository voucherGroupTxnRepository;

    @Inject
    private CommandSender commandSender;

    public void refreshVoucherGroupTxns(@Observes RefreshVoucherGroupTxnsEvent event) {
        List<AccountDebitNote> accountDebitNotes = repository
            .findByDateBetween(event.getBeginDate().getCalculatedValue(),
                event.getEndDate().getCalculatedValue());
        String feature = FeatureRegistery.getFeatureClass(AccountDebitNote.class).getSimpleName();
        voucherGroupTxnRepository.deleteByFeature_featureAndDateBetween(feature,
            event.getBeginDate().getCalculatedValue(), event.getEndDate().getCalculatedValue());

        for (AccountDebitNote entity : accountDebitNotes) {
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