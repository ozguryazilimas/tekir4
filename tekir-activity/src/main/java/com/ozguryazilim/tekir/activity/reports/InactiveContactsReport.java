package com.ozguryazilim.tekir.activity.reports;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.activity.ActivityRepository;
import com.ozguryazilim.tekir.activity.config.ActivityPages;
import com.ozguryazilim.tekir.contact.reports.InactiveContactsFilter;
import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.ContactRoleRegistery;
import com.ozguryazilim.tekir.contact.reports.InactiveContactsModel;
import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.Activity;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.tekir.entities.Person;
import com.ozguryazilim.telve.messages.Messages;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.reports.DynamicReportBase;
import com.ozguryazilim.telve.reports.Report;
import com.ozguryazilim.telve.reports.ReportDate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Report(filterPage = ActivityPages.InactiveContactsReport.class, permission = "inactiveContactsReport", path = "/contact", template = "inactiveContactsReport")
public class InactiveContactsReport extends DynamicReportBase<InactiveContactsFilter> {

    private static final Logger LOG = LoggerFactory.getLogger(InactiveContactsFilter.class);

    @Inject
    private ContactRepository repository;

    @Inject
    private AccountTxnRepository txnRepository;

    @Inject
    private ActivityRepository activityRepository;

    @Override
    protected InactiveContactsFilter buildFilter() {
        InactiveContactsFilter result = new InactiveContactsFilter();

        result.setDate(new ReportDate(DateValueType.Today));

        return result;
    }

    @Override
    protected void buildReport(JasperReportBuilder report, Boolean forExport) {

        TextColumnBuilder<String> contactCode = col
            .column("contact.code", type.stringType())
            .setTitle(msg("InactiveContactsReport.Code"));

        TextColumnBuilder<String> contactName = col
            .column("contact.name", type.stringType())
            .setTitle(msg("InactiveContactsReport.Name"))
            .setFixedWidth(cm(9));

        TextColumnBuilder<String> contactOwner = col
            .column("contact.owner", type.stringType())
            .setTitle(msg("InactiveContactsReport.Owner"));

        TextColumnBuilder<String> daysSinceLastMovement = col
            .column("daysSinceLastMovement", type.stringType())
            .setTitle(msg("InactiveContactsReport.DaysSinceLastMovement"));

        report
            .addColumn(contactCode, contactName, contactOwner, daysSinceLastMovement)
            .highlightDetailEvenRows();
    }

    @Override
    protected JRDataSource getReportDataSource() {
        List<InactiveContactsModel> rows = repository.findByInactiveContactsFilter(getFilter());

        List<InactiveContactsModel> results = new ArrayList<>();
        for (InactiveContactsModel icm : rows) {
            Date lastMovement = null;

            List<AccountTxn> txns = txnRepository.findByAccountOrderByDateDesc(icm.getContact());

            if (!txns.isEmpty()) {
                lastMovement = txns.get(0).getDate();
            }

            if (getFilter().getIncludeActivity()) {
                if (icm.getContact() instanceof Person) {
                    Person entity = (Person) icm.getContact();

                    List<Activity> activities = activityRepository
                        .findByPersonOrderByDueDateDesc(entity);

                    if (!activities.isEmpty()) {
                        if (lastMovement == null ||
                            activities.get(0).getDueDate().after(lastMovement)) {
                            lastMovement = activities.get(0).getDueDate();
                        }
                    }
                } else if (icm.getContact() instanceof Corporation) {
                    Corporation entity = (Corporation) icm.getContact();

                    List<Activity> activities = activityRepository
                        .findByCorporationOrderByDueDateDesc(entity);

                    if (!activities.isEmpty()) {
                        if (lastMovement == null ||
                            activities.get(0).getDueDate().after(lastMovement)) {
                            lastMovement = activities.get(0).getDueDate();
                        }
                    }
                }
            }

            Date referenceDate = LocalDate.now().minusDays(getFilter().getDayInterval()).toDate();
            if (lastMovement == null || referenceDate.after(lastMovement)) {
                String daysSinceLastMovement = lastMovement == null ?
                    Messages.getMessage("InactiveContactsReport.NoMovement") :
                    String.valueOf(TimeUnit.DAYS.convert(
                        LocalDate.now().toDate().getTime() -
                            lastMovement.getTime(), TimeUnit.MILLISECONDS));

                icm.setDaysSinceLastMovement(daysSinceLastMovement);
                results.add(icm);
            }
        }

        return new JRBeanCollectionDataSource(results);
    }

    @Override
    protected String getReportSubTitle() {
        String pattern = Messages.getMessage("general.format.Date");
        DateFormat df = new SimpleDateFormat(pattern);
        Date dt = getFilter().getDate().getCalculatedValue();
        String date = df.format(dt);

        StringBuilder sb = new StringBuilder();

        sb.append(date).append(" ").append(Messages.getMessage("general.label.AsOf")).append('\n');

        sb.append(Messages.getMessage("InactiveContactsReport.label.DayInterval"))
            .append(" : ")
            .append(getFilter().getDayInterval()).append("\n");

        if (!Strings.isNullOrEmpty(getFilter().getCode())) {
            sb.append(Messages.getMessage("general.label.Code")).append(" : ")
                .append(getFilter().getCode()).append('\n');
        }

        if (!Strings.isNullOrEmpty(getFilter().getName())) {
            sb.append(Messages.getMessage("general.label.Name")).append(" : ")
                .append(getFilter().getName()).append('\n');
        }

        if (getFilter().getContactCategory() != null) {
            sb.append(Messages.getMessage("general.label.Category")).append(" : ")
                .append(getFilter().getContactCategory().getName())
                .append('\n');
        }

        if (getFilter().getCorporationType() != null) {
            sb.append(Messages.getMessage("contact.label.CorporationType")).append(" : ")
                .append(getFilter().getCorporationType().getName())
                .append('\n');
        }

        if (getFilter().getIndustry() != null) {
            sb.append(Messages.getMessage("general.label.Industry")).append(" : ")
                .append(getFilter().getIndustry().getName()).append('\n');
        }

        if (getFilter().getTerritory() != null) {
            sb.append(Messages.getMessage("general.label.Territory")).append(" : ")
                .append(getFilter().getTerritory().getName()).append('\n');
        }

        if (getFilter().getOwner() != null) {
            sb.append(Messages.getMessage("general.label.Owner")).append(" : ")
                .append(getFilter().getOwner()).append('\n');
        }

        if (getFilter().getRoles() != null) {
            ListIterator<String> iterator = getFilter().getRoles().listIterator();

            if (iterator.hasNext()) {
                sb.append(Messages.getMessage("contact.label.ContactRoles")).append(" : ");
            }

            while (iterator.hasNext()) {
                sb.append(Messages.getMessage("contact.role." + iterator.next()));
                if (!iterator.hasNext()) {
                    sb.append(".\n");
                } else {
                    sb.append(", ");
                }
            }
        }

        if (getFilter().getIncludeActivity()) {
            sb.append(Messages.getMessage("InactiveContactsReport.subtitle.ActivitiesIncluded"));
        }

        return sb.toString();
    }

    public List<String> getRoles() {
        return ContactRoleRegistery.getFilterableContactRoles();
    }
}