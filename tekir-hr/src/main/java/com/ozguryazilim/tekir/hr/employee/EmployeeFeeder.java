package com.ozguryazilim.tekir.hr.employee;

import com.ozguryazilim.tekir.entities.Employee;
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
public class EmployeeFeeder extends AbstractFeeder<Employee> {

    @Inject
    private Identity identity;

    public void feed(
        @Observes(during = TransactionPhase.IN_PROGRESS) @EntityQualifier(entity = Employee.class) @After EntityChangeEvent event) {
        if (event.getEntity() instanceof Employee) {
            Employee entity = (Employee) event.getEntity();

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
    }

    protected String getMessage(EntityChangeEvent event) {
        Employee entity = (Employee) event.getEntity();
        switch (event.getAction()) {
            case INSERT:
                return "feeder.messages.EmployeeFeeder.INSERT$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            case UPDATE:
                return "feeder.messages.EmployeeFeeder.UPDATE$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            case DELETE:
                return "feeder.messages.EmployeeFeeder.DELETE$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
            default:
                return "feeder.messages.EmployeeFeeder.DEFAULT$%&" + identity.getUserName() + "$%&"
                    + entity.getCode();
        }
    }
}
