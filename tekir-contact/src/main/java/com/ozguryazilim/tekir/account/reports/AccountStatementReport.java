/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.reports;

import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.core.reports.TekirDynamicReportUtils;
import com.ozguryazilim.tekir.entities.AccountTxnState;
import com.ozguryazilim.telve.config.LocaleSelector;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.feature.FeatureCategory;
import com.ozguryazilim.telve.feature.search.FeatureCategoryLookup;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.reports.DynamicReportBase;
import com.ozguryazilim.telve.reports.DynamicSubreportBase;
import com.ozguryazilim.telve.reports.Report;
import com.ozguryazilim.telve.reports.ReportDate;
import com.ozguryazilim.telve.reports.SubreportRegistery;
import com.ozguryazilim.telve.utils.DateUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.DynamicReports;
import static net.sf.dynamicreports.report.builder.DynamicReports.cm;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.sbt;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
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
    protected Boolean isReportPotrait() {
        return Boolean.FALSE;
    }

    private BigDecimal balance = new BigDecimal(0);

    @Override
    protected void buildReport(JasperReportBuilder report, Boolean forExport) {
        TextColumnBuilder<String> info = col.column( msg("general.label.Info"), "info", type.stringType());
        TextColumnBuilder<FeaturePointer> feature = TekirDynamicReportUtils.buildFeatureColumn("feature");
        
        TextColumnBuilder<String> referenceNo = col.column( msg("general.label.Reference"), "referenceNo", type.stringType());
        TextColumnBuilder<String> status = TekirDynamicReportUtils.buildStatusColumn("status");
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
        TextColumnBuilder<BigDecimal> localAmount = col.column( msg("general.label.LocalAmount"),
                                      "localAmount", type.bigDecimalType())
                                                .setFixedWidth(cm(3));
        
        TextColumnBuilder<Boolean> debit = col.column( "debit", type.booleanType())
                                                .setTitle(msg("general.label.DebitCredit"))
                                                .setValueFormatter(new AbstractValueFormatter<String, Boolean>() {
                                                     @Override
                                                     public String format(Boolean value, ReportParameters reportParameters) {
                                                         return value ? msg("contact.label.DebitInitial") :
                                                                 msg("contact.label.CreditInitial");

                                                     }
                                                });
        //BooleanColumnBuilder debit = col.booleanColumn(msg("general.label.DebitCredit"), "debit").setComponentType(BooleanComponentType.IMAGE_CHECKBOX_1);
        
        
        //TextColumnBuilder<String> ccy = col.column( "ccy.currencyCode", type.stringType())
        //                                           .setTitle(msg("general.label.CCY"));
        
        
        
        SubreportBuilder sub = cmp.subreport(new SubreportExpresstion())
                .setDataSource(new SubreportDataSource());
        
        
        Locale locale = LocaleSelector.instance().getLocale();
        BigDecimal takeOver = repository.findByAccountBalance(getFilter().getContact(), getFilter().getBeginDate().getCalculatedValue());

        if (takeOver != null) {
            balance = takeOver;
        }
        
        HorizontalListBuilder devir = cmp.horizontalList()
	            .add(cmp.text(msg("general.label.TakeOver")))
                    .add(cmp.text(type.bigDecimalType().valueToString(balance, locale)).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT));

        report
                //.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
                .fields(
                        DynamicReports.field("currency", Currency.class),
                        DynamicReports.field("feature", FeaturePointer.class)
                )
                .detailFooter(sub).highlightDetailEvenRows()
                .columnHeader(cmp.verticalGap(4), devir);
                //.setColumnHeaderStyle(stl.templateStyle("Bold"))
                ;

        if (getFilter().getWithDetail()) {
                            report.columns(txnDate, status, feature,
                                    referenceNo, info, debit, amount,
                                    localAmount );
        } else {
            report.columns(txnDate, feature,
                    debit, amount,  localAmount );
        }

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

    public List<String> getCategories() {
        return new FeatureCategoryLookup().getSearchableFeaturesByCategory
                (FeatureCategory.ACCOUNTABLE);

    }

    public List<String> getTxnStates() {
        List<String> stateList = new ArrayList<String>();
        for (AccountTxnState ats : AccountTxnState.values())
            stateList.add(ats.toString());

        return stateList;
    }

    @Override
    protected String getReportSubTitle() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFilter().getContact().getName())
                .append(" ( ")
            .append(DateUtils.dateToStr(getFilter().getBeginDate().getCalculatedValue()))
                .append(" - ")
            .append(DateUtils.dateToStr(getFilter().getEndDate().getCalculatedValue()))
                .append(" )");
        return sb.toString();
    }

    
    
    @Override
    protected JRDataSource getReportDataSource() {
        return new JRBeanCollectionDataSource(repository
                .findAccountByStatusAndFeature(getFilter()));
    }

    @Override
    protected AccountStatementFilter buildFilter() {
        return new AccountStatementFilter(new ReportDate(DateValueType.FirstDayOfMonth), new ReportDate(DateValueType.LastDayOfMonth));
    }


    private class SubreportExpresstion extends AbstractSimpleExpression<JasperReportBuilder>{

        @Override
        public JasperReportBuilder evaluate(ReportParameters reportParameters) {
            FeaturePointer feature = reportParameters.getValue("feature");
            
            DynamicSubreportBase rpc = (DynamicSubreportBase) SubreportRegistery.getReport("accountStatement", feature.getFeature());
            if( rpc == null ){
                //Boş bir sub report dönüyoruz.
                return report();
            }
            return rpc.getSubreport(reportParameters);
            
        }
        
    }

    private class SubreportDataSource extends AbstractSimpleExpression<JRDataSource>{

        @Override
        public JRDataSource evaluate(ReportParameters reportParameters) {
            FeaturePointer feature = reportParameters.getValue("feature");
            
            DynamicSubreportBase rpc = (DynamicSubreportBase) SubreportRegistery.getReport("accountStatement", feature.getFeature());
            if( rpc == null ){
                //Boş bir sub report dönüyoruz.
                return new JREmptyDataSource(0);
            }
            return rpc.getDataSource(reportParameters);
        }
        
    }
}
