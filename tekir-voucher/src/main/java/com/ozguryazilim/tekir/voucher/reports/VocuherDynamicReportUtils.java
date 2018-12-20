package com.ozguryazilim.tekir.voucher.reports;

import com.ozguryazilim.tekir.entities.Quantity;
import com.ozguryazilim.telve.messages.Messages;
import java.io.InputStream;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.TemplateStylesBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;
import org.apache.deltaspike.core.api.config.ConfigResolver;

/**
 * Voucher tipi fişler için Dynamic Report destek fonksiyonları.
 *
 * @author Hakan Uygun
 */
public class VocuherDynamicReportUtils {

    protected VocuherDynamicReportUtils() {
    }

    public static JasperReportBuilder getSubreport(ReportParameters reportParameters) {
        
        //Template'lerin isimleri configden alınıyor. Bu sayede uygulamalar override edebilir. Varsayılan değerler bu proje içerisinde.
        
         
        InputStream iss = VocuherDynamicReportUtils.class.getResourceAsStream("/" + ConfigResolver.getPropertyValue("dynamicreports.styles", "telve.jrtx"));
        
        TemplateStylesBuilder tsb = stl.loadStyles(iss);
        
        JasperReportBuilder report = report();
        report
                .templateStyles(tsb)
                .setColumnStyle(stl.templateStyle("Base"))
                .setColumnTitleStyle(stl.templateStyle("ColumnTitle"))
                .addColumn(col.column(Messages.getMessage("widget.label.Commodity"), "commodity.name", type.stringType()))
                .addColumn(col.column(Messages.getMessage("widget.label.Info"), "info", type.stringType()))
                .addColumn(buildQuantityColumn("quantity"))
                .addColumn(col.column(Messages.getMessage("widget.label.UnitPrice"), "price", type.bigDecimalType()))
                .addColumn(col.column(Messages.getMessage("widget.label.Cost"), "total", type.bigDecimalType()))
                .addColumn(col.column(Messages.getMessage("widget.label.Discount"), "discount", type.bigDecimalType()))
                .addColumn(col.column(Messages.getMessage("widget.label.Total"), "lineTotal", type.bigDecimalType()));
                
        return report;
    }

    public static TextColumnBuilder<Quantity> buildQuantityColumn(String fieldName) {
        return col.column(Messages.getMessage("widget.label.Quantity"), "quantity", Quantity.class)
                .setValueFormatter(new AbstractValueFormatter<String, Quantity>() {
                    @Override
                    public String format(Quantity value, ReportParameters reportParameters) {
                        return type.bigDecimalType().valueToString(value.getAmount(), reportParameters.getLocale()) + " " + value.getUnitName().getName();
                    }
                });
    }
}
