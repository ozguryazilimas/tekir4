package com.ozguryazilim.finance.account.reports;

import com.google.common.base.Strings;
import com.ozguryazilim.finance.account.txn.FinanceAccountTxnRepository;
import com.ozguryazilim.finance.config.FinancePages;
import com.ozguryazilim.tekir.entities.FinanceAccountTxn;
import com.ozguryazilim.telve.messages.Messages;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import com.ozguryazilim.tekir.core.reports.TekirDynamicReportUtils;
import com.ozguryazilim.telve.config.LocaleSelector;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.reports.DynamicReportBase;
import com.ozguryazilim.telve.reports.Report;
import com.ozguryazilim.telve.reports.ReportDate;
import com.ozguryazilim.telve.utils.DateUtils;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import static net.sf.dynamicreports.report.builder.DynamicReports.cm;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.sbt;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

/**
 * Cari Durumlar için standart rapor
 *
 */
@Report(filterPage = FinancePages.FinanceAccountTxnReport.class, permission = "financeAccountTxnReport", path = "/finance/account", template = "financeAccountTxnReport")
public class FinanceAccountTxnReport extends DynamicReportBase<FinanceAccountTxnFilter> {

    @Inject
    private FinanceAccountTxnRepository repository;

    @Override
    protected Boolean isReportPotrait() {
        return Boolean.FALSE;
    }

    private BigDecimal balance = new BigDecimal(0);

    @Override
    protected void buildReport(JasperReportBuilder report, Boolean forExport) {
        TextColumnBuilder<String> contactName = col.column( msg("finance" +
            ".label.Account"), "contact.name", type.stringType())
                .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
        TextColumnBuilder<FeaturePointer> feature = TekirDynamicReportUtils.buildFeatureColumn("feature");

        TextColumnBuilder<String> referenceNo = col.column( msg("general.label.Reference"), "referenceNo", type.stringType());
        TextColumnBuilder<String> status = TekirDynamicReportUtils
                .buildStatusColumn("status").setHorizontalTextAlignment
                        (HorizontalTextAlignment.CENTER);
        TextColumnBuilder<Date> txnDate = col.column( msg("general.label.Date"), "date", type.dateType()).setFixedWidth(cm(3))
                .setPattern(msg("general.format.Date")).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
        TextColumnBuilder<BigDecimal> amount = col.column( msg("general.label.Amount"), "amount", type.bigDecimalType()).setFixedWidth(cm(4))
                .setValueFormatter(new AbstractValueFormatter<String, BigDecimal>() {
                    @Override
                    public String format(BigDecimal value, ReportParameters reportParameters) {
                        Currency ccy = reportParameters.getValue("currency");
                        return type.bigDecimalType().valueToString(value, reportParameters.getLocale()) + " " + ccy.getCurrencyCode();
                    }
                });
        TextColumnBuilder<BigDecimal> localAmount = col.column( msg("general.label.LocalAmount"), "localAmount", type.bigDecimalType()).setFixedWidth(cm(3));

        TextColumnBuilder<Boolean> debit = col.column( "debit", type.booleanType())
                .setTitle(msg("general.label.DebitCredit"))
                .setValueFormatter(new AbstractValueFormatter<String, Boolean>() {
                    @Override
                    public String format(Boolean value, ReportParameters reportParameters) {
                        return value ? msg("finance.label.DebitInitial") :
                                msg("finance.label.CreditInitial");
                    }
                });

        Locale locale = LocaleSelector.instance().getLocale();
        BigDecimal takeOver = repository.findByAccountBalance(getFilter()
                        .getFinanceAccount(),
                getFilter().getStartDate().getCalculatedValue());

        if (takeOver != null) {
            balance = takeOver;
        }

        HorizontalListBuilder devir = cmp.horizontalList()
                .add(cmp.text(msg("finance.label.TakeOver")))
                .add(cmp.text(type.bigDecimalType().valueToString(balance, locale)).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT));

        report
            .fields(
                DynamicReports.field("currency", Currency.class),
                DynamicReports.field("feature", FeaturePointer.class))
            .columns(txnDate, status, feature, referenceNo, contactName, debit,
                amount, localAmount)
            .columnHeader(cmp.verticalGap(4), devir)
            .highlightDetailEvenRows();

        if( !forExport ){
            report.subtotalsAtSummary(
                    sbt.text(msg("general.label.Total"), amount),
                    sbt.sum(localAmount).setValueFormatter(
                            new AbstractValueFormatter<BigDecimal, BigDecimal>()
                            {
                                @Override public BigDecimal format
                                        (BigDecimal value,
                                         ReportParameters reportParameters) {
                                    return value.add(balance);
                                }
                            }));

        }
    }

    @Override
    protected String getReportSubTitle() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFilter().getFinanceAccount().getName())
            .append(" ( ")
            .append(DateUtils.dateToStr(getFilter().getStartDate().getCalculatedValue()))
            .append(" - ")
            .append(DateUtils.dateToStr(getFilter().getEndDate().getCalculatedValue()))
            .append(" )\n");
        if (!Strings.isNullOrEmpty(getFilter().getCode())) {
            sb.append(Messages.getMessage("general.label.Code")).append(" : ")
                .append(getFilter().getCode()).append('\n');
        }
        if (getFilter().getAccount() != null) {
            sb.append(msg("general.label.Account"))
                .append(getFilter().getAccount().getName()).append("\n");

        }

        return sb.toString();
    }



    @Override
    protected JRDataSource getReportDataSource() {
        List<FinanceAccountTxn> rows = repository.findAccountTransactions(getFilter());
        return new JRBeanCollectionDataSource(rows);
    }

    @Override
    protected FinanceAccountTxnFilter buildFilter() {
        return new FinanceAccountTxnFilter(new ReportDate(DateValueType.FirstDayOfMonth), new
                ReportDate(DateValueType.LastDayOfMonth));
    }
}
