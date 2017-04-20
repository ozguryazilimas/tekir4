package com.ozguryazilim.tekir.account.reports;

import com.ozguryazilim.tekir.contact.config.ContactPages;
import com.ozguryazilim.telve.adminreport.AuditLogFilter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.joda.time.DateTime;

import com.ozguryazilim.telve.config.LocaleSelector;
import com.ozguryazilim.telve.config.TelveConfigResolver;
import com.ozguryazilim.telve.messages.TelveResourceBundle;
import com.ozguryazilim.telve.query.filters.DateValueType;
import com.ozguryazilim.telve.reports.JasperReportBase;
import com.ozguryazilim.telve.reports.Report;
import com.ozguryazilim.telve.reports.ReportDate;
import com.ozguryazilim.telve.view.Pages;
import net.sf.jasperreports.engine.JRParameter;

/**
 * Cari Durumlar için standart rapor
 *
 * @author Ceyhun Onur
 */
@Report(filterPage = ContactPages.AccountStatusReport.class, permission = "accountStatusReport", path = "/account/status", template = "accountStatusReport", resource = "accountReports")
public class AccountStatusReport extends JasperReportBase {

    @Inject
    private TelveConfigResolver telveConfigResolver;

    private AccountStatusFilter filter;

    public AccountStatusFilter getFilter() {
        if (filter == null) {
            buildFilter();
        }
        return filter;
    }

    public void AccountStatusFilter(AccountStatusFilter filter) {
        this.filter = filter;
    }

    public void buildFilter() {
        filter = new AccountStatusFilter();

        DateTime dt = new DateTime();

        filter.setDate(new ReportDate(DateValueType.Today));
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
        params.put("CODE", getFilter().getCode());
        params.put("CONTACT_CAT_ID", getFilter().getContactCategory() == null ? 
                0 : getFilter().getContactCategory().getId());
        params.put("CORP_TYPE_ID", getFilter().getCorporationType() == null ? 
                0 : getFilter().getCorporationType().getId());
        params.put("DATE", getFilter().getDate());
        params.put("INDUSTRY_ID", getFilter().getIndustry() == null ? 
                0 : getFilter().getIndustry().getId());
        params.put("NAME", getFilter().getName());
        params.put("TERRITORY_ID", getFilter().getTerritory() == null ? 
                0 : getFilter().getTerritory().getId());
        return true;
    }

    @Override
    protected void decorateI18NParams(Map<String, Object> params) {
    	params.put(JRParameter.REPORT_LOCALE, LocaleSelector.instance().getLocale());
        params.put(JRParameter.REPORT_RESOURCE_BUNDLE, TelveResourceBundle.getBundle());
        super.decorateI18NParams(params); // To change body of generated
        // methods, choose Tools |
        // Templates.


    }

}
