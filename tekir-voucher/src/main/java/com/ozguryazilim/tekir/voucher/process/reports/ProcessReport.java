package com.ozguryazilim.tekir.voucher.process.reports;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.core.query.filter.TagSuggestionService;
import com.ozguryazilim.tekir.core.reports.TekirDynamicReportUtils;
import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.voucher.config.VoucherPages;
import com.ozguryazilim.tekir.voucher.process.ProcessRepository;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messages.Messages;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.reports.DynamicReportBase;
import com.ozguryazilim.telve.reports.Report;
import com.ozguryazilim.telve.reports.ReportDate;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import javax.inject.Inject;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.style.TemplateStylesBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Report(filterPage = VoucherPages.Process.ProcessReport.class, permission = "processReport", path = "/process", template = "processReport")
public class ProcessReport extends DynamicReportBase<ProcessReportFilter> {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessReport.class);

    @Inject
    private ProcessRepository repository;

    @Inject
    private AccountTxnRepository txnRepository;

    private TagSuggestionService suggestionProvider;

    @Override
    protected ProcessReportFilter buildFilter() {
        ProcessReportFilter result = new ProcessReportFilter(
            new ReportDate(DateValueType.FirstDayOfMonth),
            new ReportDate(DateValueType.LastDayOfMonth));

        result.setDate(new ReportDate(DateValueType.Today));

        return result;
    }

    @Override
    protected void buildReport(JasperReportBuilder report, Boolean forExport) {

        TextColumnBuilder<String> processTopic = col.column("topic", type.stringType())
            .setTitle(msg("ProcessReport.Topic")).setWidth(30);
        TextColumnBuilder<String> processNo = col.column("processNo", type.stringType())
            .setTitle(msg("ProcessReport.No")).setWidth(20);
        TextColumnBuilder<String> processAccountName = col.column("account.name", type.stringType())
            .setTitle(msg("ProcessReport.AccountName")).setWidth(30);
        TextColumnBuilder<String> processType = TekirDynamicReportUtils
            .buildProcessTypeColumn("type").setWidth(10);
        TextColumnBuilder<String> processStatus = TekirDynamicReportUtils
            .buildProcessStatusColumn("status").setWidth(10);

        if (getFilter().getDetail()) {
            SubreportBuilder sub = cmp.subreport(new SubreportExpression())
                .setDataSource(exp.subDatasourceBeanCollection("detail"));

            report.detailFooter(
                cmp.horizontalList(cmp.horizontalGap(15), sub));
        }

        report
            .columns(processTopic, processNo, processAccountName, processType, processStatus)
            .highlightDetailEvenRows();
    }

    @Override
    protected JRDataSource getReportDataSource() {
        List<ProcessReportModel> rows = repository.findByFilter(getFilter());

        if (getFilter().getDetail()) {
            for (ProcessReportModel p : rows) {
                List<AccountTxn> txnRows = txnRepository
                    .findByProcessIdAndTagsAndDateBetweenOrderByDate(p.getProcessNo(),
                        getFilter().getTags(), getFilter().getBeginDate().getCalculatedValue(),
                        getFilter().getEndDate().getCalculatedValue());
                p.setDetail(txnRows);
            }
        }

        return new JRBeanCollectionDataSource(rows);
    }

    @Override
    protected Boolean isReportPotrait() {
        if (getFilter().getDetail()) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    @Override
    protected String getReportSubTitle() {
        String pattern = Messages.getMessage("general.format.Date");
        DateFormat df = new SimpleDateFormat(pattern);
        Date dt = getFilter().getDate().getCalculatedValue();
        String date = df.format(dt);

        StringBuilder sb = new StringBuilder();
        sb.append(date).append(" ").append(Messages.getMessage("general.label.AsOf")).append('\n');
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
            sb.append(Messages.getMessage("processReport.filter.AccountOwner")).append(" : ")
                .append(getFilter().getOwner()).append('\n');
        }
        if (getFilter().getTags() != null && getFilter().getDetail()) {
            ListIterator<String> iterator = getFilter().getTags().listIterator();

            if (iterator.hasNext()) {
                sb.append(Messages.getMessage("general.label.Tag")).append(" : ");
            }

            while (iterator.hasNext()) {
                sb.append(iterator.next());
                if (!iterator.hasNext()) {
                    sb.append(".\n");
                } else {
                    sb.append(", ");
                }
            }
        }
        if (getFilter().getDetail() &&
            getFilter().getBeginDate() != null && getFilter().getEndDate() != null) {
            String beginDate = df.format(getFilter().getBeginDate().getCalculatedValue());
            String endDate = df.format(getFilter().getEndDate().getCalculatedValue());
            String result = Messages.getMessageFromData(Messages.getCurrentLocale(),
                "processReport.filter.BetweenDates$%&" + beginDate + "$%&" + endDate);

            sb.append(result).append("\n");
        }

        return sb.toString();
    }

    public List<String> getSuggestions() {
        suggestionProvider = BeanProvider.getContextualReference(TagSuggestionService.class);
        return suggestionProvider.getSuggestions("*");
    }

    private class SubreportExpression extends AbstractSimpleExpression<JasperReportBuilder> {

        @Override
        public JasperReportBuilder evaluate(ReportParameters reportParameters) {
            JasperReportBuilder report = report();

            TextColumnBuilder<Date> txnDate = col
                .column(msg("general.label.Date"), "date", type.dateType()).setWidth(10)
                .setPattern(msg("general.format.Date"))
                .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
            TextColumnBuilder<String> status = TekirDynamicReportUtils.buildStatusColumn("status")
                .setWidth(5);
            TextColumnBuilder<FeaturePointer> feature = TekirDynamicReportUtils
                .buildFeatureColumn("feature").setWidth(25);
            TextColumnBuilder<BigDecimal> amount = col
                .column(msg("general.label.Amount"), "amount", type.bigDecimalType())
                .setWidth(10)
                .setValueFormatter(new AbstractValueFormatter<String, BigDecimal>() {
                    @Override
                    public String format(BigDecimal value, ReportParameters reportParameters) {
                        Currency ccy = reportParameters.getValue("currency");
                        return
                            type.bigDecimalType().valueToString(value, reportParameters.getLocale())
                                + " " + ccy.getCurrencyCode();
                    }
                });
            TextColumnBuilder<BigDecimal> localAmount = col
                .column(msg("general.label.LocalAmount"), "localAmount", type.bigDecimalType())
                .setWidth(10);
            TextColumnBuilder<String> topic = col.column("topic", type.stringType())
                .setTitle(msg("ProcessReport.Topic")).setWidth(25)
                .setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);

            InputStream iss = ProcessReport.class.getResourceAsStream("/" + ConfigResolver
                .getPropertyValue("dynamicreports.styles", "telve.jrtx"));

            TemplateStylesBuilder tsb = stl.loadStyles(iss);

            report
                .templateStyles(tsb)
                .setColumnStyle(stl.templateStyle("Base"))
                .setColumnTitleStyle(stl.templateStyle("ColumnTitle"))
                .columns(txnDate, status, feature, amount, localAmount, topic)
                .fields(
                    DynamicReports.field("currency", Currency.class),
                    DynamicReports.field("feature", FeaturePointer.class))
                .highlightDetailEvenRows();

            return report;
        }
    }
}