/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.reports;

import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.reports.DynamicReportBase;
import com.ozguryazilim.telve.reports.Report;
import com.ozguryazilim.telve.reports.ReportDate;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import javax.inject.Inject;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.DynamicReports;
import static net.sf.dynamicreports.report.builder.DynamicReports.cm;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.sbt;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author oyas
 */
@Report(filterPage = ContactPages.AccountStatementReport.class, permission = "accountStatementReport", path = "/account")
public class AccountStatementReport extends DynamicReportBase<AccountStatementFilter>{

    
    @Inject
    private AccountTxnRepository repository;

    @Override
    protected void buildReport(JasperReportBuilder report, Boolean forExport) {
        TextColumnBuilder<String> info = col.column( msg("general.label.Info"), "info", type.stringType());
        TextColumnBuilder<String> feature = col.column( msg("general.label.Feature"), "feature.feature", type.stringType())
                .setValueFormatter(new AbstractValueFormatter<String, String>() {
                    @Override
                    public String format(String value, ReportParameters reportParameters) {
                        return msg("feature.caption." + value);
                    }
                });
        
        TextColumnBuilder<String> featureBk = col.column( msg("general.label.Feature"), "feature.businessKey", type.stringType());
        TextColumnBuilder<String> referenceNo = col.column( msg("general.label.Reference"), "referenceNo", type.stringType());
        TextColumnBuilder<String> status = col.column( msg("general.label.Status"), "status", type.stringType());
        TextColumnBuilder<Date> txnDate = col.column( msg("general.label.Date"), "date", type.dateType()).setFixedWidth(cm(3));
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
                                                        return value ? "D" : "C"; //i18n olacak.
                                                    }
                                                });
        //BooleanColumnBuilder debit = col.booleanColumn(msg("general.label.DebitCredit"), "debit").setComponentType(BooleanComponentType.IMAGE_CHECKBOX_1);
        
        
        //TextColumnBuilder<String> ccy = col.column( "ccy.currencyCode", type.stringType())
        //                                           .setTitle(msg("general.label.CCY"));
        
        
        report
                .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
                .fields(
                        DynamicReports.field("currency", Currency.class)
                )
                .columns(txnDate, status, feature, featureBk, referenceNo, info, debit, amount,  localAmount )
                ;
        
        if( !forExport ){
              report.subtotalsAtSummary(
                      sbt.text(msg("general.label.Total"), amount), 
                      sbt.sum(localAmount)
                      );
        }
    }

    @Override
    protected String getReportSubTitle() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFilter().getContact().getName())
                .append(" ( ")
            .append(getFilter().getBeginDate().getCalculatedValue())
                .append(" - ")
            .append(getFilter().getEndDate().getCalculatedValue())
                .append(" )");
        return sb.toString();
    }

    
    
    @Override
    protected JRDataSource getReportDataSource() {
        return new JRBeanCollectionDataSource(repository.findOpenTxnsByAccount(getFilter().getContact()));
    }

    @Override
    protected AccountStatementFilter buildFilter() {
        return new AccountStatementFilter(new ReportDate(DateValueType.FirstDayOfMonth), new ReportDate(DateValueType.LastDayOfMonth));
    }
    
}
