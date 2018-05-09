package com.ozguryazilim.finance.account.reports;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import com.ozguryazilim.finance.config.FinancePages;
import com.ozguryazilim.tekir.entities.AccountType;
import com.ozguryazilim.telve.config.LocaleSelector;
import com.ozguryazilim.telve.config.TelveConfigResolver;
import com.ozguryazilim.telve.messages.TelveResourceBundle;
import com.ozguryazilim.telve.reports.JasperReportBase;
import com.ozguryazilim.telve.reports.Report;

import net.sf.jasperreports.engine.JRParameter;

@Report(filterPage = FinancePages.FinancialStatusReport.class, permission="financialStatusReport", path = "/finance/account", template = "financialStatusReport", resource = "financeReports")
public class FinancialStatusReport extends JasperReportBase {

	@Inject
	private TelveConfigResolver telveConfigResolver;

	private FinancialStatusFilter filter;

	public void buildFilter() {
		filter = new FinancialStatusFilter();
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
		params.put("SUBREPORT_DIR", "/jasper/");
		params.put("TYPE", getFilter().getAccountType());
		params.put("ACCOUNT_ID", getFilter().getFinanceAccount() == null ?
				0 : getFilter().getFinanceAccount().getId());
		params.put("IBAN", getFilter().getIBAN());
		return true;
	}

	public List<AccountType> getAccountTypes() {
		return Arrays.asList(AccountType.values());
	}

	public FinancialStatusFilter getFilter() {
		if (filter == null) {
			buildFilter();
		}
		return filter;
	}

	public void setFilter(FinancialStatusFilter filter) {
		this.filter = filter;
	}

	@Override
	protected void decorateI18NParams(Map<String, Object> params) {
		params.put(JRParameter.REPORT_LOCALE, LocaleSelector.instance().getLocale());
		params.put(JRParameter.REPORT_RESOURCE_BUNDLE, TelveResourceBundle.getBundle());
	}

}
