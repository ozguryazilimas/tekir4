package com.ozguryazilim.finance.account;

import com.ozguryazilim.tekir.entities.FinanceAccount;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureUtils;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

/**
 * @author Soner Cirit
 */
@Feeder
public class FinanceAccountFeeder extends AbstractFeeder<FinanceAccount> {

    @Inject
    private Identity identity;

    public void feed(
        @Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = FinanceAccount.class) @After EntityChangeEvent event) {
        FinanceAccount entity = (FinanceAccount) event.getEntity();

        List<FeaturePointer> mentions = new ArrayList<>();

        mentions.add(FeatureUtils.getFeaturePointer(entity));

        sendFeed(
            entity.getType().toString(),
            getClass().getSimpleName(),
            identity.getLoginName(),
            entity.getName(),
            getMessage(event),
            mentions
        );
    }

    protected String getMessage(EntityChangeEvent event) {
        FinanceAccount entity = (FinanceAccount) event.getEntity();
        switch (event.getAction()) {
            case INSERT:
                return "feeder.messages.FinanceAccountFeeder.INSERT$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            case UPDATE:
                return "feeder.messages.FinanceAccountFeeder.UPDATE$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            case DELETE:
                return "feeder.messages.FinanceAccountFeeder.DELETE$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            default:
                return "feeder.messages.FinanceAccountFeeder.DEFAULT$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
        }
    }
}