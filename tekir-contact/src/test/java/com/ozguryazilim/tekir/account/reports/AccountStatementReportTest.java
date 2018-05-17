/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.reports;

import com.ozguryazilim.tekir.entities.AccountTxn;
import com.ozguryazilim.tekir.entities.Contact;
import com.ozguryazilim.tekir.entities.Corporation;
import com.ozguryazilim.telve.api.module.TelveModuleRegistery;
import com.ozguryazilim.telve.entities.FeaturePointer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
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
public class AccountStatementReportTest {
    
    private Contact contact = new Corporation();
    
    public AccountStatementReportTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        TelveModuleRegistery.register("TelveCoreModule", "", "TelveCoreModule-messages");
        TelveModuleRegistery.register("TekirCoreModule", "", "TekirCoreModule-messages");
        TelveModuleRegistery.register("TelveContactModule", "", "TekirContactModule-messages");
        TelveModuleRegistery.register("TelveInvoiceModule", "", "TekirInvoiceModule-messages");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        initContact();
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
        AccountStatementReport instance = new AccountStatementReport();
        instance.buildFilter();
        instance.getFilter().setContact(contact);
        
        JasperReportBuilder rb = instance.initReport();//.createReport();
        //instance.buildReport(rb, Boolean.FALSE);
        rb.setDataSource(createDataSource()).show(false);
        System.out.println("execReport");
    }
    

    private void initContact(){
        contact.setId(1L);
        contact.setName("Deneme Şirketi");
    }
    
    private Collection<?> createDataSource() {
        List<AccountTxn> rows = new ArrayList<>();
        
        FeaturePointer fp = new FeaturePointer();
        fp.setBusinessKey("FT-00001");
        fp.setPrimaryKey(1L);
        fp.setFeature("SalesInvoiceFeature");
        
        AccountTxn txn = new AccountTxn();
        txn.setId(1L);
        txn.setAccount(contact);
        txn.setAmount(BigDecimal.ONE);
        //FIXME txn.setCode("ABC");
        txn.setCurrency( Currency.getInstance("TRY"));
        txn.setDate(new Date());
        txn.setDebit(Boolean.TRUE);
        txn.setFeature(fp);
        txn.setInfo("Belge Açıklaması");
        txn.setLocalAmount(BigDecimal.ONE);
        txn.setOwner("telve");
        txn.setProcessId("PS-00001");
        txn.setReferenceNo("AA-0001");
        txn.setStatus("OPEN-NEUTRAL-OPEN");
        txn.setStatusReason("");
        
        rows.add( txn );
        
        fp = new FeaturePointer();
        fp.setBusinessKey("FT-00002");
        fp.setPrimaryKey(2L);
        fp.setFeature("SalesInvoiceFeature");
        
        txn = new AccountTxn();
        txn.setId(2L);
        txn.setAccount(contact);
        txn.setAmount(BigDecimal.TEN);
        //FIXME txn.setCode("ABC");
        txn.setCurrency( Currency.getInstance("TRY"));
        txn.setDate(new Date());
        txn.setDebit(Boolean.FALSE);
        txn.setFeature(fp);
        txn.setInfo("Belge Açıklaması");
        txn.setLocalAmount(BigDecimal.TEN);
        txn.setOwner("telve");
        txn.setProcessId("PS-00002");
        txn.setReferenceNo("AA-0002");
        txn.setStatus("OPEN-NEUTRAL-OPEN");
        txn.setStatusReason("");
        
        rows.add( txn );
        return rows;
    }
}
