/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.quote.reports;

import com.ozguryazilim.tekir.quote.QuoteFeature;
import com.ozguryazilim.tekir.quote.QuoteItemViewModel;
import com.ozguryazilim.tekir.quote.QuoteRepository;
import com.ozguryazilim.tekir.voucher.reports.VocuherDynamicReportUtils;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.reports.DynamicSubreportBase;
import com.ozguryazilim.telve.reports.Subreport;
import java.util.List;
import javax.inject.Inject;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * AccountStatement için Quote detay subreport'u
 *
 * @author Hakan Uygun
 */
@Subreport(group = "accountStatement", feature = QuoteFeature.class)
public class AccountStatementQuoteSubReport extends DynamicSubreportBase {

    @Inject
    private QuoteRepository repository;

    @Override
    public JasperReportBuilder getSubreport(ReportParameters reportParameters) {
        return VocuherDynamicReportUtils.getSubreport(reportParameters);
    }

    @Override
    public JRDataSource getDataSource(ReportParameters reportParameters) {
        //TODO: NPE kontrolü
        FeaturePointer feature = reportParameters.getValue("feature");

        List<QuoteItemViewModel> items = repository.findQuoteItems(feature.getPrimaryKey());

        return new JRBeanCollectionDataSource(items);

    }

}
