package com.ozguryazilim.tekir.contact;

import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
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
public class ContactFeeder extends AbstractFeeder<Contact> {

    @Inject
    private Identity identity;

    public void feed(
        @Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = Contact.class) @After EntityChangeEvent event) {
        if (event.getEntity() instanceof Person) {
            Person entity = (Person) event.getEntity();

            List<FeaturePointer> mentions = new ArrayList<>();

            mentions.add(FeatureUtils.getFeaturePointer(entity));

            Corporation corp = entity.getCorporation();

            if (corp != null) {
                mentions.add(FeatureUtils.getFeaturePointer(corp));
            }

            sendFeed(
                entity.getClass().getSimpleName(),
                getClass().getSimpleName(),
                identity.getLoginName(),
                entity.getName(),
                getMessage(event),
                mentions
            );
        }

        if (event.getEntity() instanceof Corporation) {
            Corporation entity = (Corporation) event.getEntity();

            List<FeaturePointer> mentions = new ArrayList<>();

            mentions.add(FeatureUtils.getFeaturePointer(entity));

            Person cont = entity.getPrimaryContact();
            Corporation parentCorp = entity.getParentCorporation();

            if (cont != null) {
                mentions.add(FeatureUtils.getFeaturePointer(cont));
            }

            if (parentCorp != null) {
                mentions.add(FeatureUtils.getFeaturePointer(parentCorp));
            }

            sendFeed(
                entity.getClass().getSimpleName(),
                getClass().getSimpleName(),
                identity.getLoginName(),
                entity.getName(),
                getMessage(event),
                mentions
            );
        }

    }

    protected String getMessage(EntityChangeEvent event) {
        Contact entity = (Contact) event.getEntity();
        switch (event.getAction()) {
            case INSERT:
                return "feeder.messages.ContactFeeder.INSERT$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            case UPDATE:
                return "feeder.messages.ContactFeeder.UPDATE$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            case DELETE:
                return "feeder.messages.ContactFeeder.DELETE$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            default:
                return "feeder.messages.ContactFeeder.DEFAULT$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
        }
    }
}
