package com.ozguryazilim.tekir.contact.reports;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.contact.ContactListModel;
import com.ozguryazilim.tekir.contact.ContactRepository;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.reports.DynamicReportBase;
import com.ozguryazilim.telve.reports.Report;
import com.ozguryazilim.telve.reports.ReportDate;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Report(filterPage = ContactPages.ContactListDynaReport.class, permission = "contactListReport", path = "/contact/list", template = "contactListDynaReport")
public class ContactListDynaReport extends DynamicReportBase<ContactListFilter> {

    private static final Logger LOG = LoggerFactory.getLogger(ContactListDynaReport.class);

    @Inject
    private ContactRepository repository;

    @Override
    protected ContactListFilter buildFilter() {
        ContactListFilter result = new ContactListFilter();

        result.setDate(new ReportDate(DateValueType.Today));

        return result;
    }

    @Override
    protected void buildReport(JasperReportBuilder report, Boolean forExport) {
        TextColumnBuilder<String> contactCode = col.column("contactCode", type.stringType())
            .setTitle(msg("AccountTxnReport.Code"));
        TextColumnBuilder<String> contactName = col.column("contactName", type.stringType())
            .setTitle(msg("AccountTxnReport.Name"));
        TextColumnBuilder<String> contactInfo = col.column("contactInfo", type.stringType())
            .setTitle(msg("AccountTxnReport.Info"));

        report.columns(contactCode, contactName, contactInfo);
    }

    @Override
    protected String getReportSubTitle() {
        Date dt = getFilter().getDate().getCalculatedValue();
        //FIXME: Burada tarih formatlanmalı. Msajlar i18n

        StringBuilder sb = new StringBuilder();
        sb.append(dt).append(' ').append(" itibari ile").append('\n');
        if (!Strings.isNullOrEmpty(getFilter().getCode())) {
            sb.append("Kod : ").append(getFilter().getCode()).append('\n');
        }
        if (!Strings.isNullOrEmpty(getFilter().getName())) {
            sb.append("Ad : ").append(getFilter().getName()).append('\n');
        }
        if (getFilter().getContactCategory() != null) {
            sb.append("Kategori : ").append(getFilter().getContactCategory().getName())
                .append('\n');
        }
        if (getFilter().getCorporationType() != null) {
            sb.append("Kurum Tipi : ").append(getFilter().getCorporationType().getName())
                .append('\n');
        }
        if (getFilter().getIndustry() != null) {
            sb.append("Sektör : ").append(getFilter().getIndustry().getName()).append('\n');
        }
        if (getFilter().getTerritory() != null) {
            sb.append("Bölge : ").append(getFilter().getTerritory().getName()).append('\n');
        }
        if (getFilter().getOwner() != null) {
            sb.append("Belge sahibi : ").append(getFilter().getOwner()).append('\n');
        }

        return sb.toString();
    }

    @Override
    protected JRDataSource getReportDataSource() {
        List<ContactListModel> rows = repository.findContacts(getFilter());
        return new JRBeanCollectionDataSource(rows);
    }
}
