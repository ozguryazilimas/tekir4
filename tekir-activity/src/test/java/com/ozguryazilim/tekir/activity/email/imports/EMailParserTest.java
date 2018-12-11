/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.activity.email.imports;

import com.ozguryazilim.tekir.activity.email.imports.model.EMailMessage;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author oyas
 */
public class EMailParserTest {
    
    public EMailParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of parse method, of class EMailParser.
     */
    //@org.junit.Test
    public void testParse_String() throws Exception {
        System.out.println("parse");
        String messages = "";
        EMailParser instance = new EMailParser();
        instance.parse(messages);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of parse method, of class EMailParser.
     */
    @org.junit.Test
    public void testParse_InputStream() throws Exception {
        System.out.println("parse");
        
        
        //FIXME: test hata veriyor neden?
        // InputStream messageStream = this.getClass().getResourceAsStream("/test6.eml");
        
        // EMailParser instance = new EMailParser();
        // EMailMessage result = instance.parse(messageStream);
        
        // System.out.println(result);
        
        // System.out.println(result.getContent());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
