package com.ozguryazilim.tekir.core.reports;

import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.messages.Messages;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 * Dynamic Report için utility fonksiyonlar.
 *
 * @author Hakan Uygun
 */
public class TekirDynamicReportUtils {

    public static TextColumnBuilder<FeaturePointer> buildFeatureColumn(String fieldName) {
        return col.column(Messages.getMessage("general.label.Feature"), fieldName, FeaturePointer.class)
                .setValueFormatter(new AbstractValueFormatter<String, FeaturePointer>() {
                    @Override
                    public String format(FeaturePointer value, ReportParameters reportParameters) {
                        return Messages.getMessage("feature.caption." + value.getFeature()) + " " + value.getBusinessKey();
                    }
                });
    }

    public static TextColumnBuilder<String> buildStatusColumn(String fieldName) {
        return col.column(Messages.getMessage("general.label.Status"), fieldName, type.stringType())
                .setValueFormatter(new AbstractValueFormatter<String, String>() {
                    @Override
                    public String format(String value, ReportParameters reportParameters) {
                        //String[] ss = value.split("-");
                        return Messages.getMessage( "voucherState.name." + value );
                    }
                });
    }

    public static TextColumnBuilder<String> buildProcessTypeColumn(String fieldName) {
        return col.column(Messages.getMessage("general.label.Type"), fieldName, type.stringType())
            .setValueFormatter(new AbstractValueFormatter<String, String>() {
                @Override
                public String format(String value, ReportParameters reportParameters) {
                    //String[] ss = value.split("-");
                    return Messages.getMessage( "processType." + value );
                }
            });
    }

    public static TextColumnBuilder<String> buildProcessStatusColumn(String fieldName) {
        return col.column(Messages.getMessage("general.label.Status"), fieldName, type.stringType())
            .setValueFormatter(new AbstractValueFormatter<String, String>() {
                @Override
                public String format(String value, ReportParameters reportParameters) {
                    //String[] ss = value.split("-");
                    return Messages.getMessage( "processStatus." + value );
                }
            });
    }


}
