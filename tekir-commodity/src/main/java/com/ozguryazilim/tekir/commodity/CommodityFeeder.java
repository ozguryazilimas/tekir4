package com.ozguryazilim.tekir.commodity;

import com.ozguryazilim.tekir.entities.Commodity;
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
public class CommodityFeeder extends AbstractFeeder<Commodity> {

    @Inject
    private Identity identity;

    public void feed(
        @Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = Commodity.class) @After EntityChangeEvent event) {
        Commodity entity = (Commodity) event.getEntity();

        List<FeaturePointer> mentions = new ArrayList<>();

        mentions.add(FeatureUtils.getFeaturePointer(entity));

        sendFeed(
            entity.getClass().getSimpleName(),
            getClass().getSimpleName(),
            identity.getLoginName(),
            entity.getName(),
            getMessage(event),
            mentions
        );
    }

    protected String getMessage(EntityChangeEvent event) {
        Commodity entity = (Commodity) event.getEntity();
        switch (event.getAction()) {
            case INSERT:
                return "feeder.messages.CommodityFeeder.INSERT$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            case UPDATE:
                return "feeder.messages.CommodityFeeder.UPDATE$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            case DELETE:
                return "feeder.messages.CommodityFeeder.DELETE$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            default:
                return "feeder.messages.CommodityFeeder.DEFAULT$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
        }
    }
}