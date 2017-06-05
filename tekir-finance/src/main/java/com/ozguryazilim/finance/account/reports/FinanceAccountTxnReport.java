package com.ozguryazilim.finance.account.reports;

import com.ozguryazilim.finance.config.FinancePages;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import com.ozguryazilim.telve.config.LocaleSelector;
import com.ozguryazilim.telve.config.TelveConfigResolver;
import com.ozguryazilim.telve.messages.TelveResourceBundle;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.reports.JasperReportBase;
import com.ozguryazilim.telve.reports.Report;
import com.ozguryazilim.telve.reports.ReportDate;
import net.sf.jasperreports.engine.JRParameter;

/**
 * Cari Durumlar için standart rapor
 *
 * @author Ceyhun Onur
 */
@Report(filterPage = FinancePages.FinanceAccountTxnReport.class, permission = "financeAccountTxnReport", path = "/finance/account", template = "financeAccountTxnReport", resource = "financeAccountReports")
public class FinanceAccountTxnReport extends JasperReportBase {

    @Inject
    private TelveConfigResolver telveConfigResolver;

    private FinanceAccountTxnFilter filter;

    public FinanceAccountTxnFilter getFilter() {
        if (filter == null) {
            buildFilter();
        }
        return filter;
    }

    public void AccountTxnFilter(FinanceAccountTxnFilter filter) {
        this.filter = filter;
    }

    public void buildFilter() {
        filter = new FinanceAccountTxnFilter();                
        filter.setEndDate(new ReportDate(DateValueType.Today));
        filter.setStartDate(new ReportDate(DateValueType.TenDaysBefore));
    }

    @Override
    protected boolean buildParam(Map<String, Object> params) {
        String logo = telveConfigResolver.getProperty("brand.company.reportLogo");
        String title = telveConfigResolver.getProperty("brand.company.reportTitle");

        if (logo != null) {
            BufferedImage image;
            try {
                image = ImageIO.read(getClass().getResource("/META-INF/resources/brand/img/".concat(logo)));
                params.put("FIRM_LOGO", image);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        params.put("FIRM_TITLE", title);        
        params.put("START_DATE", getFilter().getStartDate());
        params.put("END_DATE", getFilter().getEndDate());
        return true;
    }

    @Override
    protected void decorateI18NParams(Map<String, Object> params) {
    	params.put(JRParameter.REPORT_LOCALE, LocaleSelector.instance().getLocale());
        params.put(JRParameter.REPORT_RESOURCE_BUNDLE, TelveResourceBundle.getBundle());
    }
}
