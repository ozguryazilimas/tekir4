package com.ozguryazilim.tekir.activity;

import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.feed.AbstractFeeder;
import com.ozguryazilim.tekir.feed.Feeder;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureRegistery;
import com.ozguryazilim.telve.forms.EntityChangeEvent;
import com.ozguryazilim.telve.qualifiers.After;
import com.ozguryazilim.telve.qualifiers.EntityQualifier;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

/**
 *
 * @author Hakan Uygun
 */
@Feeder
public class ActivityFeeder extends AbstractFeeder<Activity> {

    @Inject
    private Identity identity;

    public void feed(@Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = Activity.class) @After EntityChangeEvent event) {
        Activity entity = (Activity) event.getEntity();
        
        List<FeaturePointer> mentions = new ArrayList<>();

        if (entity.getCorporation() != null) {
            FeaturePointer cp = new FeaturePointer();
            cp.setBusinessKey(entity.getCorporation().getName());
            cp.setPrimaryKey(entity.getCorporation().getId());
            cp.setFeature(FeatureRegistery.getFeatureClass(entity.getCorporation().getClass()).getSimpleName());
            mentions.add(cp);
        }

        if (entity.getPerson() != null) {
            FeaturePointer pp = new FeaturePointer();
            pp.setBusinessKey(entity.getPerson().getName());
            pp.setPrimaryKey(entity.getPerson().getId());
            pp.setFeature(FeatureRegistery.getFeatureClass(entity.getPerson().getClass()).getSimpleName());
            mentions.add(pp);
        }

        if (entity.getRegarding() != null) {
            mentions.add(entity.getRegarding());
        }

        //Activity'nin kendisini de katalım. Netekim activity detayını da görme ihtiyacı olacaktır.
        FeaturePointer fp = new FeaturePointer();
        fp.setBusinessKey(entity.getSubject());
        fp.setPrimaryKey(entity.getId());
        fp.setFeature(ActivityFeature.class.getSimpleName());
        mentions.add(fp);

        //TODO: Hatta planlanan bir görüşme ise User'ı da mentiona katmak lazım
        //TODO: Aslında mesajları activity status'e göre düzenlemek lazım. Mesela başarız görüşme. Şu kişi için görüşme planlaması v.b.
        //FIXME: Burada activity body'si yerine aslında daha doğru bir metin feed etmek lazım. Ne oldu? Ne yapıyoruz gibi.
        sendFeed(entity.getDirection().name(), 
                getClass().getSimpleName() + "." + entity.getClass().getSimpleName(), 
                identity.getLoginName(),
                entity.getSubject(),
                getMessage(event),
                mentions);

    }

    protected String getMessage(EntityChangeEvent event) {
        Activity entity = (Activity) event.getEntity();
        switch (event.getAction()) {
            case INSERT:
                return "feeder.messages.ActivityFeeder.INSERT$%&" + identity.getUserName() + "$%&"
                    + entity.getActivityNo();
            case UPDATE:
                return "feeder.messages.ActivityFeeder.UPDATE$%&" + identity.getUserName() + "$%&"
                    + entity.getActivityNo();
            case DELETE:
                return "feeder.messages.ActivityFeeder.DELETE$%&" + identity.getUserName() + "$%&"
                    + entity.getActivityNo();
            default:
                return "feeder.messages.ActivityFeeder.DEFAULT$%&" + identity.getUserName() + "$%&"
                    + entity.getActivityNo();
        }
    }
}
