package com.ozguryazilim.tekir.voucher.group;

import com.ozguryazilim.tekir.entities.VoucherGroup;
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
public class VoucherGroupFeeder extends AbstractFeeder<VoucherGroup> {

    @Inject
    private Identity identity;

    public void feed(
        @Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = VoucherGroup.class) @After EntityChangeEvent event) {
        VoucherGroup entity = (VoucherGroup) event.getEntity();

        List<FeaturePointer> mentions = new ArrayList<>();

        mentions.add(FeatureUtils.getFeaturePointer(entity));

        sendFeed(
            entity.getStatus().toString(),
            getClass().getSimpleName(),
            identity.getLoginName(),
            entity.getTopic(),
            getMessage(event),
            mentions
        );
    }

    protected String getMessage(EntityChangeEvent event) {
        VoucherGroup entity = (VoucherGroup) event.getEntity();
        switch (event.getAction()) {
            case INSERT:
                return "feeder.messages.VoucherGroupFeeder.INSERT$%&" + identity.getUserName() + "$%&"
                    + entity.getGroupNo();
            case UPDATE:
                return "feeder.messages.VoucherGroupFeeder.UPDATE$%&" + identity.getUserName() + "$%&"
                    + entity.getGroupNo();
            case DELETE:
                return "feeder.messages.VoucherGroupFeeder.DELETE$%&" + identity.getUserName() + "$%&"
                    + entity.getGroupNo();
            default:
                return "feeder.messages.VoucherGroupFeeder.DEFAULT$%&" + identity.getUserName() + "$%&"
                    + entity.getGroupNo();
        }
    }
}