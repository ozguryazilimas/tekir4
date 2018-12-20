package com.ozguryazilim.tekir.account.reports;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.account.AccountTxnRepository;
import com.ozguryazilim.tekir.account.AccountTxnStatusModel;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.telve.messages.Messages;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.reports.DynamicReportBase;
import com.ozguryazilim.telve.reports.Report;
import com.ozguryazilim.telve.reports.ReportDate;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author oyas
 */
@Report(filterPage = ContactPages.AccountStatusDynaReport.class, permission = "accountStatusDynaReport", path = "/account/status", template = "accountStatusReport")
public class AccountStatusDynaReport extends DynamicReportBase<AccountStatusFilter> {

    private static final Logger LOG = LoggerFactory.getLogger(AccountStatusDynaReport.class);

    @Inject
    private AccountTxnRepository repository;

    @Inject
    private CurrencyService currencyService;

    @Override
    public AccountStatusFilter buildFilter() {
        AccountStatusFilter result = new AccountStatusFilter();

        result.setDate(new ReportDate(DateValueType.Today));
        
        return result;
    }

    @Override
    protected void buildReport(JasperReportBuilder report, Boolean forExport) {
        
        TextColumnBuilder<String> accountName = col.column( "accountName", type.stringType())
                                                   .setTitle(msg("AccountTxnStatusReport.Name"));
        TextColumnBuilder<BigDecimal> debit = col.column( "debit", type.bigDecimalType())
                                                  .setTitle(msg("AccountTxnStatusReport.Debit"))
                                                  .setFixedWidth(cm(3));
        TextColumnBuilder<BigDecimal> credit = col.column( "credit", type.bigDecimalType())
                                                  .setTitle(msg("AccountTxnStatusReport.Credit"))
                                                  .setFixedWidth(cm(3));
        TextColumnBuilder<BigDecimal> balance = col.column( "balance", type.bigDecimalType())
                                                  .setTitle(msg("AccountTxnStatusReport.Balance"))
                                                  .setFixedWidth(cm(3));
        
        report.columns( accountName, debit, credit, balance );
        
        if( !forExport ){
              report.subtotalsAtSummary(
                      sbt.text(Messages.getMessage("general.label.Total"), accountName), 
                      sbt.sum(debit),
                      sbt.sum(credit),
                      sbt.sum(balance)
                      );
        }
        
    }

    @Override
    protected String getReportSubTitle() {
        Date dt = getFilter().getDate().getCalculatedValue();
        //FIXME: Burada tarih formatlanmalı. Msajlar i18n
        
        StringBuilder sb = new StringBuilder();
        sb.append(dt).append(' ').append(" itibari ile").append('\n');
        if( !Strings.isNullOrEmpty(getFilter().getCode())){
            sb.append("Kod : ").append(getFilter().getCode()).append('\n');
        }
        if( !Strings.isNullOrEmpty(getFilter().getName())){
            sb.append("Ad : ").append(getFilter().getName()).append('\n');
        }
        if( getFilter().getContactCategory() != null){
            sb.append("Kategori : ").append(getFilter().getContactCategory().getName()).append('\n');
        }
        if( getFilter().getCorporationType()!= null){
            sb.append("Kurum Tipi : ").append(getFilter().getCorporationType().getName()).append('\n');
        }
        if( getFilter().getIndustry()!= null){
            sb.append("Sektör : ").append(getFilter().getIndustry().getName()).append('\n');
        }
        if( getFilter().getTerritory()!= null){
            sb.append("Bölge : ").append(getFilter().getTerritory().getName()).append('\n');
        }
        
        return sb.toString();
    }

    
    
    @Override
    protected JRDataSource getReportDataSource() {
        List<AccountTxnStatusModel> rows = repository.findAccountStatus(getFilter());
        return new JRBeanCollectionDataSource(rows);
    }

}
