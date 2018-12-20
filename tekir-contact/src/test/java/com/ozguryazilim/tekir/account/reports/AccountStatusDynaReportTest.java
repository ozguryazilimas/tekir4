package com.ozguryazilim.tekir.account.reports;

import com.ozguryazilim.tekir.account.AccountTxnStatusModel;
import com.ozguryazilim.telve.api.module.TelveModuleRegistery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import net.sf.dynamicreports.report.exception.DRException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author oyas
 */
public class AccountStatusDynaReportTest {
    
    public AccountStatusDynaReportTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        TelveModuleRegistery.register("TelveCoreModule", "", "TelveCoreModule-messages");
        TelveModuleRegistery.register("TekirCoreModule", "", "TekirCoreModule-messages");
        TelveModuleRegistery.register("TelveContactModule", "", "TekirContactModule-messages");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    
    
    /**
     * Test of execReport method, of class AccountStatusDynaReport.
     */
    @Test
    public void testExecReport() throws DRException {
        System.out.println("execReport");
        AccountStatusDynaReport instance = new AccountStatusDynaReport();
        instance.buildFilter();
        instance.getFilter().setName("Deneme Şirketi");
        
        JasperReportBuilder rb = instance.initReport();//.createReport();
        //instance.buildReport(rb, Boolean.FALSE);
        rb.setDataSource(createDataSource()).show(false);
        System.out.println("execReport");
    }
    
    private JasperReportBuilder createReport(){
        JasperReportBuilder rb = report();
            
            
        return rb;
    }

    private Collection<?> createDataSource() {
        List<AccountTxnStatusModel> rows = new ArrayList<>();
        
        rows.add( new AccountTxnStatusModel( 1L, "Deneme Cari", BigDecimal.ONE, BigDecimal.TEN));
        rows.add( new AccountTxnStatusModel( 2L, "ÜĞİŞÇÖI",  BigDecimal.TEN, BigDecimal.ONE));
        rows.add( new AccountTxnStatusModel( 3L, "üişçöı",  BigDecimal.ONE, BigDecimal.ZERO));
        rows.add( new AccountTxnStatusModel( 4L, "Bişiler bişiler ve daha başka başka şeyler de dahi bişiler", BigDecimal.ONE, BigDecimal.TEN));
        
        return rows;
    }
    
    
}
